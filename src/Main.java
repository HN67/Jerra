import java.util.Scanner;

// The main file
public class Main {
	public static void main(String[] args) {

		System.out.println("Hello");

		Room room = new TextRoom();
		room.add(new DefaultEntity(new Vector(1, 1), new Vector(0, 0)));
		room.add(new DefaultEntity(new Vector(3, -1), new Vector(0, 0)));
		room.add(new DefaultEntity(new Vector(5, -1), new Vector(0, 0)));
		room.add(new DefaultEntity(new Vector(1, -1), new Vector(0, 0)));
		room.add(new DefaultEntity(new Vector(3, 4), new Vector(0, 0)));
		
		room.add(new Player(new Vector(0, 0), new Vector(0, 0)));

		Scanner keyboard = new Scanner(System.in);

		String command = "";

		while (!command.equals("quit")) {
			command = keyboard.nextLine();
			room.update(command);
			System.out.println(room.toString());
		}

		keyboard.close();
		

	}
}
