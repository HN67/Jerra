package jerra.entity;

import java.lang.Math;

import jerra.core.Vector;

import jerra.presence.Presence;

/**
 * Ambient Spawner, an object that periodically spawns Entities around its position.
 */
public class AmbientSpawner implements Spawner {
    
    private int period;
	private int tick;
	private Entity entity;
	private Vector origin;
	private int range;

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area. Specifically, the origin coordinate
     * of the spawned entity is <= origin -+ range.
     * @param period, a int, the Entity is spawned every this many ticks
     */
    public AmbientSpawner(Entity entity, Vector origin, int range, int period) {
        // Save origin and range and entity
    	this.entity = entity;
    	this.origin = origin;
        this.range = range;
        // Initalize tick and period
        this.tick = 0;
        this.period = period;
    }

    @Override
    public boolean spawns() {
        // Count the number of ticks, return true every period amount
    	this.tick += 1;
    	if (this.tick >= period) {
    		this.tick = 0;
    		return true;
    	} else {
            return false;
        }
    }

    @Override
    public Entity spawn() {
        // Generate random offsets
        // Determine negative or not for y offset
    	int negative = (int)(Math.random()*(2));
    	int y;
    	if (negative == 0) {
            // Non negative, just generate up to range + 1 exclusive
    		y = (int)(Math.random()*(this.range+1));
    	} else {
            // Negate generation result
    		y = (int)(Math.random()*(this.range+1))*-1;
        }
        // Determine negative or not for x offset
    	negative = (int)(Math.random()*(2));
    	int x;
    	if (negative == 0) {
            // Non negative, just generate up to range + 1 exclusive
    		x = (int)(Math.random()*(this.range+1));
    	} else {
            // Negate generation result
    		x = (int)(Math.random()*(this.range+1))*-1;
        }
        // Copy entity
        Entity newEntity = this.entity.copy();
        // Set position to determined offset of origin
        Presence newPresence = newEntity.getPresence();
        // It would be convenient if setPosition returned a Presence so we could oneline, but this works
        newPresence.setPosition(this.origin.add(x, y));
        newEntity.setPresence(newPresence);
    	return newEntity;
    }

    @Override
    public boolean alive() {
        return true;
    }
    
}
