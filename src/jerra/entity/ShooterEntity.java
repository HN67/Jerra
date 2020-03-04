package jerra.entity;

import jerra.core.Vector;
import jerra.presence.Presence;

/**
 * DefaultEntity with a Gun that fires the Gun as often as possible.
 * Bullets are shot in the current velocity direction of the Shooter
 */
public class ShooterEntity extends DefaultEntity implements Shooter {

    private Gun gun;

    public ShooterEntity(Presence presence, Gun gun) {
        super(presence);
        this.gun = gun;
    }

    @Override
    public void update() {
        super.update();
        this.gun.update();
    }

    @Override
    public boolean spawns() {
        return this.gun.spawns() && !this.getPresence().getVelocity().equals(new Vector(0, 0));
    }

    @Override
    public Entity spawn() {
        Entity bullet = this.gun.spawn();
        Presence presence = bullet.getPresence().copy();
        presence.setPosition(this.getPosition().getOrigin());
        presence.setVelocity(presence.getVelocity().scale(this.getPresence().getVelocity().sign()));
        bullet.setPresence(presence);
        return bullet;
    }

    @Override
    public Shooter copy() {
        return new ShooterEntity(this.getPresence().copy(), this.gun.copy());
    }
    
}