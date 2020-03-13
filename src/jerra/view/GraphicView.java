package jerra.view;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import jerra.core.Rect;
import jerra.entity.Entity;
import jerra.room.Room;

public class GraphicView extends View<Room> {

	private Map<String, Image> imageDictionary;
	
	private Canvas canvas;
	
	public GraphicView(Room model, Canvas canvas, Map<String, Image> imageDictionary) {
		super(model);
		
		this.canvas = canvas;
		this.imageDictionary = imageDictionary;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	// private static Color getColor(String symbol) {
	// 	Color color = Color.RED;
		
	// 	switch(symbol) {
	// 		case "P":
	// 			color = Color.BLUE;
	// 			break;
			
	// 		case "B": 
	// 			color = Color.GREEN;
	// 			break;

	// 		case "W":
	// 			color = Color.YELLOW;
	// 	}
		
	// 	return color;
	// }

	@Override
	public void render() {
		Room model = this.getModel();
		Canvas canvas = this.getCanvas();
		GraphicsContext context = canvas.getGraphicsContext2D();

		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for(Entity entity : model.getEntities()) {
			
			Rect pos = entity.getPosition();
			Image image = this.imageDictionary.get(entity.image());
			
			// Align center of image and position
			context.drawImage(image, pos.centerX() - image.getWidth()/2, pos.centerY() - image.getHeight()/2);
		
		}
	}

}
