package jerra.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jerra.core.Rect;
import jerra.core.Vector;
import jerra.entity.Entity;
import jerra.room.Room;

public class FxView extends View {
	
	private GraphicsContext context;
	
	public FxView(Room model, GraphicsContext context) {
		super(model);
		
		this.context = context;
	}
	
	public GraphicsContext getContext() {
		return this.context;
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
		GraphicsContext context = this.getContext();
		
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
