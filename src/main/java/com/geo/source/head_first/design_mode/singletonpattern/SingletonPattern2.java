package com.geo.source.head_first.design_mode.singletonpattern;

/**
 * @Description 单例模式，一个‘急切’实例化的单例模式
 * @Author YanZhen
 * 2019-01-17 15:10
 */
public class SingletonPattern2 {

    private static SingletonPattern2 singletonPattern2 = new SingletonPattern2();
    private int a = 0;

    private SingletonPattern2() {
    }

    public static SingletonPattern2 getInstance() {
        return singletonPattern2;
    }

    void getParam() {
        // System.out.println("==================2====================");
        System.out.println("当前数值：" + ++a);
    }
}
