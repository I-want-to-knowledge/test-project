package com.geo.source.head_first.design_mode.factory.pizza.pizzafactory;

/**
 * @ClassName PepperoniPizza
 * @Author YanZhen
 * 2019-01-11 15:34
 * @Description Pepperoni
 */
public class PepperoniPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Pepperoni 准备");
    }

    @Override
    public void bake() {
        System.out.println("Pepperoni 烘烤");
    }

    @Override
    public void cut() {
        System.out.println("Pepperoni 切片");
    }

    @Override
    public void box() {
        System.out.println("Pepperoni 装箱");
    }
}
