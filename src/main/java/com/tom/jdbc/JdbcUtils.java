package com.tom.jdbc;

import com.tom.entity.Field;
import com.tom.util.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 16/7/2.
 */
public class JdbcUtils {
    //数据库用户名
    private static final String USERNAME = "test";
    //数据库密码
    private static final String PASSWORD = "123456";
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static final String URL = "jdbc:mysql://localhost:3306/fiona_pet_business";
    private static final char UNDERLINE = '_';

    private Connection connection;

    private PreparedStatement pstmt;

    private ResultSet resultSet;

    public JdbcUtils() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(DRIVER);
            System.out.println("数据库连接成功！");

        } catch (Exception e) {

        }

        this.getConnection();
    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public List<Field> desc(String tableName) throws SQLException {
        List<Field> list = new ArrayList<Field>();

        pstmt = connection.prepareStatement("desc " + tableName);

        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            Field field = new Field();
            field.setName(resultSet.getString("Field"));
            field.setType(resultSet.getString("Type"));
            field.setIsNull(resultSet.getString("Null"));
            list.add(field);
        }

        return list;
    }



//    /**
//     * @param args
//     */
//    public static void main(String[] args) throws SQLException {
//        // TODO Auto-generated method stub
//        JdbcUtils jdbcUtils = new JdbcUtils();
//        jdbcUtils.getConnection();
//
//        String tableName = "t_pet_small_race";
//
//        List<Map<String, String>> descs = jdbcUtils.desc(tableName);
//
//        for (Map<String, String> desc:descs){
//            System.out.println(desc.get("Field") + "-" + Utils.underlineToCamel(desc.get("Field")));
//        }
//    }
}

