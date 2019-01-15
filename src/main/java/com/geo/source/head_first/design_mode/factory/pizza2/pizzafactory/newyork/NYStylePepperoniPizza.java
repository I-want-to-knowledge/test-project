package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @ClassName PepperoniPizza
 * @Author YanZhen
 * 2019-01-11 15:34
 * @Description Pepperoni
 */
public class NYStylePepperoniPizza extends Pizza2 {

    public NYStylePepperoniPizza() {
        this.name = "NY Style Pepperoni Pizza";
        this.dough = "NY Dough";
        this.sauce = "NY Sauce";
        this.toppings.add("Pepperoni");
    }
}
