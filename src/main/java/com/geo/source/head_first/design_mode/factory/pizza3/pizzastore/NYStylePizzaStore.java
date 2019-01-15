package com.geo.source.head_first.design_mode.factory.pizza3.pizzastore;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.newyork.NYPizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle.CheesePizza;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle.ClamPizza;

/**
 * @ClassName NYPizzaStore
 * @Author YanZhen
 * 2019-01-11 16:50
 * @Description New York style pizza store
 */
public class NYStylePizzaStore extends PizzaStore3 {

    @Override
    Pizza3 createPizza3(String type) {
        Pizza3 pizza3 = null;
        PizzaIngredientFactory nyPizzaIngredientFactory = new NYPizzaIngredientFactory();

        switch (type) {
            case "Cheese":
                pizza3 = new CheesePizza(nyPizzaIngredientFactory);
                pizza3.setName("New York Style Cheese Pizza");
                break;
            case "Veggie":
                // TODO 未实现
                // pizza3.setName("New York Style Veggie Pizza");
                break;
            case "Clam":
                pizza3 = new ClamPizza(nyPizzaIngredientFactory);
                pizza3.setName("New York Style Clam Pizza");
                break;
            case "Pepperoni":
                // TODO 未实现
                // pizza3.setName("New York Style Pepperoni Pizza");
                break;
        }
        return pizza3;
    }
}
