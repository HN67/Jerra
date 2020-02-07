import java.util.List;
import java.util.ArrayList;

/**
 * TextRoom
 */
public class TextRoom implements InterfaceRoom {

    private List<Entity> entityList;

    public TextRoom() {
        this.entityList = new ArrayList<Entity>();
    }

    public void add(Entity entity) {
        this.entityList.add(entity);
    }

    public void update(String command) {
        for (Entity entity: this.entityList) {
            entity.update(command);
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