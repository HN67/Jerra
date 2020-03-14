package jerra.presence;

import java.lang.Math;

import jerra.core.Rect;
import jerra.core.Vector;

/**
 * WanderPresence
 */
public class WanderPresence extends DefaultPresence {

    private Vector baseVelocity;
    private int segmentTime;
    private int progress;

    public WanderPresence(Rect position, Vector baseVelocity, int segmentTime) {
        super(position, baseVelocity);
        this.baseVelocity = baseVelocity;
        this.segmentTime = segmentTime;
        this.progress = 0;
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
        if (this.progress <= 0) {
            this.progress = this.segmentTime;
            // Generate random numbers in [-1, 1]
            int x = (int)(Math.random() * 3) - 1;
            int y = (int)(Math.random() * 3) - 1;
            // Move random direction
            this.setVelocity(this.getBaseVelocity().scale(x, y));
        }
        this.progress -= 1;
        // Transfer velocity
        super.update();
    }

    @Override
    public Presence copy() {
        Presence out = new WanderPresence(this.getPosition(), this.getBaseVelocity(), this.segmentTime);
        out.setVelocity(this.getVelocity());
        return out;
    }
    
}
