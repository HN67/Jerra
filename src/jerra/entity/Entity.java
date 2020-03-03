package jerra.entity;

import jerra.api.Copyable;
import jerra.api.Interactive;
import jerra.api.Updatable;
import jerra.core.Rect;
import jerra.presence.Presence;

/**
 * Entity
 */
public interface Entity extends Updatable, Copyable<Entity>, Interactive {

    public Presence getPresence();
    public void setPresence(Presence presence);

    public Rect getPosition();

    public boolean collides(Entity other);
    
    public void interact(Entity other);

    @Override
    public String toString();
    public String symbol();

    @Override
    public Entity copy();

    public boolean alive();
    public void kill(boolean dead);

    default boolean deflectable() {
        return true;
    }
    
}