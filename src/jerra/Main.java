package jerra;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jerra.control.Controller;
import jerra.control.RoomController;
import jerra.room.Room;
import jerra.room.TextRoom;

public class Main extends Application {
	
	Controller controller;
	
	@Override
    public void start(Stage primaryStage) {
				
		
		Group root = new Group();
		Scene s = new Scene(root, 625, 625, Color.BLACK);

		final Canvas canvas = new Canvas(625,625);
		 
		root.getChildren().add(canvas);
		
		primaryStage.setScene(s);
		primaryStage.show();
		
		Room room = new TextRoom();
		
		RoomController controller = new RoomController(room, canvas);
		// Controller controller = new TextController(room, new Scanner(System.in));
		controller.start();

		s.setOnKeyPressed(event -> controller.handle(event));
    }
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
