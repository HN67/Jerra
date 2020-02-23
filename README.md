# Jerra

Repository for *Jerra*, a two dimensional shoot-loot-upgrade game written in Java.

## Building

Jerra can be compiled from command line by running `javac *.java` inside the `src` directory to compile all of the source source files. Once Jerra is compiled, the project can be run with `java Main` inside the `src` directory.

Alternatively, an IDE such as Eclipse or VSCode should recognize the GitHub repository as a Java project, and handle compiling/running automatically.

Either method requires a Java JDK installation, recommended Java 8 or later.

## Overview

Jerra is currently a text based game designed to be run from the console. Jerra is a top down shooter game designed to be capable of growing into a full loot and upgrade adventure game.

### Movement

The player controls an avatar which can be moved by entering movement commands, such as "up", "down", "left", and "right", which causes the avatar to move in the respective direction. Movement is implemented in Jerra with a capable velocity system, allowing expansion into acceleration and other complex movement if desired.

### Mortality

### Collisions

Jerra uses a sophisticated linear control hierarchy to handle collisions. Each Entity self contains logic to check for and handle a collision with another Entity, and the Room can operate these methods without any knowledge of the internal logic.

### Projectiles
