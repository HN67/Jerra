/**
 * Entity
 */
public interface Entity extends Updatable {

    public Presence getPresence();
    public void setPresence(Presence presence);

    public Vector getPosition();

    public void update(String command);
    public String toString();

    public boolean alive();
    
}