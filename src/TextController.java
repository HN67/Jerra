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
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(8, 0), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 4), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 8), new Vector(1, 1)), zero)));
        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(5, 5), new Vector(1, 1)), zero)));

        this.room.spawnSpawner(new AmbientSpawner(
            new DefaultEntity(new DefaultPresence(new Rect(0, 0, 1, 1), zero)),
            new Vector(4, 4), 
            1, 
            3
        ));

        this.room.spawnPlayer(new Player(new ActivePresence(new Rect(new Vector(0, 0), new Vector(1, 1)), new Vector(1, 1), "up", "down", "left", "right")));
        
        String command = "";

		while (!command.equals("quit")) {
			command = this.keyboard.nextLine();
			this.room.update(command);
            System.out.println(this.room);
            System.out.println(this.room.gridString());
		}

    }

}
