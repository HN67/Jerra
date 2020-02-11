/**
 * Projectile
 */
public class Projectile extends DefaultEntity {

    private int lifetime;
    private int age;

    public Projectile(Vector position, Vector velocity, int lifetime) {
        // Call super constructor
        super(position, velocity);
        // Reference lifetime
        this.lifetime = lifetime;
        // Initalizie age
        this.age = this.lifetime;
    }

    @Override
    public String getName() {
        return "PROJECTILE (" + this.age + ")";
    }

    @Override
    public void update(String command) {
        this.age -= 1;
        super.update(command);
    }

    @Override
    public boolean alive() {
        return this.age > 0;
    }

}