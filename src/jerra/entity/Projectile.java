package jerra.entity;

import jerra.presence.Presence;

/**
 * Projectile
 */
public class Projectile extends DefaultEntity {

    protected int lifetime;
    protected int age;

    public Projectile(Presence presence, int lifetime) {
        // Call super constructor
        super(presence);
        // Reference lifetime
        this.lifetime = lifetime;

        // Initalizie age
        this.age = this.lifetime;
    }

    @Override
    public String getName() {
        return "PROJECTILE (" + this.age + ")";
    }

    @Override
    public String symbol() {
        return "B";
    }

    @Override
    public void update() {
        this.age -= 1;
        this.kill(this.age <= 0);
        super.update();
    }

}
