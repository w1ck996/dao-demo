package com.cheer.util;

// 设计模式 单例模式 23种设计模式
// 设计优点：在整个系统里有且仅有一个对象，可以做到节省资源，使用场景：数据库的连接等资源
public class Singleton {
    // 创建一个私有静态常量存储类的对象
    private static final Singleton INSTANCE = new Singleton();

    // 构造方法私有化
    private Singleton() {}

    // 提供一个静态方法获取实例
    public static Singleton getInstance() {
        return INSTANCE;
    }
}
