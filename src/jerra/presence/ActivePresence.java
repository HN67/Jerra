package jerra.presence;

import jerra.core.Rect;
import jerra.core.Vector;

/**
 * ActivePresence
 * @author Ryan Allard
 */
public class ActivePresence extends DefaultPresence {

    private static final long serialVersionUID = 0;

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
    public void update() {
        this.setVelocity(0, 0);
        for (String command: this.commandQueue()) {
            // Sets velocity to axis bound of base velocity based on command
            if (command.equals(this.up)) {
                this.setVelocity(this.getVelocity().add(this.getBaseVelocity().scale(0, -1)));
            } else if (command.equals(this.down)) {
                this.setVelocity(this.getVelocity().add(this.getBaseVelocity().scale(0, 1)));
            } else if (command.equals(this.left)) {
                this.setVelocity(this.getVelocity().add(this.getBaseVelocity().scale(-1, 0)));
            } else if (command.equals(this.right)) {
                this.setVelocity(this.getVelocity().add(this.getBaseVelocity().scale(1, 0)));
            }
        }
        // Add velocity into position
        super.update();
    }

    @Override
    public Presence copy() {
        Presence out = new ActivePresence(this.getPosition(), this.getBaseVelocity(), this.up, this.down, this.left, this.right);
        out.setVelocity(this.getVelocity());
        return out;
    }
    
}