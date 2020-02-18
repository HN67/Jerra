/**
 * Projectile
 */
public class Projectile extends DefaultEntity {

    protected int lifetime;
    protected int age;

    public Projectile(Presence presence, int lifetime) {
        // Call super constructor
        super(presence);
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
