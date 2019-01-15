package com.geo.source.head_first.design_mode.factory.pizza.pizzafactory;

/**
 * @ClassName CheesePizza
 * @Author YanZhen
 * 2019-01-11 14:05
 * @Description 奶酪pizza
 */
public class CheesePizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Cheese 准备");
    }

    @Override
    public void bake() {
        System.out.println("Cheese 烘烤");
    }

    @Override
    public void cut() {
        System.out.println("Cheese 切片");
    }

    @Override
    public void box() {
        System.out.println("Cheese 装箱");
    }
}
