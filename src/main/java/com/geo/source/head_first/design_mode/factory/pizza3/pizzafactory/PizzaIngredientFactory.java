package com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory;

import com.geo.source.head_first.design_mode.factory.pizza3.pizzafactory.ingredient.*;

/**
 * @Description pizza 配料工厂
 * @Author YanZhen
 * 2019-01-15 15:30
 */
public interface PizzaIngredientFactory {

    /**
     * @return 面团
     */
    Dough createDough();// 面团

    /**
     * @return 调料
     */
    Sauce createSauce();// 调料

    /**
     * @return 蔬菜
     */
    Veggies[] createVeggies();// 蔬菜

    /**
     * @return 奶酪
     */
    Cheese createCheese();// 奶酪

    /**
     * @return 意大利辣香肠
     */
    Pepperoni createPepperoni();// 意大利辣香肠

    /**
     * @return 蛤蜊
     */
    Clam createClam();// 蛤蜊
}
