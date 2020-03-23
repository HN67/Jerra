package jerra.view;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import jerra.api.Visual;
import jerra.core.Rect;
import jerra.room.Room;
import jerra.stats.Character;
import jerra.stats.Stats.Type;
import javafx.scene.paint.Color;

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
		
		for(Visual entity : model.getVisuals()) {
			
			Rect pos = entity.getPosition();

			if (entity.symbol() == "P") {
				Character character = (Character) entity;
//				context.setFill(Color.WHITE);
//				context.fillRect(pos.left(), pos.centerY() - 25, pos.width(), 4);
				context.setFill(Color.GREEN);
				context.fillRect(pos.left(), pos.centerY() - 25, pos.width()*character.getStats().getValue(Type.HEALTH)/character.getStats().getValue(Type.VITALITY), 4);
			} else if (entity.symbol() == "E") {
				Character character = (Character) entity;
//				context.setFill(Color.WHITE);
//				context.fillRect(pos.left(), pos.centerY() - 25, pos.width(), 4);
				context.setFill(Color.RED);
				context.fillRect(pos.left(), pos.centerY() - 25, pos.width()*character.getStats().getValue(Type.HEALTH)/character.getStats().getValue(Type.VITALITY), 4);
			}

			Image image = this.imageDictionary.get(entity.image());
			
			// Align center of image and position
			context.drawImage(image, pos.centerX() - image.getWidth()/2, pos.centerY() - image.getHeight()/2);
		
		}
	}

}
