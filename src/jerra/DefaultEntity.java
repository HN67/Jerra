package jerra;

/**
 * Entity
 */
public class DefaultEntity implements Entity {

    private Presence presence;
    private boolean alive;

    public DefaultEntity(Presence presence) {
        this.presence = presence;
        this.alive = true;
    }

    public String getName() {
        return "ENTITY";
    }

    public Presence getPresence() {
        return this.presence;
    }

    public Rect getPosition() {
        return this.presence.getPosition();
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public boolean collides(Entity other) {
        return this.getPresence().collides(other.getPresence());
    }

    public void update(String command) {
        this.presence.update(command);
    }

    @Override
    public Entity copy() {
        // Construct with copied presence
        Entity out = new DefaultEntity(this.getPresence().copy());
        // Copy over aliveness
        out.kill(!this.alive());
        return out;
    }

    public void interact(Entity other, String command) {
        ;
    }

    public String toString() {
        return this.getName() + ": " + this.presence.toString();
    }

    public String symbol() {
        return "E";
    }

    public boolean alive() {
        return this.alive;
    }

    public void kill(boolean dead) {
        this.alive = !dead;
    }

}
