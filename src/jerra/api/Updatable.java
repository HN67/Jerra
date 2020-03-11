package jerra.api;

import java.io.Serializable;

/**
 * Updatable interface, guarentees that an object can be updated
 * 
 * @author Ryan Allard
 */
public interface Updatable extends Serializable {

    /**
     * Updates the Updatable
     */
    public void update();
    
}
