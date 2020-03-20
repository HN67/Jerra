package jerra.item;

import java.io.Serializable;

/**
 * This is the abstract Item class. This must be extended by a concrete, in-game
 * item class. An item could be but is not limited to food, or a weapon. Let's
 * say we have drinks like: Caffeine or HalfAndHalf which might speed a player
 * up or slow a player down, respectively.
 */
public abstract class Item implements Serializable {

    private static final long serialVersionUID = 0;

    /**
     * Implementation for checking if an item equals another item.
     * Here, the class for both items are just compared;
     * and are equal when they come from the same class.
     * 
     * @param item The Item to compare to. 
     * @return a boolean, true if the given object is of the same class
     */    
    @Override
    public boolean equals(Object item) {
        return this.hashCode() == item.hashCode();
    }

    /**
     * Overrides hashCode implementation to return the hashcode of the class.
     * This allows multiple instances of the same type of Item to have the same hash.
     * @return a int, the hashCode of the class
     */
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    /**
     * Set the name of a concrete item by overriding this method and returning
     * the intended item name.
     */
    abstract public String name();

    /**
     * Set the description of a concrete item by overriding this method and returning
     * the intended item description.
     */
    abstract public String description();

}