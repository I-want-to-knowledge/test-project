package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @ClassName VeggiePizza
 * @Author YanZhen
 * 2019-01-11 15:28
 * @Description 素食pizza
 */
public class NYStyleVeggiePizza extends Pizza2 {

    public NYStyleVeggiePizza() {
        this.name = "NY Style Veggie Pizza";
        this.dough = "NY Dough";
        this.sauce = "NY Sauce";
        this.toppings.add("Veggie");
    }
}
