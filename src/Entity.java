/**
 * Entity
 */
public class Entity {

    private Vector position;
    private Vector velocity;

    public Entity(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

	public void setPosition(Vector position) {
        this.position = position;
    }
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setPosition(int x, int y) {
        this.position = new Vector(x, y);
    }

    public void setVelocity(int x, int y) {
        this.velocity = new Vector(x, y);
    }

    public Vector getPosition() {
        return this.position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public String getName() {
        return "ENTITY";
    }

    public void update(String command) {

    }

    public String toString() {
        return this.getName() + ": Position: " + this.position.toString() + ", Velocity: " + this.velocity.toString();
    }
    
}