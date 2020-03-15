package jerra.room;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import jerra.api.Interactive;
import jerra.api.Mortal;
import jerra.api.Updatable;
import jerra.item.Loot;
import jerra.entity.Entity;
import jerra.entity.Player;
import jerra.entity.Shooter;
import jerra.entity.Spawner;
import jerra.presence.Collider;

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
    private Set<Loot> loots;
    // Spawner set
    private Set<Spawner<Entity>> spawners;
    private Set<Spawner<Shooter>> shooterSpawners;

    // Removes the object from all sets
    private void remove(Object object) {
        this.updatables.remove(object);
        this.interactables.remove(object);
        this.mortals.remove(object);
        this.entities.remove(object);
        this.spawners.remove(object);
        this.shooterSpawners.remove(object);
    }

    public TextRoom() {
        this.updatables = new HashSet<Updatable>();
        this.interactables = new HashSet<Interactive>();
        this.mortals = new HashSet<Mortal>();

        this.entities = new LinkedHashSet<Entity>();
        this.spawners = new LinkedHashSet<Spawner<Entity>>();
        this.shooterSpawners = new HashSet<Spawner<Shooter>>();
    }

    @Override
    public void spawnEntity(Entity entity) {
        this.updatables.add(entity);
        this.interactables.add(entity);
        this.mortals.add(entity);

        this.entities.add(entity);
    }

    @Override
    public void spawnSpawner(Spawner<Entity> spawner) {
        this.spawners.add(spawner);
    }

    @Override
    public void spawnPlayer(Player player) {
        // Currently a player is just a shooter
        this.spawnShooter(player);
    }

    @Override
    public void spawnShooter(Shooter shooter) {
        this.spawnEntity(shooter);
        this.spawnSpawner(shooter);
    }

    @Override
    public void spawnShooterSpawner(Spawner<Shooter> spawner) {
        this.shooterSpawners.add(spawner);
    }

    @Override
    public void spawnLoot(Loot loot) {
        this.loots.add(loot);
    }

    @Override
    public void queue(String command) {
        // Queue command to every entity
        for (Interactive interactable: this.interactables) {
            interactable.queue(command);
        }
    }

    @Override
    public void clearQueue() {
        // Queue command to every entity
        for (Interactive interactable: this.interactables) {
            interactable.clearQueue();
        }
    }

    @Override
    public void update() {

        // Update all general updatables
        for (Updatable updatable: this.updatables) {
            updatable.update();
        }

        // Check for collisions between Entities (O(n^2))
        // Creates a map from each entity to its collisions
        Map<Entity, Collection<Entity>> allCollisions = Collider.collisions(this.entities);
        // Resolve collisions through interaction
        Collider.interact(allCollisions, (Entity entity, Entity other) -> {
            entity.deflect(other);
            entity.interact(other);
        });

        // Check for collisions between loot objects
        Map<Loot, Collection<Loot>> lootCollisions = Collider.collisions(this.loots);
        // Handle collisions between loot objects with interaction (e.g. picking up)
        Collider.interact(lootCollisions, (Loot loot, Loot other) -> {
            loot.interact(other);
        });

        // Remove dead mortals
        for (Mortal mortal: new HashSet<Mortal>(this.mortals)) {
            if (!mortal.alive()) {
                this.remove(mortal);
            }
        }
        
        // Check spawners
        for (Spawner<Entity> spawner: this.spawners) {
            // Get spawned Entity if Spawner spawns
            if (spawner.spawns()) {
                this.spawnEntity(spawner.spawn());
            }
        }

        for (Spawner<Shooter> spawner: this.shooterSpawners) {
            // Get spawned Entity if Spawner spawns
            if (spawner.spawns()) {
                this.spawnShooter(spawner.spawn());
            }
        }

    }
    
    @Override
    public Set<Entity> getEntities() {
    	return this.entities;
    }

    @Override
    public String toString() {
        String output = "";
        for (Entity entity: this.entities) {
            output += entity.toString() + "\n";
        }
        return output;
    }

}