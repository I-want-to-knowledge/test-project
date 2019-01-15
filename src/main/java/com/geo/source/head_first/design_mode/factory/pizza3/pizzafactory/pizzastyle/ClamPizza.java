package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.pizzastyle;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.Pizza3;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;

/**
 * @Description 蛤蜊pizza
 * @Author YanZhen
 * 2019-01-15 16:24
 */
public class ClamPizza extends Pizza3 {
    private PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
    }
}
