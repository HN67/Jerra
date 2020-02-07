/**
 * Room
 */
public interface InterfaceRoom {

    // private List<Entity>

    public void update(String command);
    public void add(Entity entity);

    @Override
    public String toString();

    

}