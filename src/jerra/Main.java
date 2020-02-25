package jerra;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import jerra.room.Room;
import jerra.room.TextRoom;

import jerra.control.Controller;
import jerra.control.RoomController;

public class Main extends Application {
	
	Controller controller;
	
	@Override
    public void start(Stage primaryStage) {
				
		
		Group root = new Group();
		Scene s = new Scene(root, 625, 625, Color.BLACK);

		final Canvas canvas = new Canvas(625,625);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		 
		root.getChildren().add(canvas);
		
		primaryStage.setScene(s);
		primaryStage.show();
		
		Room room = new TextRoom();
		
		Controller controller = new RoomController(room, "placeholder", gc);
		controller.start();
    }
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
