package jerra.entity;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import jerra.api.Affiliate;
import jerra.api.Physical;
import jerra.core.Rect;
import jerra.effect.Effect;
import jerra.presence.Presence;
import jerra.stats.Stats;

/**
 * Entity
 */
public class DefaultEntity implements Entity {

    private Presence presence;
    private Image image;
    private boolean alive;
    private char team;
    
    private boolean hit;

    private Stats stats;

    private List<String> commands;

    public DefaultEntity(Presence presence, Image image) {

        // Initalize command list
        this.commands = new ArrayList<String>();
        this.image = image;

        this.presence = presence;
        this.alive = true;

        // Default team
        this.team = 0;

        // Empty stats
        this.stats = new Stats();

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

    @Override
    public boolean collides(Physical other) {

        return this.getPresence().collides(other.getPresence());
    }

    @Override
    public void hit() {
        this.hit = true;
    }

    @Override
    public void hit(Effect<? super Entity> effect) {
        this.hit();
        effect.apply(this);
    }

    @Override
    public void unhit() {
        this.hit = false;
    }

    @Override
    public boolean isHit() {
        return this.hit;
    }

    public List<String> commandQueue() {
        return this.commands;
    }

    @Override
    public void queue(String command) {
        this.commands.add(command);
        this.presence.queue(command);
    }

    @Override
    public void clearQueue() {
        this.commands.clear();
        this.presence.clearQueue();
    }

    @Override
    public void update() {
        this.unhit();
        this.presence.update();
    }

    @Override
    public Entity copy() {
        // Construct with copied presence
        Entity out = new DefaultEntity(this.getPresence().copy(), this.image);
        // Copy over aliveness
        out.kill(!this.alive());
        return out;
    }

    @Override
    public void interact(Entity other) {
        ;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.presence.toString();
    }

    public String symbol() {
        return "E";
    }

    @Override
    public boolean alive() {
        return this.alive;
    }

    @Override
    public void kill(boolean dead) {
        this.alive = !dead;
    }

	@Override
	public Image image() {
		return this.image;
	}
    
    
    @Override
    public void deflect(Physical other) {
        ;
    }

    @Override
    public char getTeam() {
        return this.team;
    }

    @Override
    public DefaultEntity setTeam(char team) {
        this.team = team;
        return this;
    }

    @Override
    public boolean friendly(Affiliate other) {
        return this.getTeam() == other.getTeam();
    }

    @Override
    public DefaultEntity setStats(Stats stats) {
        this.stats = stats;
        return this;
    }

    @Override
    public Stats getStats() {
        return this.stats;
    }

}
