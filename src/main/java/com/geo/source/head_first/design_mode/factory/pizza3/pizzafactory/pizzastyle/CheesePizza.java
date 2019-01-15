package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.Cheese;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.Sauce;

/**
 * @Description 奶酪pizza
 * @Author YanZhen
 * 2019-01-15 15:06
 */
public class CheesePizza extends Pizza3 {
    private PizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
    }
}
