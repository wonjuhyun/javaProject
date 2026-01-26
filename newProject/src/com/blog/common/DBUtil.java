package com.blog.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
    		"jdbc:oracle:thin:@10.15.21.232:1521:xe";
    private static final String USER = "team2";
    private static final String PASSWORD = "java";

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
