package jerra.presence;

import jerra.core.Vector;
import jerra.core.Rect;

import jerra.api.Updatable;
import jerra.api.Copyable;
import jerra.api.Interactive;

/**
 * Presence
 * A class representing a physical presence
 * Currently implemented with a position Rect and velocity Vector
 * Moreover, a Presence is capable of self updating.
 * @author Ryan Allard
 */
public interface Presence extends Updatable, Copyable<Presence>, Interactive {

    /**
     * Replaces the position of this Presence with a new Rect
     * @param position a Rect, the new position
     */
    public void setPosition(Rect position);

    /**
     * Replaces the origin of this Presence with a new Vector
     * @param origin a Vector, the new origin of the Presence
     */
    public void setPosition(Vector origin);

    /**
     * Replaces the origin coordinates of this Presence
     * @param x a int, the new x position of this Presence
     * @param y a int, then new y position of this Presence
     */
    public void setPosition(int x, int y);

    /**
     * Replaces the size of this Presence with a new Vector
     * @param size a Vector, the new size of the Presence
     */
    public void setSize(Vector size);

    /**
     * Replaces the size coordinates of this Presence
     * @param width a int, the new width of this Presence
     * @param height a int, the new height of this Presence
     */
    public void setSize(int width, int height);

    /**
     * Exposes the current position of this Presence
     * @return a Rect, identical to the position of this Presence
     */
    public Rect getPosition();

    /**
     * Exposes the current origin of this Presence, likely the origin of the position 
     * @return a Vector, identical to the origin of this Presence
     */
    public Vector getOrigin();

    /**
     * Exposes the current size of this Presence, likely the size of the position
     * @return a Vector, identical to the size of this Presence
     */
    public Vector getSize();

    /**
     * Replaces the velocity of this Presence with a new Vector
     * @param velocity a Vector, the new velocity. Should not be null
     */
    public void setVelocity(Vector velocity);

    /**
     * Replaces the velocity of this Presence with new x and y components
     * @param x an int, the new x velocity (+ right)
     * @param y an int, the new y velocity (+ down)
     */
    public void setVelocity(int x, int y);

    /**
     * Exposes the current velocity of this Presence
     * @return a Vector, identical to the velocity of this Presence
     */
    public Vector getVelocity();

    /**
     * Determines whether this Presence and the other Presence are colliding
     * Checks the intersection of the Rects
     * @param other a Presence, to be checked if colliding with
     * @return a boolean, true if there is a collision
     */
    public boolean collides(Presence other);

    /**
     * Returns a String representation of this Presence.
     * Includes position and velocity information.
     * Useful for text-based output.
     * @return a String, containing the position and velocity of this Presence
     */
    public String toString();
    
}
