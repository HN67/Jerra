package jerra.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jerra.api.Interactive;
import jerra.api.Mortal;
import jerra.api.Updatable;
import jerra.entity.Entity;
import jerra.entity.Player;
import jerra.entity.ShooterEntity;
import jerra.entity.Spawner;

/**
 * TextRoom
 */
public class TextRoom implements Room {

    // Raw sets
    private Set<Updatable> updatables;
    private Set<Interactive> interactables;
    private Set<Mortal> mortals;

    // Entity set should contain *all* entities
    private Set<Entity> entities;
    // Spawner set
    private Set<Spawner> spawners;

    // Removes the object from all sets
    private void remove(Object object) {
        this.updatables.remove(object);
        this.interactables.remove(object);
        this.mortals.remove(object);
        this.entities.remove(object);
        this.spawners.remove(object);
    }

    public TextRoom() {
        this.updatables = new HashSet<Updatable>();
        this.interactables = new HashSet<Interactive>();
        this.mortals = new HashSet<Mortal>();

        this.entities = new LinkedHashSet<Entity>();
        this.spawners = new LinkedHashSet<Spawner>();
    }

    @Override
    public void spawn(Entity entity) {
        this.updatables.add(entity);
        this.interactables.add(entity);
        this.mortals.add(entity);

        this.entities.add(entity);

        if (entity instanceof Spawner) {
            this.spawners.add((Spawner) entity);
        }
    }

    @Override
    public void spawn(Spawner spawner) {
        this.spawners.add(spawner);
    }

    @Override
    public void spawn(Player player) {
        this.spawn((Entity) player);
    }

    @Override
    public void spawn(ShooterEntity se) {
        this.spawn((Entity) se);
    }

    @Override
    public void queue(String command) {
        // Queue command to every entity
        for (Interactive interactable : this.interactables) {
            interactable.queue(command);
        }
    }

    @Override
    public void clearQueue() {
        // Queue command to every entity
        for (Interactive interactable : this.interactables) {
            interactable.clearQueue();
        }
    }

    @Override
    public void update() {

        // Update all general updatables
        for (Updatable updatable : this.updatables) {
            updatable.update();
        }

        // Check for collisions between Entities (O(n^2))
        // Creates a map from each entity to its collisions
        Map<Entity, List<Entity>> allCollisions = new HashMap<Entity, List<Entity>>(this.entities.size());
        // Iterate through each entity
        for (Entity entity : this.entities) {
            List<Entity> collisions = new ArrayList<Entity>();
            // Check for collision with *every other* entity
            for (Entity other : this.entities) {
                // Check for collision and ensure its not the same entity
                // Lazy evaluation ensures second check is only performed if first succeeds
                if (entity.collides(other) && entity != other) {
                    collisions.add(other);
                }
            }
            // Add collisions to map
            allCollisions.put(entity, collisions);
        }
        // Resolve collisions through interaction
        for (Map.Entry<Entity, List<Entity>> entry : allCollisions.entrySet()) {
            // Iterate through each collision with the entity
            Entity entity = entry.getKey();
            for (Entity other : entry.getValue()) {
                entity.interact(other);
            }
        }

        // Remove dead mortals
        for (Mortal mortal : new HashSet<Mortal>(this.mortals)) {
            if (!mortal.alive()) {
                this.remove(mortal);
            }
        }

        // Keep a separate set of new spawners
        // We add them after we loop through all
        // of current set of spawners.
        Set<Entity> newSpawners = new LinkedHashSet<Entity>();
    
        // Check spawners
        for (Spawner spawner : this.spawners) {
            // Get spawned Entity if Spawner spawns
            if (spawner.spawns()) {
                Entity spawned = spawner.spawn();

                // If spawned entity is a spawner, add it to the set of new spawners
                // Otherwise, spawn the entity.
                if (spawned instanceof Spawner) {
                    newSpawners.add(spawned);
                } else {
                    this.spawn(spawned);
                }
            }
        }

        // Spawn the new spawners
        for (Entity spawner : newSpawners) {
            this.spawn(spawner);
        }

    }

    @Override
    public Set<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public String toString() {
        String output = "";
        for (Entity entity : this.entities) {
            output += entity.toString() + "\n";
        }
        return output;
    }

}