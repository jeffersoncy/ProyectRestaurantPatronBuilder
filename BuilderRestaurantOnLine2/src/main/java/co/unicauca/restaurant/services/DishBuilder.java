
package co.unicauca.restaurant.services;

import co.unicauca.restaurant.domain.Dish;

/**
 *
 * @author ahurtado
 */
public abstract class DishBuilder {
    
    protected Dish myDish;
    
    Dish getDish() {
        return myDish;
    }

    public abstract DishBuilder init();

    public abstract DishBuilder setCore();

    public abstract boolean addPart();

    public abstract DishBuilder setSize();
    
}
