package jerra.stats;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

import jerra.api.Copyable;

/**
 * Stats class that contains various types of stats
 */
public class Stats implements Copyable<Stats>, Serializable {

    private Map<Type, Integer> values;

    public enum Type {
        HEALTH,
        VITALITY,
    }

    public Stats() {
        this.values = new HashMap<Type, Integer>(Type.values().length);
    }

    public Stats(Map<Type, Integer> values) {
        this.values = new HashMap<Type, Integer>(values);
    }

    public Stats(int health, int vitality) {
        this();
        this.setValue(Type.HEALTH, health);
        this.setValue(Type.VITALITY, vitality);
    }

    public Stats setValue(Type stat, int value) {
        this.values.put(stat, value);
        return this;
    }

    public Stats changeValue(Type stat, int value) {
        if (this.values.containsKey(stat)) {
            this.values.put(stat, this.values.get(stat) + value);
        } else {
            this.values.put(stat, value);
        }
        return this;
    }

    public int getValue(Type stat) {
        if (this.values.containsKey(stat)) {
            return this.values.get(stat);
        } else {
            return 0;
        }
    }

    @Override
    public Stats copy() {
        return new Stats(this.values);
    }
    
}
