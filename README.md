# Jerra

Repository for *Jerra*, a two dimensional shoot-loot-upgrade game.

## Roadmap

Jerra will be implemented in multiple stages, of which the two main stages will be a text-based version run in the console, and a graphical version.

### Text Jerra

The first stage of Jerra runs in the console, using typed inputs to advance the game state.

Text Jerra will be implemented in at least three stages.

#### Stage A: Move

Stage A implements basic movement and console IO. Data is stored using a Room -> Entity design.

 Room | Vector | Entity | Player (Entity)
--- | --- | --- | --- |
List\<Entity> entityList | int getX() | Vector position |
add(Entity) | int getY() | Vector velocity |
update(String) | Vector add(Vector) | update(String) | update(String)
String toString() | | String toString() |
| | | getName()

#### Stage B: Shoot

Stage B implements basic projectile creation, by subclassing Entity to add a lifetime, and in the future damage, etc. Entity is refactored to add a kill() method, removing the Entity from the game. Room.add() should be refactored so that the Entity knows which Rooms it has been added too. Player is extended with a shoot() command, which creates a Projectile.

Entity | Projectile (Entity) | Player |
--- | --- | --- |
kill() | int lifetime | shoot() |

#### Stage C: Kill

#### Stage D: Loot
