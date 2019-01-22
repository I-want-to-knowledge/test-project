package com.geo.source.head_first.design_mode.singletonpattern;

/**
 * @Description 单例模式，一个‘双重加锁’的单例模式
 * @Author YanZhen
 * 2019-01-17 15:10
 */
public class SingletonPattern3 {
    private static volatile SingletonPattern3 singletonPattern3;
    private int a = 0;

    private SingletonPattern3() {
    }

    public static SingletonPattern3 getInstance() {
        if (singletonPattern3 == null) {
            synchronized (SingletonPattern3.class) {
                if (singletonPattern3 == null) {
                    singletonPattern3 = new SingletonPattern3();
                }
            }
        }
        return singletonPattern3;
    }

    void getParam() {
        // System.out.println("==================3====================");
        System.out.println("当前数值：" + ++a);
    }
}
