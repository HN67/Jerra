package jerra.entity;

import jerra.core.Vector;
import jerra.presence.Presence;

/**
 * Player
 */
public class Player extends DefaultEntity implements Spawner {

    private String direction;
    private Projectile bullet;

    public Player(Presence presence, Projectile bullet, String direction) {
        super(presence);
        this.setDirection(direction);
        this.bullet = bullet;
    }

    public Player(Presence presence, Projectile bullet) {
        this(presence, bullet, "UP");
    }

    public void setDirection(String direction) {
        this.direction = new String(direction);
    }

    @Override
    public void update() {
        for (String command: this.commandQueue()) {
            if (command.equals("up")) {
                this.direction = "UP";
            } else if (command.equals("down")) {
                this.direction = "DOWN";
            } else if (command.equals("left")) {
                this.direction = "LEFT";
            } else if (command.equals("right")) {
                this.direction = "RIGHT";
            } else {
            }
        }

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
        // Prepare new bullet
        Entity bullet = this.bullet.copy();
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
        for (String command: this.commandQueue()) {
            if (command.equals("shoot")) {
                return true;
            }
        }
        return false;
    }

}
