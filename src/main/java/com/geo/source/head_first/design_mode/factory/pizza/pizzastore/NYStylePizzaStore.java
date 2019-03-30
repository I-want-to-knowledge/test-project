package com.geo.source.head_first.design_mode.factory.pizza.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza.SimplePizzaFactory;
import com.geo.source.head_first.design_mode.factory.pizza.pizzafactory.*;

/**
 * @ClassName NYPizzaStore
 * @Author YanZhen
 * 2019-01-11 16:50
 * @Description 纽约披萨商店
 */
public class NYStylePizzaStore extends PizzaStore {
    public NYStylePizzaStore(SimplePizzaFactory factory) {
        super(factory);
    }

    @Override
    Pizza createPizza(String type) {
        return new SimplePizzaFactory().createPizza(type);
    }
}
