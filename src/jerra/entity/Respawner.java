package jerra.entity;

import jerra.stats.Stats;

/**
 * Respawner, an object that spawns a Shooter (after a period) iff it is dead.
 */
public class Respawner implements Spawner<Shooter>{

    private Shooter entity;
    private int tick;
    private int period;
    private Stats initialStats;

    public Respawner(Shooter entity, int period) {
        this.entity = entity;
        this.initialStats = entity.getStats().copy();
        this.tick = 0;
        this.period = period;
    }

    /**
     * Returns true after a certain period the player has died.
     */
    @Override
    public boolean spawns() {
        if(!this.entity.alive()) {
            this.tick++;
        }

        if(this.tick >= this.period) {
            this.tick = 0;
            return true;
        }

        return false;
    }

    @Override
    public Shooter spawn() {
        Shooter entity = this.entity.copy();

        entity.setStats(this.initialStats.copy());

        this.entity = entity;

        return entity;
    }

}