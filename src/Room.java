/**
 * Room
 */
public interface Room {

    // private List<Entity>

    public void update(String command);
    public void addEntity(Entity entity);
    public void addPlayer(Player player);

    @Override
    public String toString();

    

}