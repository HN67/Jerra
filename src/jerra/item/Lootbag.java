package jerra.item;

import javafx.scene.image.Image;

import jerra.presence.Presence;
import jerra.core.Rect;
import jerra.api.Physical;
import jerra.api.Mortal;

/**
 * Lootbag
 */
public class Lootbag implements Loot, Mortal {

    private Presence presence;
    private Inventory inventory;
    private Image image;

    public Lootbag(Presence presence, Inventory inventory, Image image) {
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
    public Image image() {
        return this.image;
    }

    @Override
    public Rect getPosition() {
        return this.getPresence().getPosition();
    }
    
}
