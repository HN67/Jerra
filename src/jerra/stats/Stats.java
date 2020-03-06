package jerra.stats;

import java.util.Map;
import java.util.HashMap;

/**
 * Stats class that contains various types of stats
 */
public class Stats {

    private Map<Type, Integer> values;

    public enum Type {
        HEALTH,
        STRENGTH,
        FORTITUDE,
    }

    public Stats() {
        this.values = new HashMap<Type, Integer>(Type.values().length);
    }

    public void setValue(Type stat, int value) {
        this.values.put(stat, value);
    }

    public void getValue(Type stat) {
        this.values.get(stat);
    }
    
}
