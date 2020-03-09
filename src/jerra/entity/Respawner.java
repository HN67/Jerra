package jerra.entity;

/**
 * Respawner, an object that spawns a Shooter (after a period) iff it is dead.
 */
public class Respawner implements Spawner<Shooter>{

    private Shooter entity;
    private int tick;
    private int period;
    private boolean entityDied;

    public Respawner(Shooter entity, int period) {
        this.entity = entity;
        this.tick = 0;
        this.period = period;
    }

    /**
     * Returns true after a certain period the player has died.
     */
    @Override
    public boolean spawns() {
        if(!this.entity.alive()) {
            this.entityDied = true;

            this.entity = this.entity.copy();
        }

        if(this.entityDied) {
            this.tick++;
        }

        if(tick >= this.period) {
            this.tick = 0;
            return true;
        }

        return false;
    }

    @Override
    public Shooter spawn() {
        return this.entity;
    }

}