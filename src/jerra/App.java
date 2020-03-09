package jerra;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jerra.control.Controller;
import jerra.control.GraphicController;
import jerra.core.Resources;
import jerra.room.Room;
import jerra.room.TextRoom;

public class App extends Application {
	
	private Group root;
	private Scene scene;
	private Canvas canvas;
	
	@Override
    public void start(Stage primaryStage) {
				
		
		this.root = new Group();
		this.scene = new Scene(root, 600, 600, Color.BLACK);
		this.canvas = new Canvas(600, 600);
		 
		root.getChildren().add(canvas);

		primaryStage.setTitle("Jerra");
		primaryStage.getIcons().add(Resources.loadImage(Resources.resourcePath("logo.png")));
		primaryStage.setScene(scene);
		primaryStage.show();

		canvas.requestFocus();
		
		Room room = new TextRoom();
		Controller controller = new GraphicController(room, canvas);
		
		controller.start();

    }

}
