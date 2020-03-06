package jerra.entity;

import jerra.core.Vector;

/**
 * Ambient Spawner, an object that periodically spawns Entities around its position.
 */
public class AmbientSpawner extends BaseAmbient implements Spawner {
    
	private Entity entity;

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area. Specifically, the origin coordinate
     * of the spawned entity is <= origin -+ range.
     * @param period, a int, the Entity is spawned every this many ticks
     */
    public AmbientSpawner(Entity entity, Vector origin, int range, int period) {
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
    public Entity spawn() {
        // Copy entity
        Entity newEntity = this.entity.copy();
        // Place new entity
        super.place(newEntity);
        // Return entity
    	return newEntity;
    }
    
}
