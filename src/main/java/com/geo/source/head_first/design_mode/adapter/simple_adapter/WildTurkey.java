package com.geo.source.head_first.design_mode.adapter.simple_adapter;

/**
 * 野生的火鸡
 *
 * @author YanZhen
 * @since 2019-01-23 10:03
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("gobble...");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
