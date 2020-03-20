package jerra.item;

import jerra.presence.Presence;
import jerra.core.Rect;
import jerra.api.Physical;
import jerra.api.Mortal;

/**
 * Lootbag
 */
public class Lootbag implements Loot, Mortal {

    private static final long serialVersionUID = 0L;

    private Presence presence;
    private Inventory inventory;
    private String image;

    public Lootbag(Presence presence, Inventory inventory, String image) {
        this.presence = presence;
        this.inventory = inventory;
        this.image = image;
    }

    @Override
    public Presence getPresence() {
        return this.presence;
    }

    @Override
    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    @Override
    public boolean collides(Physical other) {
        return this.getPresence().collides(other.getPresence());
    }

    @Override
    public void deflect(Physical other) {
        ;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public Lootbag setInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    @Override
    public void interact(Loot other) {
        ;
    }

    @Override
    public boolean alive() {
        return !this.inventory.empty();
    }

    @Override
    public void kill(boolean dead) {
        if (dead) {
            this.inventory.clear();
        }
    }

    @Override
    public String image() {
        return this.image;
    }

    @Override
    public String symbol() {
        return "L";
    }

    @Override
    public Rect getPosition() {
        return this.getPresence().getPosition();
    }
    
}
