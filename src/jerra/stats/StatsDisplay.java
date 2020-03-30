package jerra.stats;

import java.io.Serializable;

/**
 * A class wrapping a Stats that also tracks whether it should be displayed or not
 */
public class StatsDisplay implements Serializable {

    private static final long serialVersionUID = 0L;

    private Stats stats;

    private boolean show;

    private int tick;
    private int hideAfter;

    public StatsDisplay(Stats stats) {
        this(stats, -1);
    }

    /**
     * Creates a StatsDisplay that tracks whether it should be displayed
     * Defaults to hidden
     * @param stats a Stats object, carried to be used by whatever displays this
     * @param hideAfter optional, a int, this StatsDisplay will be automatically hidden this number of tick() calls after being shown
     */
    public StatsDisplay(Stats stats, int hideAfter) {
        this.stats = stats;
        this.show = false;
        this.tick = 0;
        this.hideAfter = hideAfter;
    }

    /** 
     * Returns the stats object displayed by this object
     */
    public Stats getStats() {
        return this.stats;
    }

    /**
     * Ticks the Display, may change the visible state
     */
    public void tick() {
        if (this.visible()) {
            this.tick++;
            if(this.hideAfter > 0 && this.tick > this.hideAfter) {
                this.tick = 0;
                this.hide();
            }
        }
    }

    /**
     * Returns whether the Stats Display should be considered "visible"
     * @return
     */
    public boolean visible() {
        return this.show;
    }
    
    /**
     * Sets the instance variable show as true.
     * If it is, this ensures that the healthbar is shown
     * when it is rendered.
     */
    public void show() {
        this.show = true;
    }

    /**
     * Sets the instance variable show as false;
     * If it is, this ensures that the healthbar is hidden
     * when it is rendered.
     */
    public void hide() {
        this.show = false;
    }

    /**
     * Sets the instance variable hideAfter;
     * @param hideAfter
     */
    public void setHideAfter(int hideAfter) {
        this.hideAfter = hideAfter;
    }
    
}