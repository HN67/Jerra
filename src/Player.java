/**
 * Player
 */
public class Player extends DefaultEntity implements Spawner {

    private String direction;

    public Player(Vector position, Vector velocity) {
        super(position, velocity);
        this.direction = "UP";
    }

    @Override
    public void update(String command) {
        if (command.equals("up")) {
            this.setVelocity(0, -1);
            this.direction = "UP";
        } else if (command.equals("down")) {
            this.setVelocity(0, 1);
            this.direction = "DOWN";
        } else if (command.equals("left")) {
            this.setVelocity(-1, 0);
            this.direction = "LEFT";
        } else if (command.equals("right")) {
            this.setVelocity(1, 0);
            this.direction = "RIGHT";
        } else {
            this.setVelocity(0, 0);
        }
        super.update(command);
    }

    @Override
    public String getName() {
        // Indicate direction in name
        return "PLAYER (" + this.direction + ")";
    }

    /**
     * Returns a new Entity, shot in the direction the Player is facing
     * @return A Entity, that has not been added to any room
     */
    public Projectile spawn() {
        // Make sure to delink position vectors
        // Set Projectile velocity based on facing direction
        if (this.direction.equals("UP")) {
            return new Bullet(new Vector(this.getPosition()), new Vector(0, -1));
        }
        if (this.direction.equals("DOWN")) {
            return new Bullet(new Vector(this.getPosition()), new Vector(0, 1));
        }
        if (this.direction.equals("RIGHT")) {
            return new Bullet(new Vector(this.getPosition()), new Vector(1, 0));
        }
        if (this.direction.equals("LEFT")) {
            return new Bullet(new Vector(this.getPosition()), new Vector(-1, 0));
        }
        // If somehow there is no direction, just drop the projectile
        return new Bullet(new Vector(this.getPosition()), new Vector(0, 0));
    }

    /**
     * Checks a command to see if the Player should spawn an entity (using .spawn)
     * @param command A String command
     * @return A boolean representing whether the Player should spawn an entity
     */
    public boolean spawns(String command) {
        return command.equals("shoot");
    }

}
