package jerra.entity;

import jerra.core.Vector;

import jerra.presence.Presence;

/**
 * Player
 */
public class Player extends DefaultEntity implements Spawner {

    private String direction;

    public Player(Presence presence) {
        super(presence);
        this.direction = "UP";
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
    public Projectile spawn() {
        // Make sure to delink position vectors
        Vector position = new Vector(this.getPosition().getOrigin());
        // Set Projectile velocity based on facing direction
        if (this.direction.equals("UP")) {
            return new Bullet(position, new Vector(0, -1));
        }
        if (this.direction.equals("DOWN")) {
            return new Bullet(position, new Vector(0, 1));
        }
        if (this.direction.equals("RIGHT")) {
            return new Bullet(position, new Vector(1, 0));
        }
        if (this.direction.equals("LEFT")) {
            return new Bullet(position, new Vector(-1, 0));
        }
        // If somehow there is no direction, just drop the projectile
        return new Bullet(position, new Vector(0, 0));
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
