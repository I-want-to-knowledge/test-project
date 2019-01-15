package com.geo.source.head_first.design_mode.factory.pizza.pizzafactory;

/**
 * @ClassName ClamPizza
 * @Author YanZhen
 * 2019-01-11 15:33
 * @Description 蛤蜊pizza
 */
public class ClamPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Clam 准备");
    }

    @Override
    public void bake() {
        System.out.println("Clam 烘烤");
    }

    @Override
    public void cut() {
        System.out.println("Clam 切片");
    }

    @Override
    public void box() {
        System.out.println("Clam 装箱");
    }
}
