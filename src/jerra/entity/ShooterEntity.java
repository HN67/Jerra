package jerra.entity;

import java.lang.Math;

import jerra.core.Vector;
import jerra.presence.Presence;

/**
 * DefaultEntity with a Gun that fires the Gun as often as possible.
 * Bullets are shot in the current velocity direction of the Shooter
 */
public class ShooterEntity extends DefaultEntity implements Shooter {

    private Gun gun;

    public ShooterEntity(Presence presence, Gun gun, char team) {
        super(presence);
        this.gun = gun;
        this.setTeam(team);
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
        // Generate random direction
        // Choose between x direction (1) and y direction (0)
        int y = 0;
        int x = (int) (Math.random()*2);
        // y direction
        if (x == 0) {
            // x is already 0, generate y
            // Generate -1 or 1
            y = ((int) (Math.random()*2))*2 - 1;
        // x direction
        } else if (x == 1) {
            // y is already 0, generate x
            x = ((int) (Math.random()*2))*2 - 1;
        }
        presence.setVelocity(presence.getVelocity().scale(new Vector(x, y)));
        bullet.setPresence(presence);
        return bullet;
    }

    @Override
    public Shooter copy() {
        return new ShooterEntity(this.getPresence().copy(), this.gun.copy(), this.getTeam());
    }
    
}