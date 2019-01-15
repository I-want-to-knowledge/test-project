package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @Author YanZhen
 * 2019-01-11 15:33
 * @Description 蛤蜊pizza
 */
public class ChicagoStyleClamPizza extends Pizza2 {

    public ChicagoStyleClamPizza() {
        this.name = "Chicago Clam";
        this.dough = "Chicago dough";
        this.sauce = "Chicago sauce";
        this.toppings.add("Clam");
    }
}
