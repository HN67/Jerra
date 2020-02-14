/**
 * Projectile
 */
public class Projectile extends DefaultEntity {

    protected int lifetime;

    protected int speed;

    protected int age;

    public Projectile(Vector position, Vector velocity, int speed, int lifetime) {
        // Call super constructor
        super(
            new Vector(position),
            new Vector(
                    velocity.x() * speed,
                    velocity.y() * speed
                )
            );

        this.speed = speed;

        this.lifetime = lifetime;

        // Initalizie age
        this.age = this.lifetime;
    }

    public Projectile(Vector position, Vector velocity) {
        this(position, velocity, 5, 10);
    }


    public Projectile(Vector position, Vector velocity, int speed) {
        this(position, velocity, speed, 10);
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
