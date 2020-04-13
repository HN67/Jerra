# Jerra

Repository for *Jerra*, a two dimensional shoot-loot-upgrade game written in Java.

The source respository can be found on GitHub: [*Jerra*](https://github.com/HN67/Jerra)

## Building

Jerra can be compiled from command line using the `java` tool to compile all the source files and resources (`*`) inside the `src` directory. Depending on the version of Java, JavaFX jars may have to be provided to the classpath. Once Jerra is compiled, the project can be run using the `java` inside the `src` directory, with an entry point of `jerra.Main`. Again, JavaFX jars may have to be provided to the classpath.

Alternatively, an IDE such as Eclipse or VSCode should recognize the GitHub repository as a Java project, and handle compiling/running, as well as JavaFX dependencies, automatically.

Either method requires a Java JDK installation, recommended Java 8 or later. Java 11 or later will also require a seperate JavaFX installation.

## Overview

Jerra is a top down shooter game designed to be capable of growing into a full loot and upgrade adventure game.

### Save

Jerra supports a fully capable save and load system by serializing the Room object (which is the main logic container) to disk and then unserializing on load. Loading fully resets the game state to when it was saved, and can be loaded from a save created by a previous instance of the application.

### Movement

The player controls an avatar which can be moved by entering movement commands, such as "up", "down", "left", and "right", which causes the avatar to move in the respective direction. Movement commands are automatically provided by WASD keypresses. Enemies will autonoumously move in random directions. Conversely, bullets move in a fixed linear direction. Movement is implemented in Jerra with a capable velocity system, allowing expansion into acceleration and other complex movement if desired.

### Spawning

New entities are spawned into the game room with a robust Spawner architecture, which allows spawners to self report when and what they spawn. The avatar and enemies uses this architecture to spawn bullets, and an ambient spawner uses this architecture to spawn new entities. Additionally, another ambient spawner (using the same generic class) uses this to spawn medkit lootbags.

### Collisions

Jerra checks for collisions between all entities, and allows them to interact with each other. Currently, this allows for bullets to hit and kill the player's avatar, as well as other entities, but has the potential to be expanded to handle any type of interaction, such as opening a chest. Collision architecture is used to detect and collect lootbags.

### Items

Jerra has a developed Item and Inventory system that allows tracking and using quantities of items. The player has an inventory that contains items, currently only medkits. The player can pick up randomly spawned lootbags, which adds the contents (a medkit) to their inventory. A medkit can then be consumed with E, which takes advantage of the Effect and Stats architecture to increase the player health.

### Deflection

Using the collision and interaction architecture, Jerra integrates a Deflectable architecture used by walls to prevent entities from moving into them.

### Teams

Jerra uses a simple and robust Affiliate architecture to allow entities to differentiate between enemies and allies, which allows bullets to only collide with and damage enemies.

### Effects

Jerra uses an Effects architecture, where each Bullet carries an effect (i.e. Damage) that it applies to the entity it collides with. This allows for expandable mechanics beyond damage, such as bullets that cause acceleration.

### Stats

Every entity has a Stats object, which tracks various values such as health. Effects can manipulate these stats, allowing bullets to deal damage to entities. Health is displayed using health bars overhead that are displayed briefly onhit.

### Mortality

Every entity has a state of aliveness, and is removed from the game when it is "dead". This allows for a flexible system, where bullets report as dead when they have gone a specific distance, and the avatar and other entities report as dead when their health reaches zero.

### Images

Every entity self-reports a image string, which is matched against a string to JavaFX map, allowing entities to return a different image based on its state and orientation, but also be easily serializable. This image is then drawn onto a canvas based on the entity's position, allowing simple graphical output of the game logic.
