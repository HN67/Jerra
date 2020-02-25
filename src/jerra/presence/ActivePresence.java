package jerra.presence;

import jerra.core.Vector;
import jerra.core.Rect;

/**
 * ActivePresence
 * @author Ryan Allard
 */
public class ActivePresence extends DefaultPresence {

    private String up;
    private String right;
    private String down;
    private String left;

    private Vector baseVelocity;

    /**
     * Constructs an Active Presence controlled by inputs to the update method
     * @param position Position rect of the Presence
     * @param velocity Base velocity vector of the Presence, scaled in update
     * @param up The Command string that causes upward movement
     * @param right The Command string that causes rightward movement 
     * @param down The Command string that causes downward movement
     * @param left The Command string that causes leftward movement
     */
    public ActivePresence(Rect position, Vector velocity, String up, String down, String left, String right) {
        super(position, velocity);

        // Save base velocity
        this.baseVelocity = velocity;

        // Copy strings
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;

    }

    /**
     * Sets the base velocity of the Presence to scale movement off of
     * @param velocity
     */
    public void setBaseVelocity(Vector velocity) {
        this.baseVelocity = velocity;
    }

    /**
     * Returns the base velocity of the Presence
     * @return A Vector of the base velocity
     */
    public Vector getBaseVelocity() {
        return this.baseVelocity;
    }

    @Override
    public void update(String command) {
        // Sets velocity to axis bound of base velocity based on command
        if (command.equals(this.up)) {
            this.setVelocity(this.getBaseVelocity().scale(0, -1));
        } else if (command.equals(this.down)) {
            this.setVelocity(this.getBaseVelocity().scale(0, 1));
        } else if (command.equals(this.left)) {
            this.setVelocity(this.getBaseVelocity().scale(-1, 0));
        } else if (command.equals(this.right)) {
            this.setVelocity(this.getBaseVelocity().scale(1, 0));
        } else {
            this.setVelocity(0, 0);
        }
        // Add velocity into position
        super.update(command);
    }
    
}