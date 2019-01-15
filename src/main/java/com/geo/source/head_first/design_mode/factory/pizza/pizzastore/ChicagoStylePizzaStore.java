package com.geo.source.head_first.design_mode.factory.pizza.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza.SimplePizzaFactory;
import com.geo.source.head_first.design_mode.factory.pizza.pizzafactory.*;

/**
 * @ClassName ChicagoStylePizzaStore
 * @Author YanZhen
 * 2019-01-11 17:20
 * @Description 芝加哥类型的pizza
 */
public class ChicagoStylePizzaStore extends PizzaStore {

    public ChicagoStylePizzaStore(SimplePizzaFactory factory) {
        super(factory);
    }

    @Override
    Pizza createPizza(String type) {
        return new SimplePizzaFactory().createPizza(type);
    }
}
