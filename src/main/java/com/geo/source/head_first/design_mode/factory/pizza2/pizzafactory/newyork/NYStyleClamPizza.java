package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.newyork;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @Author YanZhen
 * 2019-01-11 15:33
 * @Description 蛤蜊pizza
 */
public class NYStyleClamPizza extends Pizza2 {

    public NYStyleClamPizza() {
        this.name = "NY Style Clam Pizza";
        this.dough = "NY Dough";
        this.sauce = "NY Sauce";
        this.toppings.add("Clam");
    }
}
