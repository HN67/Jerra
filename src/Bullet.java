/**
 * Bullet
 */
public class Bullet extends Projectile {

    public Bullet(Presence presence) {
        // Set Bullet range
        super(presence, 5);
    }

    @Override
    public String getName() {
        return "Bullet (" + this.age + ")";
    }

}
