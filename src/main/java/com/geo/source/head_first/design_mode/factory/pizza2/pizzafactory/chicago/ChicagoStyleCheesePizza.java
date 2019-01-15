package com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;

/**
 * @Author YanZhen
 * 2019-01-11 14:05
 * @Description 奶酪pizza
 */
public class ChicagoStyleCheesePizza extends Pizza2 {

    public ChicagoStyleCheesePizza() {
        this.name = "Chicago Style Deep Dish Cheese Pizza";
        this.dough = "Extra Thick Crust Dough";
        this.sauce = "Plum Tomato Sauce";
        this.toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
