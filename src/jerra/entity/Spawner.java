package jerra.entity;

/**
 * Spawner Interface
 * Represents something that has the capability to spawn new objects
 * @author Ryan Allard
 */
public interface Spawner {

    /**
     * Describes whether the Spawner would currently spawn a <Type>
     * May be based on current command queue.
     * @return a boolean representing whether the Spawner wants to spawn an <Type>
     */
    public boolean spawns();
    /**
     * Returns a new <Type> 'spawned' (created) by the Spawner
     * @return The new <Type>
     */
    public Entity spawn();

}
