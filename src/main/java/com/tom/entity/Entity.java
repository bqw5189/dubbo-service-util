package com.tom.entity;

import com.tom.util.Utils;

import java.util.List;

/**
 * Created by tom on 16/7/2.
 */
public class Entity {
    public static final String OUT_PATH = "/work/001_code/github/java/pet-hospital/fionapet-business/";
    public static final String OUT_API_PATH = OUT_PATH + "fionapet-business-api/src/main/java/com/fionapet/business/";
    public static final String OUT_REST_PATH = OUT_PATH + "fionapet-business-rest-provider/src/main/java/com/fionapet/business/";
    public static final String OUT_SERVICE_PATH = OUT_PATH + "fionapet-business-service-provider/src/main/java/com/fionapet/business/";

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

    public String getFieldClassName() {
        return Utils.firstLetterToLower(getEntityClassName());
    }

    public String getPath() {
        return getEntityClassName().toLowerCase() + "s";
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
