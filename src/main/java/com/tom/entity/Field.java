package com.tom.entity;

import com.tom.util.Utils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by tom on 16/7/2.
 */
public class Field {
    private String name;
    private String type;
    private String isNull;
    private String monthName;

    public String getName() {
        return Utils.underlineToCamel(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Utils.toJavaType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getMonthName() {
        return Utils.firstLetterToUpper(this.getName());
    }




}
