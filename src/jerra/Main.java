package jerra;

import java.util.Scanner;

import jerra.room.Room;
import jerra.room.TextRoom;

import jerra.control.Controller;
import jerra.control.TextController;

// The main file
public class Main {
	public static void main(String[] args) {

		//this.getClass().getClassLoader().getResourceAsStream(resources/<resource>)

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
