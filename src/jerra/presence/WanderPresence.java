package jerra.presence;

import java.util.Random;

import jerra.core.Rect;
import jerra.core.Vector;

/**
 * WanderPresence
 */
public class WanderPresence extends DefaultPresence {

    private static final long serialVersionUID = 0;

    private Vector baseVelocity;
    private int segmentTime;
    private int progress;
    private Random generator;

    public WanderPresence(Rect position, Vector baseVelocity, int segmentTime, Random generator) {
        super(position, baseVelocity);
        this.baseVelocity = baseVelocity;
        this.segmentTime = segmentTime;
        this.progress = 0;
        this.generator = generator;
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
            int x = this.generator.nextInt(3) - 1;
            int y = this.generator.nextInt(3) - 1;
            // Move random direction
            this.setVelocity(this.getBaseVelocity().scale(x, y));
        }
        this.progress -= 1;
        // Transfer velocity
        super.update();
    }

    @Override
    public Presence copy() {
        Presence out = new WanderPresence(this.getPosition(), this.getBaseVelocity(), this.segmentTime, this.generator);
        out.setVelocity(this.getVelocity());
        return out;
    }
    
}
