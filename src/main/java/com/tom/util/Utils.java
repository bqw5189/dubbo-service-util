package com.tom.util;

import com.tom.entity.Field;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tom.entity.Entity.FILTER_FIELD;

/**
 * Created by tom on 16/7/2.
 */
public class Utils {
    private static final char UNDERLINE = '_';

    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String firstLetterToUpper(String str){
        char[] array = str.toCharArray();
        array[0] -= 32;
        return String.valueOf(array);
    }
    public static String firstLetterToLower(String str){
        char[] array = str.toCharArray();
        array[0] += 32;
        return String.valueOf(array);
    }

    public static String toJavaType(String type) {
        if (StringUtils.indexOf(type,"timestamp") > -1){
            return "Date";
        }else if (StringUtils.indexOf(type,"tinyint") > -1){
            return "Boolean";
        }else if (StringUtils.indexOf(type,"int") > -1){
            return "Integer";
        }
        return "String";
    }

    public static List<Field> filterField(List<Field> fields) {
        List<Field> fieldList = new ArrayList<Field>();
        for(Field field: fields){
            if (ArrayUtils.contains(FILTER_FIELD, field.getName())){
                continue;
            }
            fieldList.add(field);
        }
        return fieldList;
    }
}
