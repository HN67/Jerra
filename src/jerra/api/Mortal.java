package jerra.api;

import java.io.Serializable;

/**
 * Interface to specify that an object is Mortal. A Mortal object has an alive
 * method that returns a boolean, and a kill method which takes a boolean. Sane
 * Mortal objects will have their alive method respect kill calls, so calling
 * kill(true) should cause a directly subsequent alive to return false.
 */
public interface Mortal extends Serializable {

    /**
     * Indicates whether the object is 'alive'.
     * Dead objects may often be removed from collections or trackers.
     * @return a boolean, true if the object is alive
     */
    public boolean alive();

    /**
     * Updates the aliveness of the object.
     * A well-implemented Mortal will have alive return !dead when called directly after kill.
     * @param dead a boolean, true to 'kill' the Mortal.
     */
    public void kill(boolean dead);
    
}
