package jerra.presence;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import jerra.api.Physical;

/**
 * Helper generic class for producing a collision map between Phsyical subclasses
 */
public class Collider {

    /**
     * Checks for a collision between all the objects in the set.
     * Returns a Map mapping from each object to a set of the objects it is colliding with.
     * Likely runs in O(n^2) time, where n is the size of the objects set.
     * @param objects a Set, the objects to check for collisiosn between
     * @return a Map<T, Set<T>>, maps from objects to their collisions
     */
    public static <T extends Physical> Map<T, Collection<T>> collisions(Collection<T> objects) {
        // Creates a map from each entity to its collisions
        Map<T, Collection<T>> allCollisions = new HashMap<T, Collection<T>>(objects.size());
        // Iterate through each entity
        for (T entity: objects) {
            Set<T> collisions = new HashSet<T>();
            // Check for collision with *every other* entity
            for (T other: objects) {
                // Check for collision and ensure its not the same entity
                // Lazy evaluation ensures second check is only performed if first succeeds
                if (entity.collides(other) && entity != other) {
                    collisions.add(other);
                }
            }
            // Add collisions to map
            allCollisions.put(entity, collisions);
        }
        return allCollisions;
    }
    
}
