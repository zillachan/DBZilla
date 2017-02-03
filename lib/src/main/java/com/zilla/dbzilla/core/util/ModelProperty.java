package com.zilla.dbzilla.core.util;

import java.lang.reflect.Field;

/**
 * Created by zilla on 23/01/2017.
 */
public class ModelProperty implements Comparable<ModelProperty> {

    private Field field;
    /**
     * model field name;
     */
    private String name;

    /**
     * model field type;
     */
    private Class type;

    public ModelProperty() {
    }

    public ModelProperty(String name) {
        this.name = name;
    }


    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ModelProperty{" +
                "field=" + field +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(ModelProperty o) {
        return this.name.compareTo(o.getName());
    }
}
