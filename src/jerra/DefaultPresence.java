package jerra;

/**
 * An implementation of a Presence by simply storing the Vectors
 * The update method is a sane default of adding the velocity to the position
 * @author Ryan Allard
 */
public class DefaultPresence implements Presence {

    private Rect position;
    private Vector velocity;

    /**
     * Constructs a Presence with the given position and velocity
     * @param position a Rect, the position
     * @param velocity a Vector, the velocity
     */
    public DefaultPresence(Rect position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Constructs a deep-copied Presence
     * @param other The Presence to copy
     */
    public DefaultPresence(Presence other) {
        this.position = new Rect(other.getPosition());
        this.velocity = new Vector(other.getVelocity());
    }

	public void setPosition(Rect position) {
        this.position = position;
    }

    public void setPosition(Vector position) {
        this.position = new Rect(position, this.position.getSize());
    }

    public void setPosition(int x, int y) {
        this.setPosition(new Vector(x, y));
    }

    public void setSize(Vector size) {
        this.position = new Rect(this.position.getOrigin(), size);
    }

    public void setSize(int width, int height) {
        this.setSize(new Vector(width, height));
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(int x, int y) {
        this.setVelocity(new Vector(x, y));
    }

    public Rect getPosition() {
        return this.position;
    }

    public Vector getOrigin() {
        return this.position.getOrigin();
    }

    public Vector getSize() {
        return this.position.getSize();
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public boolean collides(Presence other) {
        return this.getPosition().intersects(other.getPosition());
    }

    /**
     * Updates the Presence by adding the Velocity onto the Position origin
     */
    public void update(String command) {
        this.setPosition(this.getOrigin().add(this.getVelocity()));
    }

    public Presence copy() {
        // Simply delegate to copy constructor
        return new DefaultPresence(this);
    }

    public String toString() {
        return "Position: " + this.position.toString() + ", Velocity: " + this.velocity.toString();
    }
    
}