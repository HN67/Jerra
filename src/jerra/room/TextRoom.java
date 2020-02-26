package jerra.room;

import java.util.ArrayList;
import java.util.List;

import jerra.entity.Entity;
import jerra.entity.Player;
import jerra.entity.Spawner;

/**
 * TextRoom
 */
public class TextRoom implements Room {

    // EntityList should contain *all* entities
    private List<Entity> entityList;
    // Sublists are for finer control
    private List<Spawner> spawners;

    public TextRoom() {
        this.entityList = new ArrayList<Entity>();
        this.spawners = new ArrayList<Spawner>();
    }

    @Override
    public void spawnEntity(Entity entity) {
        this.entityList.add(entity);
    }

    @Override
    public void spawnSpawner(Spawner spawner) {
        this.spawners.add(spawner);
    }

    @Override
    public void spawnPlayer(Player player) {
        // Adding to entityList allows bulk updating
        this.entityList.add(player);
        // Registers the player as a spawner (for shooting)
        this.spawners.add(player);
    }

    @Override
    public void queue(String command) {
        // Queue command to every entity
        for (Entity entity: this.entityList) {
            entity.queue(command);
        }
        // Queue command to every spawner
        // for (Spawner spawner: this.spawners) {
        //     spawner.queue(command);
        // }
    }

    @Override
    public void clearQueue() {
        // Queue command to every entity
        for (Entity entity: this.entityList) {
            entity.clearQueue();
        }
        // Queue command to every spawner
        for (Spawner spawner: this.spawners) {
            spawner.clearQueue();
        }
    }

    @Override
    public void update() {

        // Update all general entities
        for (Entity entity: this.entityList) {
            entity.update();
        }

        // Check for collisions between Entities (O(n^2))
        // Creates a list of lists of collisions, ordered in the same order as entity list
        List<List<Entity>> allCollisions = new ArrayList<List<Entity>>(this.entityList.size());
        // Iterate through each entity
        for (Entity entity: this.entityList) {
            List<Entity> collisions = new ArrayList<Entity>();
            // Check for collision with *every other* entity
            for (Entity other: this.entityList) {
                // Check for collision and ensure its not the same entity
                // Lazy evaluation ensures second check is only performed if first succeeds
                if (entity.collides(other) && entity != other) {
                    collisions.add(other);
                }
            }
            // Add collisions to larger list
            allCollisions.add(collisions);
        }
        // Resolve collisions through interaction
        for (int i = 0, n = this.entityList.size(); i < n; i++) {
            // Iterate through each collision with the entity
            for (Entity other: allCollisions.get(i)) {
                this.entityList.get(i).interact(other);
            }
        }

        // Remove dead entities
        this.entityList.removeIf(entity -> !entity.alive());
        
        // Check spawners
        for (Spawner spawner: this.spawners) {
            // Get spawned Entity if Spawner spawns
            if (spawner.spawns()) {
                this.spawnEntity(spawner.spawn());
            }
        }

        this.spawners.removeIf(spawner -> !spawner.alive());

    }
    
    @Override
    public List<Entity> getEntities() {
    	return this.entityList;
    }

    @Override
    public String toString() {
        String output = "";
        for (Entity entity: this.entityList) {
            output += entity.toString() + "\n";
        }
        return output;
    }

}