package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @Author YanZhen
 * 2019-01-11 15:34
 * @Description Pepperoni
 */
public class ChicagoStylePepperoniPizza extends Pizza2 {

    public ChicagoStylePepperoniPizza() {
        this.name = "Chicago Pepperoni";
        this.dough = "Chicago dough";
        this.sauce = "Chicago sauce";
        this.toppings.add("Pepperoni");
    }
}
