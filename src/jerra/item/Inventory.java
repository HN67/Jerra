package jerra.item;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Inventory class, for tracking quantities of Items
 */
public class Inventory {

    // Map the links an Item to its count
    private Map<Item, Integer> inventory = new HashMap<Item, Integer>();

    /**
     * Creates an Inventory, capable of storing Items
     */
    public Inventory() {
        this.inventory = new HashMap<Item, Integer>();
    }

    /**
     * Adds an amount of an Item to this Inventory.
     * If the Item is not already in the inventory, it will be initalized with the count.
     * @param item a Item, the item to add to the inventory. Items are differentiated by class.
     * @param count a int, the number to increase the counter of the Item by.
     */
    public void add(Item item, int count) {

        // Check if the item is already in the inventory
        if (this.inventory.containsKey(item)) {
            // Increment the value
            this.inventory.put(item, this.inventory.get(item) + count);
        } else {
            // Set the value
            this.inventory.put(item, count);
        }

    }

    /**
     * Adds a single Item to this Inventory.
     * @param item a Item, the item to add to this Inventory.
     */
    public void add(Item item) {
        this.add(item, 1);
    }
    
    /**
     * Iteratively add the contents of the other Inventory to this Inventory
     * @param other a Inventory, will have its contents copied into this one
     */
    public void add(Inventory other) {
        for (Item item: other.items()) {
            this.add(item, other.count(item));
        }
    }

    /**
     * Returns a set of the Items in this inventory.
     * Some Items may have a 0 count.
     * Useful for iteration.
     * @return a Set<Item>, the items in this Inventory.
     */
    public Set<Item> items() {
        return this.inventory.keySet();
    }

    /**
     * Returns the count associated with a given Item
     * @param item a Item, the item to be checked for
     * @return a int, the number of the Item in this Inventory
     */
    public int count(Item item) {
        // Check if the Item is tracked at all
        if (this.inventory.containsKey(item)) {
            return this.inventory.get(item);
        // Default to 0
        } else {
            return 0;
        }
    }

    /**
     * Attempts an amount of an Item from this Inventory.
     * Only affects the Inventory if there are enough to remove the requested amount.
     * @param item a Item, the item to remove
     * @param count a int, the amount to remove
     * @return a boolean, true if and only if items were removed
     */
    public boolean remove(Item item, int count) {
        if (this.inventory.containsKey(item) && this.inventory.get(item) >= count) {
            this.add(item, -count);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Attempts to remove a single Item from this Inventory.
     * Only removes the Item if there is at least one in the inventory
     * @param item a Item, to be removed from the inventory
     * @return a boolean, true if and only if the Item was removed
     */
    public boolean remove(Item item) {
        return this.remove(item, 1);
    }

    /**
     * Clears all items from this Inventory.
     * Causes .count to return 0 for any item.
     */
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public String toString() {
        return this.inventory.toString();
    }

}
