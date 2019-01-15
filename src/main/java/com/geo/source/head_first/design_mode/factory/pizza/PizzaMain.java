package com.geo.source.head_first.design_mode.factory.pizza;

import com.geo.source.head_first.design_mode.factory.pizza.pizzastore.ChicagoStylePizzaStore;
import com.geo.source.head_first.design_mode.factory.pizza.pizzastore.PizzaStore;

/**
 * @ClassName PizzaMain
 * @Author YanZhen
 * 2019-01-11 15:44
 * @Description Pizza Test
 */
public class PizzaMain {
    public static void main(String[] args) {
        PizzaStore pizza = new ChicagoStylePizzaStore(new SimplePizzaFactory());
        String type = "Clam";
        pizza.orderPizza(type);
        System.out.println("您的 " + type + " Pizza 来啦！");
    }
}
