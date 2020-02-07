/**
 * Entity
 */
public class Entity {

    private InterfaceVector position;
    private InterfaceVector velocity;

    public Entity(InterfaceVector position, InterfaceVector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

	public void setPosition(InterfaceVector position) {
        this.position = position;
    }
    public void setVelocity(InterfaceVector velocity) {
        this.velocity = velocity;
    }

    public void setPosition(int x, int y) {
        this.position = new Vector(x, y);
    }

    public void setVelocity(int x, int y) {
        this.velocity = new Vector(x, y);
    }

    public InterfaceVector getPosition() {
        return this.position;
    }

    public InterfaceVector getVelocity() {
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