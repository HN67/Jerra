package jerra.item;

import jerra.api.Physical;
import jerra.api.Visual;

/**
 * Loot object interface
 * Represents a combination of a physical entity and an inventory
 * @author Ryan Allard
 */
public interface Loot extends Physical, Visual {

    /**
     * Gets a view of the Loot's current inventory
     * @return A Inventory representing the Loot's contents
     */
    public Inventory getInventory();

    /**
     * Sets the Loots inventory to a new object
     * @param inventory a Inventory, to be tracked by the Loot
     * @return this Loot, allows chaining
     */
    public Loot setInventory(Inventory inventory);

    /**
     * Interacts with another Loot
     * @param other a Loot, to be interacted with
     */
    public void interact(Loot other);
    
}
