package jerra.item;

import java.io.Serializable;

import jerra.api.Updatable;

import jerra.presence.Presence;


/**
 * Loot object interface
 * Represents a combination of a physical entity and an inventory
 * @author Ryan Allard
 */
public interface Loot extends Updatable, Serializable {

    /**
     * Gets a view of the Loot's current inventory
     * @return A Inventory representing the Loot's contents
     */
    public Inventory getInventory();

    /**
     * Returns the Presence of the Loot
     * @return A Presence representing the Loot's physical presence
     */
    public Presence getPresence();

    /**
     * Returns a String representing the state of the Loot
     * @return A String, for textbased output
     */
    public String toString();
    
}
