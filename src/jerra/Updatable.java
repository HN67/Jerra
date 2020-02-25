package jerra;

/**
 * Updatable interface, guarentees that an object can be updated
 * @author Ryan Allard
 */
public interface Updatable {

    /**
     * Updates the Updatable using a String, likely retrieved from a console
     * @param command
     */
    public void update(String command);
    
}
