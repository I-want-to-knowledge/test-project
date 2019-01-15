package com.geo.source.head_first.design_mode.factory.pizza3;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzastore.ChicagoStylePizzaStore;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzastore.NYStylePizzaStore;

/**
 * @Description pizza test 类
 * @Author YanZhen
 * 2019-01-15 19:54
 */
public class PizzaMain {
    public static void main(String[] args) {
        NYStylePizzaStore nyStylePizzaStore = new NYStylePizzaStore();
        Pizza3 nyPizza3 = nyStylePizzaStore.orderPizza("Cheese");
        System.out.println("You " + nyPizza3.getName() + "Pizza");

        System.out.println();
        
        ChicagoStylePizzaStore chicagoStylePizzaStore = new ChicagoStylePizzaStore();
        Pizza3 chicagoPizza3 = chicagoStylePizzaStore.orderPizza("Clam");
        System.out.println("You " + chicagoPizza3.getName() + "Pizza");
    }
}
