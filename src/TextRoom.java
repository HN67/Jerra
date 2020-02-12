import java.util.List;
import java.util.ArrayList;

/**
 * TextRoom
 */
public class TextRoom implements Room {

    // EntityList should contain *all* entities
    private List<Entity> entityList;
    // Sublists are for finer control
    private List<Player> players;

    public TextRoom() {
        this.entityList = new ArrayList<Entity>();
        this.players = new ArrayList<Player>();
    }

    public void addEntity(Entity entity) {
        this.entityList.add(entity);
    }

    public void addPlayer(Player player) {
        // Adding to entityList allows bulk updating
        this.entityList.add(player);
        // Adding to players allows specific control, like shooting
        this.players.add(player);
    }

    public void update(String command) {
        // Update all general entities
        for (Entity entity: this.entityList) {
            entity.update(command);
        }
        // Remove dead entities
        this.entityList.removeIf(entity -> !entity.alive());
        // Check players
        for (Player player: this.players) {
            // Get shot projectile if player shoots
            if (player.shoots(command)) {
                this.addEntity(player.shoot());
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