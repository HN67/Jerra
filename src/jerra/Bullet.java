package jerra;

/**
 * Bullet
 */
public class Bullet extends Projectile {

    /**
     * Constructs a Presence based on position and velocity
     * Bullets have a fixed size
     */
    private static Presence constructPresence(Vector position, Vector velocity) {
        return new DefaultPresence(new Rect(position, new Vector(1, 1)), velocity);
    }

    public Bullet(Vector position, Vector velocity) {
        // Create projectile with predetermined presence and lifetime
        super(constructPresence(position, velocity), 5);
    }

    @Override
    public String getName() {
        return "Bullet (" + this.age + ")";
    }

    @Override
    public void interact(Entity other, String command) {
        // Kill other entity
        other.kill(true);
        // Kill this (remove for penetrating)
        this.kill(true);
    }

}
