package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @Author YanZhen
 * 2019-01-11 15:28
 * @Description 素食pizza
 */
public class ChicagoStyleVeggiePizza extends Pizza2 {

    public ChicagoStyleVeggiePizza() {
        this.name = "Chicago Style Sauce and Veggie Pizza";
        this.dough = "Thin Crust Dough";
        this.sauce = "Marinara Sauce";
        this.toppings.add("Veggie");
    }
}
