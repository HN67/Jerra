package jerra.entity;

import jerra.core.Vector;
import jerra.presence.Presence;
import jerra.stats.Stats;
import jerra.item.Inventory;
import jerra.item.Item;
import jerra.item.Loot;
import jerra.item.Medkit;

/**
 * Player
 */
public class Player extends DefaultCharacter implements Shooter, Loot {

    private static final long serialVersionUID = 0;

    private static Medkit medkitRef = new Medkit();

    private Vector direction;
    private Gun gun;
    private Inventory inventory;

    private static final String[] xDirectionNames = {"LEFT", "", "RIGHT"};
    private static final String[] yDirectionNames = {"UP", "", "DOWN"};

    public Player(Presence presence, Stats stats, Gun gun, Inventory inventory, char team, Vector direction, String image) {
        super(presence, stats, image);
        this.setDirection(direction);
        this.setTeam(team);
        this.gun = gun;
        this.inventory = inventory;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        for (String command: this.commandQueue()) {
            if (command.equals("upSecondary")) {
                this.direction = new Vector(0, -1);
            } else if (command.equals("downSecondary")) {
                this.direction = new Vector(0, 1);
            } else if (command.equals("leftSecondary")) {
                this.direction =  new Vector(-1, 0);
            } else if (command.equals("rightSecondary")) {
                this.direction = new Vector(1, 0);
            } else if (command.equals("use")) {
                if (
                    this.inventory.count(medkitRef) > 0 &&
                    this.getStats().getValue(Stats.Type.HEALTH) < this.getStats().getValue(Stats.Type.VITALITY)
                ) {
                    medkitRef.apply(this);
                    this.inventory.remove(medkitRef);
                }
            } else {
            }
        }

        // Update gun
        this.gun.update();

        // Call super update (updates Presence and clears queue)
        super.update();
    }

    @Override
    public String getName() {
        // Indicate direction in name
        return "PLAYER (" + this.getStats().getValue(Stats.Type.HEALTH) + ", " + this.getDirectionString() + ")";
    }

    public String getDirectionString() {
        return yDirectionNames[this.direction.y()+1] + xDirectionNames[this.direction.x()+1];
    }

    @Override
    public String symbol() {
        return "P";
    }

    /**
     * Returns a new Entity, shot in the direction the Player is facing
     * @return A Entity, that has not been added to any room
     */
    @Override
    public Entity spawn() {
        // Get new bullet from gun
        Entity bullet = this.gun.spawn();
        Presence presence = bullet.getPresence().copy();
        presence.setPosition(this.getPosition().center().add(-presence.getPosition().width()/2 + this.direction.x()*this.getPosition().width()/2, -presence.getPosition().height()/2 + this.direction.y()*this.getPosition().height()/2));
        Vector velocity = presence.getVelocity();
        // Set Projectile velocity based on facing direction
        velocity = velocity.scale(this.direction);
        // Update and return new bullet
        presence.setVelocity(velocity);
        bullet.setPresence(presence);
        return bullet;

    }

    /**
     * Checks a command to see if the Player should spawn an entity (using .spawn)
     * @return A boolean representing whether the Player should spawn an entity
     */
    public boolean spawns() {
        // Only spawns if gun is ready
        if (this.gun.spawns()) {
            // Check for appropriate command
            for (String command: this.commandQueue()) {
                if (command.equals("shoot")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public Player setInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    @Override
    public void interact(Loot other) {
        // Collect items from other inventory
        for (Item item: other.getInventory().items()) {
            // Can only carry 1 medkit
            if (item.equals(medkitRef)) {
                if (other.getInventory().count(medkitRef) > 0 && this.inventory.count(medkitRef) <= 0) {
                    // Set inventory to 1 medkit
                    this.inventory.add(medkitRef, -this.inventory.count(medkitRef) + 1);
                    // Remove one medkit
                    other.getInventory().remove(medkitRef);
                }
            } else {
                this.inventory.add(item, other.getInventory().count(item));
                other.getInventory().remove(item, other.getInventory().count(item));
            }
        }
    }

    @Override
    public Shooter copy() {
        return new Player(this.getPresence().copy(), this.getStats().copy(), this.gun.copy(), this.inventory.copy(), this.getTeam(), this.direction, this.image());
    }

}
