package com.geo.source.head_first.design_mode.factory.pizza.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza.SimplePizzaFactory;
import com.geo.source.head_first.design_mode.factory.pizza.pizzafactory.Pizza;

/**
 * @ClassName PizzaStore
 * @Author YanZhen
 * 2019-01-10 20:31
 * @Description pizza store
 */
public abstract class PizzaStore {
    private SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);

        // 制作
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    abstract Pizza createPizza(String type);
}
