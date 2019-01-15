package com.geo.source.head_first.design_mode.factory.pizza3.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;

/**
 * @ClassName PizzaStore
 * @Author YanZhen
 * 2019-01-10 20:31
 * @Description pizza store
 */
public abstract class PizzaStore3 {

    public Pizza3 orderPizza(String type) {
        Pizza3 pizza3 = createPizza3(type);

        // 制作
        pizza3.prepare();
        pizza3.bake();
        pizza3.cut();
        pizza3.box();

        return pizza3;
    }

    abstract Pizza3 createPizza3(String type);
}
