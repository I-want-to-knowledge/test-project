package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.newyork;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.*;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.newyork.ingredient.*;

/**
 * @Description new York pizza 原料工厂
 * @Author YanZhen
 * 2019-01-15 15:39
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clam createClam() {
        return new FreshClams();
    }
}
