import java.util.Scanner;

/**
 * TextController
 */
public class TextController implements Controller {

    private Room room;
    private Scanner keyboard;

    public TextController(Room room, Scanner keyboard) {
        this.room = room;
        this.keyboard = keyboard;
    }

    public void start() {
        Vector zero = new Vector(0, 0);

        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(4, 0), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(-4, 0), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 4), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, -4), new Vector(1, 1)), zero)));
        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(4, 4), new Vector(1, 1)), zero)));
        this.room.spawnEntity(new Bullet(new Vector(-3, 0), new Vector(1, 0)));

        // ActivePresence takes a base velocity, which is scaled off of (1, 1 in this case)
        this.room.spawnPlayer(new Player(new ActivePresence(new Rect(new Vector(0, 0), new Vector(1, 1)), new Vector(1, 1), "up", "down", "left", "right")));
        
        String command = "";

		while (!command.equals("quit")) {
			command = this.keyboard.nextLine();
			this.room.update(command);
			System.out.println(this.room);
		}

    }

}
