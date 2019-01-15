package com.geo.source.head_first.design_mode.factory.pizza;

import com.geo.source.head_first.design_mode.factory.pizza.pizzafactory.*;

/**
 * @ClassName SimplePizzaFactory
 * @Author YanZhen
 * 2019-01-10 20:33
 * @Description 简单的pizza工厂
 */
public class SimplePizzaFactory {

    /**
     * 创建pizza
     * @param type
     * @return Pizza
     */
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "Cheese":
                pizza = new CheesePizza();
                break;
            case "Veggie":
                pizza = new VeggiePizza();
                break;
            case "Clam":
                pizza = new ClamPizza();
                break;
            case "Pepperoni":
                pizza = new PepperoniPizza();
                break;
            default:
                return null;
        }

        return pizza;
    }
}
