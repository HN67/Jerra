package jerra.presence;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;
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

    /**
     * Takes a Map that maps from any type to a collection of that type.
     * The method then uses the callback method to combine every key with each object in its Collection value.
     * Intended to be used after the collisions method, in order to actually handle the collisions.
     * @param <T> any type, should be able to be in a Map
     * @param interactors a Map<T, Collection<T>> that indicates which group of objects is associated with each object
     * @param callback a BiConsumer<T, T>, should interact the two objects it is given
     */
    public static <T> void interact(Map<T, Collection<T>> interactors, BiConsumer<T, T> callback) {
        for (Map.Entry<T, Collection<T>> entry: interactors.entrySet()) {
            // Iterate through each interaction, call callback
            T left = entry.getKey();
            for (T right: entry.getValue()) {
                callback.accept(left, right);
            }
        }
    }
    
}
