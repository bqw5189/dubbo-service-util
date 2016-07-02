package com.tom.entity;

import com.tom.util.Utils;

import java.util.List;

/**
 * Created by tom on 16/7/2.
 */
public class Entity {
    public static final String OUT_PATH = "/work/001_code/github/java/pet-hospital/fionapet-business/fionapet-business-api/src/main/java/com/fionapet/business/entity/";
    public static final String TEMP_PATH = "api/entity/Entity.tpl";
    public static final String[] FILTER_FIELD = new String[]{"createUserId","createDate", "updateUserId", "updateDate","status"};
    private String entityName;
    private String entityClassName;
    private String tableName;
    private List<Field> fields;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityClassName() {
        return Utils.underlineToCamel(this.getTableName()).substring(1);
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = Utils.filterField(fields);
    }
}
