package jerra.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import jerra.core.Rect;
import jerra.entity.Entity;
import jerra.entity.Wall;
import jerra.room.Room;

public class GraphicView extends View<Room> {
	
	private Canvas canvas;
	
	public GraphicView(Room model, Canvas canvas) {
		super(model);
		
		this.canvas = canvas;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}

	@Override
	public void render() {
		Room model = this.getModel();
		Canvas canvas = this.getCanvas();
		GraphicsContext context = canvas.getGraphicsContext2D();

		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for(Entity entity : model.getEntities()) {

			Rect pos = entity.getPosition();
			Image image = entity.image();
			
			context.setFill(new ImagePattern(image));

			if(entity.isHit() && !(entity instanceof Wall)) {
				context.setFill(Color.YELLOW);
			}

			context.fillRect(pos.x(), pos.y(), pos.width(), pos.height());
		
		}
	}

}
