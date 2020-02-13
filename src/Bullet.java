/**
 * Bullet
 */
public class Bullet extends Projectile {

    public Bullet(Vector position, Vector velocity) {
        // Set Bullet range
        super(position, velocity, 5);
    }

    @Override
    public String getName() {
        return "BULLET";
    }
    
}
