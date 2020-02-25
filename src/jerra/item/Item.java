package jerra.item;

/** 
 * This is the abstract Item class. This must be extended by a concrete,
 * in-game item class. An item could be but is not limited to food, or a
 * weapon. Let's say we have drinks like: Caffeine or HalfAndHalf which might 
 * speed a player up or slow a player down, respectively.
 */
abstract class Item {
    /**
     * Implementation for checking if an item equals another item.
     * Here, the class for both items are just compared;
     * and are equal when they come from the same class.
     * 
     * @param item The item to compare to. 
     * @return boolean
     */    
    @Override
    public boolean equals(Object item) {
        return this.getClass() == item.getClass();
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