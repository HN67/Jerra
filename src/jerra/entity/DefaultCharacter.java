package jerra.entity;

import jerra.presence.Presence;

import jerra.stats.Stats;
import jerra.stats.Character;

/**
 * DefaultCharacter
 */
public class DefaultCharacter extends DefaultEntity implements Character {

    private Stats stats;

    public DefaultCharacter(Presence presence, Stats stats) {
        super(presence);
        this.setStats(stats);
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public Character setStats(Stats stats) {
        this.stats = stats;
        return this;
    }
    
}