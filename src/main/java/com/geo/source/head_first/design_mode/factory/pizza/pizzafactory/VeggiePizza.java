package com.geo.source.head_first.design_mode.factory.pizza.pizzafactory;

/**
 * @ClassName VeggiePizza
 * @Author YanZhen
 * 2019-01-11 15:28
 * @Description 素食pizza
 */
public class VeggiePizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Veggie 准备");
    }

    @Override
    public void bake() {
        System.out.println("Veggie 烘烤");
    }

    @Override
    public void cut() {
        System.out.println("Veggie 切片");
    }

    @Override
    public void box() {
        System.out.println("Veggie 装箱");
    }
}
