import java.util.Scanner;
import java.io.File;

// The main file
public class Main {
	public static void main(String[] args) {

		// Create keyboard scanner
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Hello");

		Room room = new TextRoom();
		Controller controller = new TextController(room, keyboard);
		controller.start();

		// Collapse scanner
		keyboard.close();
		
	}
}
