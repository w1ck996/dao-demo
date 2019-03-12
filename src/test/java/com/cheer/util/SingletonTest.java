package com.cheer.util;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {
    @Test
    public void getInstance() {
        // 通过静态方法获取对象实例
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();

        Assert.assertTrue(singleton == singleton1);
    }
}
