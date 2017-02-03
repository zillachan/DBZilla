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

import java.lang.reflect.Field;
import java.util.*;

/**
 * Model properties
 * <br>
 * 表信息缓存记录
 *
 * @author zilla
 */
public class ModelHolder {

    /**
     * model class,model fields(name,type)
     */
    private static HashMap<Class, List<ModelProperty>> mValue = new HashMap<>();

    private static HashMap<Class, String> sqlCache = new HashMap<>();

    /**
     * get model field properties
     *
     * @param c
     * @return
     */
    public static List<ModelProperty> getProperties(Class c) {
        if (mValue.containsKey(c)) {
            return mValue.get(c);
        } else {
            Field[] fields = ReflectUtil.getModelFields(c);
            List<ModelProperty> modelProperties = new ArrayList<>();
            for (Field field : fields) {
                ModelProperty model = new ModelProperty();
                model.setField(field);
                model.setName(field.getName());
                model.setType(field.getType());
                modelProperties.add(model);
            }
            Collections.sort(modelProperties);
            mValue.put(c, modelProperties);
            return modelProperties;
        }
    }


    public static Field getFieldByName(Class c, String fieldName) {
        List<ModelProperty> properties = getProperties(c);
        ModelProperty modelProperty = properties.get(Collections.binarySearch(properties, new ModelProperty(fieldName)));
        if (modelProperty != null) {
            return modelProperty.getField();
        }
        return null;
//        for (ModelProperty modelProperty : properties) {
//            if (modelProperty.getName().equals(fieldName)) return modelProperty.getField();
//        }
//        return null;
    }

    /**
     * get the inset sql of model
     *
     * @param c
     * @return
     */
    public static String getInsetSQL(Class c) {
        if (sqlCache.containsKey(c)) return sqlCache.get(c);
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT");
        sql.append(" INTO ");
        sql.append(AnnotationUtil.getTableName(c));
        sql.append('(');

        List<ModelProperty> modelProperties = ModelHolder.getProperties(c);
        StringBuilder values = new StringBuilder();
        values.append("(");

        for (ModelProperty modelProperty : modelProperties) {
            sql.append(modelProperty.getName()).append(",");
            values.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") values ");

        values.deleteCharAt(values.length() - 1);
        values.append(")");
        sql.append(values);
        String result = sql.toString();
        sqlCache.put(c, result);
        return result;
    }

}
