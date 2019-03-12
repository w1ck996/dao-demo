package com.cheer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// 数据库工具类
public class DBUtils {
    private static final DBUtils INSTANCE = new DBUtils();

    private DBUtils() {
    }

    public static DBUtils getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Properties properties = this.load();
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        System.out.printf("username=%s, password=%s, url=%s, driverClass=%s", username, password, url, driverClass);

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("建立数据库连接失败！");
            e.printStackTrace();
            return null;
        }
        System.out.println("建立数据库连接成功！");
        return connection;
    }

    public Properties load() {
        // 加载classpath下的文件
        InputStream in = DBUtils.class.getResourceAsStream("/conf/jdbc.properties");

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
