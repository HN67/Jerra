package jerra.entity;

import java.io.Serializable;

import jerra.api.Physical;

import jerra.core.Vector;

import jerra.presence.Presence;

/**
 * Base ambient class contains helper methods for implementing an ambient spawner
 */
public class BaseAmbient implements Serializable {

    private static final long serialVersionUID = 0L;

    private int period;
	private int tick;
	private Vector origin;
	private int range;

    public BaseAmbient(Vector origin, int range, int period) {
        // Save origin and range
    	this.origin = origin;
        this.range = range;
        // Initalize tick and period
        this.tick = 0;
        this.period = period;
    }

    public boolean tick() {
        // Count the number of ticks, return true every period amount
    	this.tick += 1;
    	if (this.tick >= period) {
    		this.tick = 0;
    		return true;
    	} else {
            return false;
        }
    }

    /**
     * Modifies the position of the physical in place to a random location within the range of the ambient
     * @param physical a Physical, will have its position modified inplace
     * @return the same Physical that was passed, allows for potential chaining
     */
    public Physical place(Physical physical) {
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
        // Set position to determined offset of origin
        Presence newPresence = physical.getPresence();
        // It would be convenient if setPosition returned a Presence so we could oneline, but this works
        newPresence.setPosition(this.origin.add(x, y));
        physical.setPresence(newPresence);
        // Return the physical again
        return physical;
    }
    
}