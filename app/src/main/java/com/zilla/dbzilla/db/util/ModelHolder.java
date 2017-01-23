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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            mValue.put(c, modelProperties);
            return modelProperties;
        }
    }

    private static HashMap<Class, String> keyCache = new HashMap<>();

    /**
     * get Id name
     *
     * @param c
     * @return
     */
    public static String getIdName(Class c) {
        if (keyCache.containsKey(c)) {
            return keyCache.get(c);
        } else {
            String key = AnnotationUtil.getIdName(c);
            keyCache.put(c, key);
            return key;
        }
    }

}
