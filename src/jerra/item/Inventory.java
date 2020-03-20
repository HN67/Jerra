package jerra.item;

import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import jerra.api.Copyable;

/**
 * Inventory class, for tracking quantities of Items
 */
public class Inventory implements Copyable<Inventory>, Serializable {

    private static final long serialVersionUID = 0L;

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
     * @return this Inventory
     */
    public Inventory add(Item item, int count) {

        // Check if the item is already in the inventory
        if (this.inventory.containsKey(item)) {
            // Increment the value
            this.inventory.put(item, this.inventory.get(item) + count);
        } else {
            // Set the value
            this.inventory.put(item, count);
        }
        return this;

    }

    /**
     * Adds a single Item to this Inventory.
     * @param item a Item, the item to add to this Inventory.
     * @return this Inventory
     */
    public Inventory add(Item item) {
        return this.add(item, 1);
    }
    
    /**
     * Iteratively add the contents of the other Inventory to this Inventory
     * @param other a Inventory, will have its contents copied into this one
     * @return this Inventory
     */
    public Inventory add(Inventory other) {
        for (Item item: other.items()) {
            this.add(item, other.count(item));
        }
        return this;
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
    public Inventory clear() {
        this.inventory.clear();
        return this;
    }

    /**
     * Checks if all Items have a zero count
     * Note: If potentially there is a negative amount, this counts as non-zero
     * @return a boolean, true if there are no items in the inventory, false if there are any.
     */
    public boolean empty() {
        // Look for non zero entries, return false on finding one
        for (Map.Entry<Item, Integer> entry: this.inventory.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        // Return true if no non zero found
        return true;
    }

    @Override
    public Inventory copy() {
        return new Inventory().add(this);
    }

    @Override
    public String toString() {
        return this.inventory.toString();
    }

}
