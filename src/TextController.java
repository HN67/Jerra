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

        this.room.addEntity(new DefaultEntity(new Vector(1, 1), new Vector(0, 0)));
		this.room.addEntity(new DefaultEntity(new Vector(3, -1), new Vector(0, 0)));
		this.room.addEntity(new DefaultEntity(new Vector(5, -1), new Vector(0, 0)));
		this.room.addEntity(new DefaultEntity(new Vector(1, -1), new Vector(0, 0)));
        this.room.addEntity(new DefaultEntity(new Vector(3, 4), new Vector(0, 0)));
        this.room.addEntity(new Projectile(new Vector(-5, 0), new Vector(1, 0), 5));

        this.room.addPlayer(new Player(new Vector(0, 0), new Vector(0, 0)));
        
        String command = "";

		while (!command.equals("quit")) {
			command = this.keyboard.nextLine();
			this.room.update(command);
			System.out.println(this.room);
		}

    }
    
}