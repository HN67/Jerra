/**
 * Bullet
 */
public class Bullet extends Projectile {
    public Bullet(Vector position, Vector velocity) {
        super(position, velocity, 2, 8);
    }

    @Override
    public String getName() {
        return "Bullet (" + this.age + ")";
    }

}
