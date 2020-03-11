package jerra.entity;

import java.lang.Math;

import javafx.scene.image.Image;

import jerra.core.Vector;
import jerra.presence.Presence;
import jerra.stats.Stats;

/**
 * DefaultEntity with a Gun that fires the Gun as often as possible.
 * Bullets are shot in the current velocity direction of the Shooter
 */
public class ShooterEntity extends DefaultCharacter implements Shooter {

    private static final long serialVersionUID = 0;

    private Gun gun;

    public ShooterEntity(Presence presence, Stats stats, Gun gun, char team, Image image) {
        super(presence, stats, image);
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
        presence.setPosition(this.getPosition().center().add(-presence.getPosition().width()/2 + x*this.getPosition().width()/2, -presence.getPosition().height()/2 + y*this.getPosition().height()/2));
        presence.setVelocity(presence.getVelocity().scale(new Vector(x, y)));
        bullet.setPresence(presence);
        return bullet;
    }

    @Override
    public Shooter copy() {
        return new ShooterEntity(this.getPresence().copy(), this.getStats().copy(), this.gun.copy(), this.getTeam(), this.image());
    }
    
}