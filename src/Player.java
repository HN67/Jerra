/**
 * Player
 */
public class Player extends Entity {

    public Player(InterfaceVector position, InterfaceVector velocity) {
        super(position, velocity);
    }

    @Override
    public void update(String command) {
        if (command.equals("up")) {
            this.setVelocity(0, -1);
        }
        if (command.equals("down")) {
            this.setVelocity(0, 1);
        }
        if (command.equals("left")) {
            this.setVelocity(-1, 0);
        }
        if (command.equals("right")) {
            this.setVelocity(1, 0);
        }
        this.setPosition(this.getPosition().add(this.getVelocity()));
    }

    @Override
    public String getName() {
        return "PLAYER";
    }
    
}