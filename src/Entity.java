/**
 * Entity
 */
public interface Entity extends Updatable, Copyable<Entity> {

    public Presence getPresence();
    public void setPresence(Presence presence);

    public Rect getPosition();

    public boolean collides(Entity other);
    
    public void interact(Entity other, String command);

    @Override
    public void update(String command);
    @Override
    public String toString();

    @Override
    public Entity copy();

    public boolean alive();
    public void kill(boolean dead);
    
}