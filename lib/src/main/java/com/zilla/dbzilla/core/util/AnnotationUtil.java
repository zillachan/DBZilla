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
package com.zilla.dbzilla.core.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.github.snowdream.android.util.Log;
import com.zilla.dbzilla.core.Id;
import com.zilla.dbzilla.core.Table;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * annotation util
 * <br>
 * 注释工作类
 *
 * @author ze.chen
 */
public class AnnotationUtil {


    /**
     * get the tableName from Model
     */
    private static HashMap<Class, String> tableNameCache = new HashMap<>();
    private static HashMap<Class, String> tableIdCache = new HashMap<>();

    /**
     * get @Id annotation from a model.
     * <br>
     * 获取模型中定义的主键
     *
     * @param model the pojo model
     * @return the key of the table
     */
    public static String getIdName(Class<?> model) {
        return getAutoIdName(model, false);
    }

    private static String getAutoIdName(Class<?> model, boolean isAutoIncrease) {
        if (tableIdCache.containsKey(model)) {
            return tableIdCache.get(model);
        } else {
            String primaryKey = "";
            List<ModelProperty> modelProperties = ModelHolder.getProperties(model);
            if (modelProperties != null) {
                Id id = null;
                Field idField = null;
                for (ModelProperty modelProperty : modelProperties) { //获取ID注解
                    id = modelProperty.getField().getAnnotation(Id.class);
                    if (id != null) {
                        idField = modelProperty.getField();
                        break;
                    }
                }
                if (id != null) { //有ID注解
                    primaryKey = idField.getName();
                } else {
                    throw new RuntimeException("@Id annotation is not found, Please make sure the @Id annotation is added in Model!");
                }
                if (isAutoIncrease) {
                    //type check.
                    Class type = idField.getType();
                    if (type == int.class || type == long.class) {
                        return primaryKey;
                    } else {
                        return null;
                    }
                }
            }
            tableIdCache.put(model, primaryKey);
            return primaryKey;
        }
    }


    /**
     * get tableName from a model.
     * <br>
     * 获取Class注释名称，如果没有注释名称则取类名的小写字符串
     *
     * @param c the pojo model
     * @return the table name of the model
     */
    public static String getTableName(Class<?> c) {
        if (tableNameCache.containsKey(c)) {
            return tableNameCache.get(c);
        } else {
            String tableName;
            Table table = c.getAnnotation(Table.class);
            if (table == null || TextUtils.isEmpty(table.value())) {
                //如果存在类名注释，使用注释名；否则使用类名小写的字符串作为表名
                String className = c.getName();
                tableName = className.substring(className.lastIndexOf(".") + 1).toLowerCase();
            } else {
                tableName = table.value();
            }
            tableNameCache.put(c, tableName);
            return tableName;
        }
    }

    /**
     * get model key value
     *
     * @param obj
     * @return
     */
    public static Object getIdValue(Object obj) {
        return ReflectUtil.getFieldValue(obj, getIdName(obj.getClass()));
    }

    /**
     * set keyvalue of an saved obj
     *
     * @param obj
     * @param value
     * @return
     */
    public static void setIdValue(Object obj, Object value) {
        String key = getIdName(obj.getClass());
        ReflectUtil.setFieldValue(obj, key, value);
    }

    public static void setAutoIdValue(Object obj, Object value) {
        String key = getAutoIdName(obj.getClass(), true);
        ReflectUtil.setFieldValue(obj, key, value);
    }

    public static ContentValues model2ContentValues(SQLiteDatabase database, Object model, String tableName) {
        ContentValues value = new ContentValues();
        String[] tableFields = TableHolder.getTableFields(database, tableName);
        HashMap<String, Object> mValues = getContentmValues(value);
        for (int i = 0, l = tableFields.length; i < l; i++) {
            mValues.put(tableFields[i], ReflectUtil.getFieldValue(model, tableFields[i]));
        }
        return value;
    }


    private static HashMap<String, Object> getContentmValues(ContentValues value) {
        Field f = ReflectUtil.getModelField(value.getClass(), "mValues");
        f.setAccessible(true);
        if (f != null) {
            try {
                return (HashMap<String, Object>) f.get(value);
            } catch (IllegalAccessException e) {
                Log.e("getContentmValues", e);
            }
        }
        return null;
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


}