package com.geo.source.head_first.design_mode.singletonpattern;

/**
 * @Description 单例模式，一个‘同步’的单例模式
 * @Author YanZhen
 * 2019-01-17 15:10
 */
public class SingletonPattern1 {
    private static SingletonPattern1 singletonPattern1;
    private int a = 0;

    private SingletonPattern1() {
    }

    public static SingletonPattern1 getInstance() {
        if (singletonPattern1 == null) {
            singletonPattern1 = new SingletonPattern1();
        }
        return singletonPattern1;
    }

    void getParam() {
        // System.out.println("==================1====================");
        System.out.println("当前数值：" + ++a);
    }
}
