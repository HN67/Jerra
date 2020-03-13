package jerra.entity;

import java.util.ArrayList;
import java.util.List;

import jerra.api.Interactive;
import jerra.stats.Stats;

/**
 * Respawner, an object that spawns a Shooter (after queueing the `respawn`
 * command and some period) iff it is dead.
 */
public class Respawner implements Interactive, Spawner<Shooter>{

    private Shooter entity;
    private int tick;
    private boolean start;
    private int period;
    private Stats initialStats;

    private List<String> commands;

    public Respawner(Shooter entity, int period) {
        this.entity = entity;
        this.initialStats = entity.getStats().copy();
        this.tick = 0;
        this.period = period;
        this.commands = new ArrayList<String>();
    }

    /**
     * Starts a timer after entity has died and the `respawn` command
     * has been called. This returns true when the timer is already
     * equal to the given respawner period.
     * 
     * @return boolean
     */
    @Override
    public boolean spawns() {
        if(!this.entity.alive()) {
            for (String command: this.commands) {
                if (command.equals("respawn")) {
                    this.start = true;
                }
            }
        }

        if(this.start) {
            this.tick++;
        }

        if(this.tick >= this.period) {
            this.tick = 0;
            this.start = false;
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

    @Override
    public void queue(String command) {
        this.commands.add(command);
    }

    @Override
    public void clearQueue() {
        this.commands.clear();
    }

    public int getTick() {
        return this.tick;
    }

}