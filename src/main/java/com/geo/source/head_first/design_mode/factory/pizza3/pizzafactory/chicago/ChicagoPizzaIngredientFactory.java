package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.chicago;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.PizzaIngredientFactory;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.chicago.ingredient.*;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.*;
import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.newyork.ingredient.SlicedPepperoni;

/**
 * @Description 芝加哥pizza原料工厂
 * @Author YanZhen
 * 2019-01-15 15:36
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new BlackOlives(), new Spinach(), new EggPlant()};
    }

    @Override
    public Cheese createCheese() {
        return new Mozzarella();
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clam createClam() {
        return new FrozenClams();
    }
}
