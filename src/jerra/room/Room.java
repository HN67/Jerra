package jerra.room;

import jerra.entity.Entity;
import jerra.entity.Spawner;
import jerra.entity.Player;

/**
 * Room
 */
public interface Room {

    // private List<Entity>

    public void update(String command);

    /**
     * Spawns (inserts) a general Entity in the Room
     * @param entity The Entity that the Room will now track (as a general Entity)
     */
    public void spawnEntity(Entity entity);
    /**
     * Spawns (inserts) a general Spawner in the Room
     * @param spawner The Spawner that the Room will now tack (as a general Spawner)
     */
    public void spawnSpawner(Spawner spawner);
    /**
     * Spawns (inserts) a Player in the Room, allowing the Room to have finer control
     * @param player The Player that the Room will now track (as a Player)
     */
    public void spawnPlayer(Player player);

    @Override
    public String toString();

    public String gridString();

}