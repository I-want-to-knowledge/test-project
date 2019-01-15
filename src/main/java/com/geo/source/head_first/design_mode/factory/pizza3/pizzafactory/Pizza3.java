package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.*;

/**
 * @Description pizza
 * @Author YanZhen
 * 2019-01-15 11:14
 */
public abstract class Pizza3 {
    protected String name;// 名
    protected Dough dough;// 面团
    protected Sauce sauce;// 调料
    protected Veggies[] veggies;// 蔬菜
    protected Cheese cheese;// 奶酪
    protected Pepperoni pepperoni;// 意大利辣香肠
    protected Clam clam;// 蛤蜊

    /**
     * 准备
     */
    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\"Pizza3\":{\"name\":\"" + name + "\"}";
    }

}
