/**
 * Projectile
 */
public class Projectile extends DefaultEntity {

    protected int lifetime;

    protected int speed;

    protected int age;

    protected static int DEFAULT_LIFETIME = 10;
    protected static int DEFAULT_SPEED = 5;

    public Projectile(Vector position, Vector velocity, int speed, int lifetime) {
        // Create new Entity
        super(new Vector(position), velocity.scale(speed));

        // Initialize instance variables.
        this.speed = speed;
        this.lifetime = lifetime;
        this.age = this.lifetime;
    }

    public Projectile(Vector position, Vector velocity) {
        this(position, velocity, DEFAULT_SPEED, DEFAULT_LIFETIME);
    }

    public Projectile(Vector position, Vector velocity, int speed) {
        this(position, velocity, speed, DEFAULT_LIFETIME);
    }

    @Override
    public String getName() {
        return "PROJECTILE (" + this.age + ")";
    }

    @Override
    public void update(String command) {
        this.age -= this.speed;
        super.update(command);
    }

    @Override
    public boolean alive() {
        return this.age > 0;
    }

}
