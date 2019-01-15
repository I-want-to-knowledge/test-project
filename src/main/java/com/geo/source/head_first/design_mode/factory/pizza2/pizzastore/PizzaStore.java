package com.geo.source.head_first.design_mode.factory.pizza2.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @ClassName PizzaStore
 * @Author YanZhen
 * 2019-01-10 20:31
 * @Description pizza store
 */
public abstract class PizzaStore {

    public Pizza2 orderPizza(String type) {
        Pizza2 pizza3;
        pizza3 = createPizza2(type);

        // 制作
        pizza3.prepare();
        pizza3.bake();
        pizza3.cut();
        pizza3.box();

        return pizza3;
    }

    abstract Pizza2 createPizza2(String type);
}
