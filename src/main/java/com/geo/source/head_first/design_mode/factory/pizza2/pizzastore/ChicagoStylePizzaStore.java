package com.geo.source.head_first.design_mode.factory.pizza2.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.Pizza2;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago.ChicagoStyleCheesePizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago.ChicagoStyleClamPizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago.ChicagoStylePepperoniPizza;
import com.geo.source.head_first.design_mode.factory.pizza2.pizzafactory.chicago.ChicagoStyleVeggiePizza;

/**
 * @ClassName ChicagoStylePizzaStore
 * @Author YanZhen
 * 2019-01-11 17:20
 * @Description 芝加哥类型的pizza
 */
public class ChicagoStylePizzaStore extends PizzaStore {

    @Override
    Pizza2 createPizza2(String type) {
        Pizza2 pizza2 = null;
        switch (type) {
            case "Cheese":
                pizza2 = new ChicagoStyleCheesePizza();
                break;
            case "Veggie":
                pizza2 = new ChicagoStyleVeggiePizza();
                break;
            case "Clam":
                pizza2 = new ChicagoStyleClamPizza();
                break;
            case "Pepperoni":
                pizza2 = new ChicagoStylePepperoniPizza();
                break;
        }
        return pizza2;
    }
}
