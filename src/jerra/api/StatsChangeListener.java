package jerra.api;

import java.io.Serializable;

import jerra.stats.Stats;

/**
 * Interface meant for classes that handle the Stats on change event.
 */
@FunctionalInterface
public interface StatsChangeListener extends Serializable {

    /**
     * The method invoked by a Stats object when one of it the Stats change.
     * Or more specifically, when Stats.changeValue is called.
     * @param stats
     * @param property
     */
    public void changed(Stats stats, Stats.Type property);
    
}