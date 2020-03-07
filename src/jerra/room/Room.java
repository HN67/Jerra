package jerra.room;

import java.util.Set;

import jerra.api.Interactive;
import jerra.api.Updatable;
import jerra.entity.Entity;
import jerra.entity.Player;
import jerra.entity.ShooterEntity;
import jerra.entity.Spawner;

/**
 * Room
 */
public interface Room extends Updatable, Interactive {

    /**
     * Spawns (inserts) a general Entity in the Room
     * @param entity The Entity that the Room will now track (as a general Entity)
     */
    public void spawn(Entity entity);
    /**
     * Spawns (inserts) a general Spawner in the Room
     * @param spawner The Spawner that the Room will now track (as a general Spawner)
     */
    public void spawn(Spawner spawner);

    /**
     * Spawns (inserts) a Player in the Room, allowing the Room to have finer control
     * @param player The Player that the Room will now track (as a Player)
     */
    public void spawn(Player player);

    /**
     * Spawns (inserts) a ShooterEntity in the Room
     * @param player The ShooterEntity that the Room will now track (as a ShooterEntity)
     */
    public void spawn(ShooterEntity se);
    
    public Set<Entity> getEntities();

    @Override
    public String toString();

}