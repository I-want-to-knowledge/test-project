package com.geo.source.head_first.design_mode.adapter.simple_adapter;

/**
 * 野鸭
 *
 * @author YanZhen
 * @since 2019-01-23 09:55
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack...");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
