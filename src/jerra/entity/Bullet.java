package jerra.entity;

import jerra.core.Rect;
import jerra.core.Vector;
import jerra.presence.DefaultPresence;
import jerra.presence.Presence;

import jerra.effect.Effect;

/**
 * Bullet
 */
public class Bullet extends Projectile {

    private static final long serialVersionUID = 0;

    // Allows for any Effect that can be applied to an Entity
    private Effect<? super Entity> effect;

    /**
     * Constructs a Presence based on position and velocity
     * Bullets have a fixed lifetime
     */
    private static Presence constructPresence(Rect position, Vector velocity) {
        return new DefaultPresence(position, velocity);
    }

    public Bullet(Rect position, Vector velocity, Effect<? super Entity> effect, int lifetime, char team, String image) {
        // Create projectile with predetermined presence
        super(constructPresence(position, velocity), lifetime, image);
        this.setTeam(team);
        this.effect = effect;
    }

    @Override
    public Entity copy() {
        return new Bullet(this.getPosition(), this.getPresence().getVelocity(), this.effect.copy(), this.lifetime, this.getTeam(), this.image());
    }

    @Override
    public String getName() {
        return "Bullet (" + this.age + ")";
    }

    @Override
    public void interact(Entity other) {
        // Only interact if not friendly
        if (!this.friendly(other)) {
            this.effect.apply(other);
            // Kill other entity
            // other.kill(true);
            // Kill this (remove for penetrating)
            this.kill(true);
        }
    }

}
