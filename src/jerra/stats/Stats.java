package jerra.stats;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jerra.api.Copyable;
import jerra.api.StatsChangeListener;

/**
 * Stats class that contains various types of stats
 */
public class Stats implements Copyable<Stats>, Serializable {

    private static final long serialVersionUID = 0;

    private Map<Type, Integer> values;

    private StatsChangeListener onChangeValue = null;

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

        if(this.onChangeValue != null) {
            this.onChangeValue.changed(this.copy(), stat);
        }

        return this;
    }

    public void setOnChangeValue(StatsChangeListener onChangeValue) {
        this.onChangeValue = onChangeValue;
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
