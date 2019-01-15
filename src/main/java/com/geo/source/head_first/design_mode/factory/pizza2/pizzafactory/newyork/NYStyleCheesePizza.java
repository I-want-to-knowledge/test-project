package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @ClassName CheesePizza
 * @Author YanZhen
 * 2019-01-11 14:05
 * @Description 奶酪pizza
 */
public class NYStyleCheesePizza extends Pizza2 {

    public NYStyleCheesePizza() {
        this.name = "NY Style Sauce and Cheese Pizza";
        this.dough = "Thin Crust Dough";
        this.sauce = "Marinara Sauce";
        this.toppings.add("Grated Reggiano Cheese");
    }
}
