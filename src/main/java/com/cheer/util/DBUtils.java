package com.cheer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库工具类
 * 1.获取实例
 * 2.获取数据库连接
 * 3.关闭数据库资源
 */
public class DBUtils {
    private static final DBUtils INSTANCE = new DBUtils();

    private DBUtils() {
    }

    /**
     * 获取实例
     * @return
     */
    public static DBUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection() {
        Properties properties = this.load("/conf/jdbc.properties");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        System.out.printf("username=%s, password=%s, url=%s, driverClass=%s", username, password, url, driverClass);

        try {
            // 加载数据库驱动
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

    /**
     * 加载classpath下的properties文件
     * @param file
     * @return
     */
    public Properties load(String file) {

        InputStream in = DBUtils.class.getResourceAsStream(file);

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
    /**
     * 关闭资源
     * @param connection
     * @param statement
     * @param resultSet
     */
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
