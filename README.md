# Jerra

Repository for *Jerra*, a two dimensional shoot-loot-upgrade game written in Java.

## Building

Jerra can be compiled from command line by running `javac *.java` inside the `src` directory to compile all of the source source files. Once Jerra is compiled, the project can be run with `java Main` inside the `src` directory.

Alternatively, an IDE such as Eclipse or VSCode should recognize the GitHub repository as a Java project, and handle compiling/running automatically.

Either method requires a Java JDK installation, recommended Java 8 or later.

## Overview

Jerra is currently a text based game designed to be run from the console. Jerra is a top down shooter game designed to be capable of growing into a full loot and upgrade adventure game.

### Movement

The player controls an avatar which can be moved by entering movement commands, such as "up", "down", "left", and "right", which causes the avatar to move in the respective direction. Conversely, bullets move in a fixed linear direction. Movement is implemented in Jerra with a capable velocity system, allowing expansion into acceleration and other complex movement if desired.

### Spawning

New entities are spawned into the game room with a robust Spawner architecture, which allows spawners to self report when and what they spawn. The avatar uses this architecture to spawn bullets, and an ambient spawner uses this architecture to spawn new entities.

### Collisions

Jerra checks for collisions between all entities, and allows them to interact with each other. Currently, this allows for bullets to hit and kill the player's avatar, as well as other entities, but has the potential to be expanded to handle any type of interaction, such as opening a chest.

### Mortality

Every entity has a state of aliveness, and is removed from the game when it is "dead". This allows for a flexible system, where bullets report as dead when they have gone a specific distance, and the avatar and other entities report as dead when they have been hit by a bullet.
