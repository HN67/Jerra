/**
 * Player
 */
public class Player extends DefaultEntity {

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
        }
        if (command.equals("down")) {
            this.setVelocity(0, 1);
            this.direction = "DOWN";
        }
        if (command.equals("left")) {
            this.setVelocity(-1, 0);
            this.direction = "LEFT";
        }
        if (command.equals("right")) {
            this.setVelocity(1, 0);
            this.direction = "RIGHT";
        }
        super.update(command);
    }

    @Override
    public String getName() {
        // Indicate direction in name
        return "PLAYER (" + this.direction + ")";
    }

    /**
     * Returns a new projectile, shot in the direction the Player is facing
     * @return A Projectile, that has not been added to any room
     */
    public Projectile shoot() {
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
     * Checks a command to see if the Player should shoot
     * @param command A String command
     * @return A boolean representing whether the Player should shoot
     */
    public boolean shoots(String command) {
        return command.equals("shoot");
    }
    
}