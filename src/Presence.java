/**
 * Presence
 */
public interface Presence {

    public void setPosition(Vector position);
    public void setPosition(int x, int y);
    public Vector getPosition();

    public void setVelocity(Vector velocity);
    public void setVelocity(int x, int y);
    public Vector getVelocity();
    
}
