package jerra.entity;

import jerra.api.Interactive;

/**
 * Spawner Interface
 * Represents something that has the capability to spawn an Entity
 * @author Ryan Allard
 */
public interface Spawner extends Interactive {

    /**
     * Describes whether the Spawner would currently spawn an Entity
     * May be based on current command queue.
     * @return a boolean representing whether the Spawner wants to spawn an Entity
     */
    public boolean spawns();
    /**
     * Returns a new Entity 'spawned' (created) by the Spawner
     * @return The new Entity
     */
    public Entity spawn();

    public boolean alive();

}
