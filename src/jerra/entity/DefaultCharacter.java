package jerra.entity;

import jerra.presence.Presence;

import jerra.stats.Stats;
/**
 * DefaultCharacter
 */
public class DefaultCharacter extends DefaultEntity {

    public DefaultCharacter(Presence presence, Stats stats) {
        super(presence);
        this.setStats(stats);
    }

    @Override
    public boolean alive() {
        return this.getStats().getValue(Stats.Type.HEALTH) > 0;
    }

    @Override
    public void kill(boolean dead) {
        if (!dead) {
            this.setStats(this.getStats().setValue(Stats.Type.HEALTH, 0));
        }
    }
    
}