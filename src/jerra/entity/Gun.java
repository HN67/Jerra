package jerra.entity;

import jerra.api.Copyable;
import jerra.api.Updatable;

/**
 * Gun class, designed to be a bullet spawner
 */
public class Gun implements Spawner<Entity>, Updatable, Copyable<Gun> {

    private Entity bullet;
    private int reload;

    private int cooldown;
    
    /**
     * Creates a Gun that spawns the given bullet with a given reload time
     * @param bullet a Entity, returned by the spawn method
     * @param reload a int, the number of updates that .spawns will return false after spawning
     */
    public Gun(Entity bullet, int reload) {

        this.bullet = bullet;
        this.reload = reload;

        this.cooldown = 0;

    }

    @Override
    public void update() {
        if (this.cooldown > 0) {
            this.cooldown -= 1;
        }
    }

    @Override
    public boolean spawns() {
        return this.cooldown <= 0;
    }

    @Override
    public Entity spawn() {
        this.cooldown = this.reload;
        return this.bullet.copy();
    }

    @Override
    public Gun copy() {
        Gun out = new Gun(this.bullet.copy(), this.reload);
        out.cooldown = this.cooldown;
        return out;
    }
    
}
