package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 一个Pizza
 * @Author YanZhen
 * 2019-01-14 15:35
 */
public abstract class Pizza2 {
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<>();

    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough ...");
        System.out.println("Adding sauce ...");
        System.out.println("Adding toppings: ");
        for (Object topping:toppings) {
            System.out.println("  " + topping);
        }
    }

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
}
