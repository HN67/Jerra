package jerra.api;

import jerra.stats.Stats;

@FunctionalInterface
public interface ChangeListener<T> {
    public void changed(T object, Stats.Type property);
}