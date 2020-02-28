package jerra.room;

import java.util.List;

import jerra.api.Updatable;
import jerra.api.Interactive;

import jerra.entity.Entity;
import jerra.entity.Spawner;
import jerra.entity.Player;

/**
 * Room
 */
public interface Room extends Updatable, Interactive {

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
    
    public List<Entity> getEntities();

    @Override
    public String toString();

}