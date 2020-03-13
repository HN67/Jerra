package jerra.entity;

import jerra.core.Vector;

/**
 * AmbientShooterSpawner
 */
public class AmbientShooterSpawner extends BaseAmbient implements Spawner<Shooter> {

    private static final long serialVersionUID = 0L;

    private Shooter entity;

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area. Specifically, the origin coordinate
     * of the spawned entity is <= origin -+ range.
     * @param period, a int, the Entity is spawned every this many ticks
     */
    public AmbientShooterSpawner(Shooter entity, Vector origin, int range, int period) {
        // Call super constructor
        super(origin, range, period);
        // Save entity
    	this.entity = entity;
    }

    @Override
    public boolean spawns() {
        // Use super tick
        return super.tick();
    }

    @Override
    public Shooter spawn() {
        // Copy entity
        Shooter newEntity = this.entity.copy();
        // Place new entity
        super.place(newEntity);
        // Return entity
    	return newEntity;
    }
    
}