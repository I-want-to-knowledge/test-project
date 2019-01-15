package com.geo.source.head_first.design_mode.factory.pizza2;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzastore.ChicagoStylePizzaStore;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzastore.NYStylePizzaStore;

/**
 * @ClassName PizzaMain
 * @Author YanZhen
 * 2019-01-11 15:44
 * @Description Pizza Test
 */
public class PizzaMain {
    public static void main(String[] args) {
        // 纽约风味pizza
        NYStylePizzaStore nyStylePizzaStore = new NYStylePizzaStore();
        Pizza2 nyCheese = nyStylePizzaStore.orderPizza("Cheese");
        System.out.println("Ethan ordered a " + nyCheese.getName());
        System.out.println();

        // 芝加哥风味pizza
        ChicagoStylePizzaStore chicagoStylePizzaStore = new ChicagoStylePizzaStore();
        Pizza2 chicagoCheese = chicagoStylePizzaStore.orderPizza("Cheese");
        System.out.println("Joel ordered a " + chicagoCheese.getName());
    }
}
