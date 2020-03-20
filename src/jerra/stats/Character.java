package jerra.stats;

import java.io.Serializable;

/**
 * Character interfaces, indicates that the object has a Stats component
 */
public interface Character extends Serializable {

    /**
     * Returns the Stats object associated with this Character.
     * Reference maintaining should be not relied on.
     * @return a Stats, from this Character
     */
    public Stats getStats();
    /**
     * Replaces the Stats object of this Character with the given
     * @param stats a Stats, to be assigned to this Character
     * @return a Character, this Character to allow for method chaining
     */
    public Character setStats(Stats stats);

}
