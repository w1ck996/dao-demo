package com.cheer.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// 如何读取一个properties文件
public class PropertiesResourceLoadTest {
    @Test
    public void load() {
        // 加载classpath下的文件
        InputStream in = PropertiesResourceLoadTest.class.getResourceAsStream("/conf/jdbc.properties");

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        System.out.printf("username=%s, password=%s, url=%s, driverClass=%s", username, password, url, driverClass);
    }
}
