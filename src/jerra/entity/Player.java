package jerra.entity;

import jerra.core.Vector;
import jerra.presence.Presence;

/**
 * Player
 */
public class Player extends DefaultEntity implements Spawner<Entity> {

    private String direction;
    private Gun gun;

    public Player(Presence presence, Gun gun, String direction) {
        super(presence);
        this.setDirection(direction);
        this.gun = gun;
    }

    public Player(Presence presence, Gun gun) {
        this(presence, gun, "UP");
    }

    public void setDirection(String direction) {
        this.direction = new String(direction);
    }

    @Override
    public void update() {
        for (String command: this.commandQueue()) {
            if (command.equals("upSecondary")) {
                this.direction = "UP";
            } else if (command.equals("downSecondary")) {
                this.direction = "DOWN";
            } else if (command.equals("leftSecondary")) {
                this.direction = "LEFT";
            } else if (command.equals("rightSecondary")) {
                this.direction = "RIGHT";
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
        return "PLAYER (" + this.direction + ")";
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
        presence.setPosition(this.getPosition().getOrigin());
        Vector velocity = presence.getVelocity();
        // Set Projectile velocity based on facing direction
        if (this.direction.equals("UP")) {
            velocity = velocity.scale(0, -1);
        }
        if (this.direction.equals("DOWN")) {
            velocity = velocity.scale(0, 1);
        }
        if (this.direction.equals("RIGHT")) {
            velocity = velocity.scale(1, 0);
        }
        if (this.direction.equals("LEFT")) {
            velocity = velocity.scale(-1, 0);
        }
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

}
