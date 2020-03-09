package jerra.entity;

import jerra.api.Affiliate;
import jerra.api.Physical;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

import jerra.api.Physical;
import jerra.core.Rect;
import jerra.presence.Presence;

/**
 * Entity
 */
public class DefaultEntity implements Entity {

    private Presence presence;
    private boolean alive;
    private char team;

    private List<String> commands;

    public DefaultEntity(Presence presence) {

        // Initalize command list
        this.commands = new ArrayList<String>();

        this.presence = presence;
        this.alive = true;

        // Default team
        this.team = 0;

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
        Entity out = new DefaultEntity(this.getPresence().copy());
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
    public void deflect(Physical other) {
        ;

    @Override
    public char getTeam() {
        return this.team;
    }

    @Override
    public Affiliate setTeam(char team) {
        this.team = team;
        return this;
    }
      
    @Override
    public boolean friendly(Affiliate other) {
        return this.getTeam() == other.getTeam();
    }

}
