import java.util.List;
import java.util.ArrayList;

/**
 * TextRoom
 */
public class TextRoom implements Room {

    // EntityList should contain *all* entities
    private List<Entity> entityList;
    // Sublists are for finer control
    private List<Spawner> spawners;

    public TextRoom() {
        this.entityList = new ArrayList<Entity>();
        this.spawners = new ArrayList<Spawner>();
    }

    public void spawnEntity(Entity entity) {
        this.entityList.add(entity);
    }

    public void spawnPlayer(Player player) {
        // Adding to entityList allows bulk updating
        this.entityList.add(player);
        // Registers the player as a spawner (for shooting)
        this.spawners.add(player);
    }

    public void update(String command) {
        // Update all general entities
        for (Entity entity: this.entityList) {
            entity.update(command);
        }
        // Remove dead entities
        this.entityList.removeIf(entity -> !entity.alive());
        // Check players
        for (Spawner spawner: this.spawners) {
            // Get spawned Entity if Spawner spawns
            if (spawner.spawns(command)) {
                this.spawnEntity(spawner.spawn());
            }
        }
    }

    public String toString() {
        String output = "";
        for (Entity entity: this.entityList) {
            output += entity.toString() + "\n";
        }
        return output;
    }

    
}