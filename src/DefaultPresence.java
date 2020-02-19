/**
 * An implementation of a Presence by simply storing the Vectors
 * The update method is a sane default of adding the velocity to the position
 * @author Ryan Allard
 */
public class DefaultPresence implements Presence {

    private Vector position;
    private Vector velocity;

    /**
     * Constructs a Presence with the given two Vectors
     * @param position
     * @param velocity
     */
    public DefaultPresence(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Constructs a deep-copied Presence
     * @param other The Presence to copy
     */
    public DefaultPresence(Presence other) {
        this.position = new Vector(other.getPosition());
        this.velocity = new Vector(other.getVelocity());
    }

	public void setPosition(Vector position) {
        this.position = position;
    }
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setPosition(int x, int y) {
        this.setPosition(new Vector(x, y));
    }

    public void setVelocity(int x, int y) {
        this.setVelocity(new Vector(x, y));
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    /**
     * Updates the Presence by adding the Velocity onto the Position
     */
    public void update(String command) {
        this.setPosition(this.getPosition().add(this.getVelocity()));
    }

    public String toString() {
        return "Position: " + this.position.toString() + ", Velocity: " + this.velocity.toString();
    }
    
}