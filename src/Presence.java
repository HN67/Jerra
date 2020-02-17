/**
 * Presence
 * A class representing a physical presence
 * Currently implemented with position and velocity Vectors
 * Moreover, a Presence is capable of self updating.
 * @author Ryan Allard
 */
public interface Presence extends Updatable {

    public void setPosition(Vector position);
    public void setPosition(int x, int y);
    public Vector getPosition();

    public void setVelocity(Vector velocity);
    public void setVelocity(int x, int y);
    public Vector getVelocity();

    public String toString();
    
}
