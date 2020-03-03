package jerra.entity;

import jerra.core.Rect;
import jerra.core.Vector;
import jerra.presence.DefaultPresence;
import jerra.presence.Presence;

/**
 * Bullet
 */
public class Bullet extends Projectile {

    /**
     * Constructs a Presence based on position and velocity
     * Bullets have a fixed lifetime
     */
    private static Presence constructPresence(Rect position, Vector velocity) {
        return new DefaultPresence(position, velocity);
    }

    public Bullet(Rect position, Vector velocity, int lifetime) {
        // Create projectile with predetermined presence
        super(constructPresence(position, velocity), lifetime);
    }

    @Override
    public Entity copy() {
        return new Bullet(this.getPosition(), this.getPresence().getVelocity(), this.lifetime);
    }

    @Override
    public String getName() {
        return "Bullet (" + this.age + ")";
    }

    @Override
    public void interact(Entity other) {
        // Kill other entity
        other.kill(true);
        // Kill this (remove for penetrating)
        this.kill(true);
    }

}
