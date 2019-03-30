package com.geo.source.head_first.design_mode.factory.pizza2.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork.NYStyleCheesePizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork.NYStyleClamPizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork.NYStylePepperoniPizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork.NYStyleVeggiePizza;

/**
 * @ClassName NYPizzaStore
 * @Author YanZhen
 * 2019-01-11 16:50
 * @Description 纽约披萨商店
 */
public class NYStylePizzaStore extends PizzaStore {

    @Override
    Pizza2 createPizza2(String type) {
        Pizza2 pizza2 = null;
        switch (type) {
            case "Cheese":
                pizza2 = new NYStyleCheesePizza();
                break;
            case "Veggie":
                pizza2 = new NYStyleVeggiePizza();
                break;
            case "Clam":
                pizza2 = new NYStyleClamPizza();
                break;
            case "Pepperoni":
                pizza2 = new NYStylePepperoniPizza();
                break;
        }
        return pizza2;
    }
}
