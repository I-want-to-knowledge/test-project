package com.geo.source.head_first.design_mode.factory.pizza3.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.chicago.ChicagoPizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle.CheesePizza;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle.ClamPizza;

/**
 * @ClassName ChicagoStylePizzaStore
 * @Author YanZhen
 * 2019-01-11 17:20
 * @Description 芝加哥类型的pizza
 */
public class ChicagoStylePizzaStore extends PizzaStore3 {

    @Override
    Pizza3 createPizza3(String type) {
        Pizza3 pizza3 = null;
        PizzaIngredientFactory chicagoPizzaIngredientFactory = new ChicagoPizzaIngredientFactory();

        switch (type) {
            case "Cheese":
                pizza3 = new CheesePizza(chicagoPizzaIngredientFactory);
                pizza3.setName("Chicago Style Cheese Pizza");
                break;
            case "Veggie":
                // TODO 未实现
                // pizza3.setName("Chicago Style Veggie Pizza");
                break;
            case "Clam":
                pizza3 = new ClamPizza(chicagoPizzaIngredientFactory);
                pizza3.setName("Chicago Style Clam Pizza");
                break;
            case "Pepperoni":
                // TODO 未实现
                // pizza3.setName("Chicago Style Pepperoni Pizza");
                break;
        }
        return pizza3;
    }
}
