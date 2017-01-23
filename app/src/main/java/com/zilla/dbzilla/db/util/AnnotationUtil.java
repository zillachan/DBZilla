/*
Copyright 2015 Zilla Chen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.zilla.dbzilla.db.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.github.snowdream.android.util.Log;
import com.zilla.dbzilla.db.Id;
import com.zilla.dbzilla.db.Table;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * annotation util
 * <br>
 * 注释工作类
 *
 * @author ze.chen
 */
public class AnnotationUtil {

    /**
     * get @Id annotation from a model.
     * <br>
     * 获取模型中定义的主键
     *
     * @param model the pojo model
     * @return the key of the table
     */
    public static String getClassKey(Class<?> model) {
        String primaryKey = "";
        Field[] fields = model.getDeclaredFields();
        if (fields != null) {
            Id id = null;
            Field idField = null;
            for (Field field : fields) { //获取ID注解
                id = field.getAnnotation(Id.class);
                if (id != null) {
                    idField = field;
                    break;
                }
            }
            if (id != null) { //有ID注解
                primaryKey = idField.getName();
            } else {
                throw new RuntimeException("@Id annotation is not found, Please make sure the @Id annotation is added in Model!");
            }
        }
        return primaryKey;
    }

    /**
     * get model key value
     *
     * @param obj
     * @return
     */
    public static Object getKeyValue(Object obj) {
        Object value = null;
        Class c = obj.getClass();
        String key = getClassKey(c);
        try {
            Field keyField = c.getField(key);
            value = keyField.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * set keyvalue of an saved obj
     *
     * @param obj
     * @param value
     * @return
     */
    public static void setKeyValue(Object obj, Object value) {
        ReflectUtil.setFieldValue(obj, getKeyByModel(obj.getClass()), value);
    }

    public static ContentValues model2ContentValues(SQLiteDatabase database, Object model, String tableName) {
        ContentValues value = new ContentValues();
        String[] tableFields = getTableFields(database, tableName);
        HashMap<String, Object> mValues = getContentValues(value);
        for (int i = 0, l = tableFields.length; i < l; i++) {
            mValues.put(tableFields[i], ReflectUtil.getFieldValue(model, tableFields[i]));
        }
        return value;
    }


    private static HashMap<String, Object> getContentValues(ContentValues value) {
        Field f = getModelField(value.getClass(), "mValues");
        f.setAccessible(true);
        if (f != null) {
            try {
                return (HashMap<String, Object>) f.get(value);
            } catch (IllegalAccessException e) {
                Log.e("getContentValues", e);
            }
        }
        return null;
    }

    /**
     * get tableName from a model.
     * <br>
     * 获取Class注释名称，如果没有注释名称则取类名的小写字符串
     *
     * @param model the pojo model
     * @return the table name of the model
     */
    public static String getClassName(Class<?> model) {
        Table table = model.getAnnotation(Table.class);
        if (table == null || TextUtils.isEmpty(table.value())) {
            //如果存在类名注释，使用注释名；否则使用类名小写的字符串作为表名
            String className = model.getName();
            return className.substring(className.lastIndexOf(".") + 1).toLowerCase(Locale.CHINA);
        }
        return table.value();
    }

    /**
     * getChildClass
     *
     * @param field List<Child> children type
     * @return thie child class
     */
    public static Class getChildClass(Field field) {
        Type t = field.getGenericType();
        Type actualType = ((ParameterizedType) t).getActualTypeArguments()[0];
        Class subclass = null;
        try {
            subclass = Class.forName(actualType.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(subclass.getSimpleName());
        return subclass;
    }

    /**
     * getChildObjs
     *
     * @param object the obj to save to db ,this object contains the property private List<Child> children;
     * @return the List<Child> value
     */
    public static List<List> getChildObjs(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<List> result = new ArrayList<>();
        for (Field field : fields) {
            if ("java.util.List".equals(field.getType().getName())) {
                List list = null;
                try {
                    field.setAccessible(true);
                    list = (List) field.get(object);
                    result.add(list);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * get the t type of list<T>
     *
     * @param parentClass
     * @return
     */
    public static List<Class> getTypeofListChild(Class parentClass) {
        Field[] fs = parentClass.getDeclaredFields(); // 得到所有的fields
        List childClass = new ArrayList();
        for (Field f : fs) {
            Class fieldClazz = f.getType(); // 得到field的class及类型全路径

            if (fieldClazz.isPrimitive()) continue;  //【1】 //判断是否为基本类型

            if (fieldClazz.getName().startsWith("java.lang")) continue; //getName()返回field的类型全路径；

            if (fieldClazz.isAssignableFrom(List.class)) //【2】
            {
                Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型

                if (fc == null) continue;

                if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                {
                    ParameterizedType pt = (ParameterizedType) fc;

                    Class genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。

                    childClass.add(genericClazz);

                }
            }
        }
        return childClass;
    }


    /**
     * get the tableName from Model
     */
    private static HashMap<Class, String> tableNameCache = new HashMap<>();
    private static HashMap<Class, String> keyCache = new HashMap<>();
    private static HashMap<String, String[]> tableFieldCache = new HashMap<>();
    private static HashMap<Class, Field[]> modelFieldsCache = new HashMap<>();
    private static HashMap<String, Field> modelFieldCache = new HashMap<>();


    /**
     * get table name from model.
     *
     * @param c
     * @return
     */
    public static String getTableNameByModel(Class c) {
        if (tableNameCache.containsKey(c)) {
            return tableNameCache.get(c);
        } else {
            String tableName = AnnotationUtil.getClassName(c);
            tableNameCache.put(c, tableName);
            return tableName;
        }
    }

    /**
     * get the key of table
     *
     * @param c
     * @return
     */
    public static String getKeyByModel(Class c) {
        if (keyCache.containsKey(c)) {
            return keyCache.get(c);
        } else {
            String key = AnnotationUtil.getClassKey(c);
            keyCache.put(c, key);
            return key;
        }
    }

    /**
     * get table fields by tableName
     *
     * @param database
     * @param tableName
     * @return
     */
    public static String[] getTableFields(SQLiteDatabase database, String tableName) {
        if (tableFieldCache.containsKey(tableName)) {
            return tableFieldCache.get(tableName);
        } else {
            Cursor cursor = database.query(tableName, null, null, null, null, null, null, "1");//执行了一次空查询。
            String[] names = cursor.getColumnNames();
            tableFieldCache.put(tableName, names);
            cursor.close();
            return names;
        }
    }

    /**
     * get fields of model.
     *
     * @param c
     * @return
     */
    public static Field[] getModelFields(Class c) {
        if (modelFieldsCache.containsKey(c)) {
            return modelFieldsCache.get(c);
        } else {
            Field[] fields = c.getDeclaredFields();
            modelFieldsCache.put(c, fields);
            return fields;
        }
    }

    /**
     * get field by field name from class.
     *
     * @param c
     * @param fieldName
     * @return
     */
    public static Field getModelField(Class c, String fieldName) {
        String key = c.getName() + "." + fieldName;
        if (modelFieldCache.containsKey(key)) {
            return modelFieldCache.get(key);
        } else {
            Field f = null;
            try {
                f = c.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } finally {
                modelFieldCache.put(key, f);
                return f;
            }
        }
    }
}