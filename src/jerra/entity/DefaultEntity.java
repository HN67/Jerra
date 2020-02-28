package jerra.entity;

import java.util.List;

import javafx.scene.image.Image;

import java.util.ArrayList;

import jerra.core.Rect;

import jerra.presence.Presence;

/**
 * Entity
 */
public class DefaultEntity implements Entity {

    private Presence presence;
    private Image image;
    private boolean alive;

    private List<String> commands;

    public DefaultEntity(Presence presence, Image image) {

        // Initalize command list
        this.commands = new ArrayList<String>();
        this.image = image;

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

	@Override
	public Image image() {
		return this.image;
	}
    
    

}
