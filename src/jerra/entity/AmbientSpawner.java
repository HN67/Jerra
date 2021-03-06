package jerra.entity;

import java.util.Random;

import jerra.api.Physical;
import jerra.api.Copyable;

import jerra.core.Vector;

/**
 * Ambient Spawner, an object that periodically spawns Entities around its position.
 */
public class AmbientSpawner<Spawnable extends Physical & Copyable<Spawnable>> extends BaseAmbient implements Spawner<Spawnable> {
    
    private static final long serialVersionUID = 0L;

	private Spawnable entity;

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area. Specifically, the origin coordinate
     * of the spawned entity is <= origin -+ range.
     * @param period, a int, the Entity is spawned every this many ticks
     */
    public AmbientSpawner(Spawnable entity, Vector origin, int range, int period, Random generator) {
        // Call super constructor
        super(origin, range, period, generator);
        // Save entity
    	this.entity = entity;
    }

    @Override
    public boolean spawns() {
        // Use super tick
        return super.tick();
    }

    @Override
    public Spawnable spawn() {
        // Copy entity
        Spawnable newEntity = this.entity.copy();
        // Place new entity
        super.place(newEntity);
        // Return entity
    	return newEntity;
    }
    
}
