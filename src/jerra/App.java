package jerra;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jerra.control.Controller;
import jerra.control.GraphicController;
import jerra.room.Room;
import jerra.room.TextRoom;

public class App extends Application {
	
	private Group root;
	private Scene scene;
	private Canvas canvas;
	
	@Override
    public void start(Stage primaryStage) {
				
		
		this.root = new Group();
		this.scene = new Scene(root, 625, 625, Color.BLACK);
		this.canvas = new Canvas(625,625);
		 
		root.getChildren().add(canvas);
		
		primaryStage.setScene(scene);
		primaryStage.show();

		canvas.requestFocus();
		
		Room room = new TextRoom();
		Controller controller = new GraphicController(room, canvas);
		
		controller.start();

    }

}
