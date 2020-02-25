package jerra;

/**
 * Spawner Interface
 * Represents something that has the capability to spawn an Entity
 * @author Ryan Allard
 */
public interface Spawner {

    /**
     * Describes whether the Spawner would currently spawn an Entity
     * @param command Command string that can be used to update Spawner state
     * @return a boolean representing whether the Spawner wants to spawn an Entity
     */
    public boolean spawns(String command);
    /**
     * Returns a new Entity 'spawned' (created) by the Spawner
     * @return The new Entity
     */
    public Entity spawn();

    public boolean alive();

}
