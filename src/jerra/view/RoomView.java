package jerra.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jerra.core.Rect;
import jerra.core.Vector;
import jerra.entity.Entity;
import jerra.room.Room;

public class RoomView extends View<Room> {
	
	private Canvas canvas;
	
	public RoomView(Room model, Canvas canvas) {
		super(model);
		
		this.canvas = canvas;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	private static Color getColor(String symbol) {
		Color color = Color.RED;
		
		switch(symbol) {
			case "P":
				color = Color.BLUE;
				break;
			
			case "B": 
				color = Color.GREEN;
				
		}
		
		return color;
	}

	@Override
	public void render() {
		Room model = this.getModel();
		Canvas canvas = this.getCanvas();
		GraphicsContext context = canvas.getGraphicsContext2D();

		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		int scale = 25;
		
		for(Entity entity : model.getEntities()) {
			
			Rect rect = entity.getPosition();
			Vector origin = entity.getPosition().getOrigin().scale(scale);
			int width = rect.width() * scale;
			int height = rect.height() * scale;
			
			context.setFill(getColor(entity.symbol()));
			context.fillRect(
				origin.x(),
				origin.y(),
				width,
				height
			);
		}
	}

}
