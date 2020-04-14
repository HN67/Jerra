package jerra.view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import jerra.api.Visual;
import jerra.core.Rect;
import jerra.entity.DefaultCharacter;
import jerra.entity.Entity;
import jerra.entity.Player;
import jerra.room.Room;
import jerra.stats.StatsDisplay;

public class GraphicView extends View<Room> {

	private Map<String, Image> imageDictionary;

	private Map<Entity, Healthbar> healthbars;
	
	private Canvas canvas;
	
	public GraphicView(Room model, Canvas canvas, Map<String, Image> imageDictionary) {
		super(model);
		
		this.imageDictionary = imageDictionary;
		this.healthbars = new HashMap<Entity, Healthbar>();
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

		Image backgroundImage = this.imageDictionary.get("/resources/background.png");
		context.drawImage(backgroundImage, 0, 0);
		
		for(Visual entity : model.getVisuals()) {
			Rect pos = entity.getPosition();

			Image image = this.imageDictionary.get(entity.image());

			this.drawHealthbar(entity, context);
			
			// Align center of image and position
			context.drawImage(image, pos.centerX() - image.getWidth()/2, pos.centerY() - image.getHeight()/2);

			this.healthbars.entrySet().removeIf(healthbar -> healthbar.getValue().equals(null));
		}
	}

	/**
	 * Associates healthbars to entities and draws it in the graphics context.
	 * @param entity an Visual in the room
	 * @param context a context currently used by the canvas
	 */
	private void drawHealthbar(Visual visual, GraphicsContext context) {
		if(visual instanceof DefaultCharacter) {
			DefaultCharacter entity = (DefaultCharacter) visual;
			if(!this.healthbars.containsKey(entity)) {

				Color color = Color.RED;

				if(entity instanceof Player) {
					color = Color.GREEN;
				}

				StatsDisplay display = new StatsDisplay(entity.getStats(), 100);

				this.healthbars.put(entity, new Healthbar(
					(DefaultCharacter) entity, display, canvas, color
				));

				entity.getStats().setOnChangeValue(
				(event, type) -> 
				display.show()
				);
			
			}

			this.healthbars.get(entity).render();
			
		}
	}

	// public void show(Entity entity) {
	// 	this.healthbars.get(entity).show();
	// }

}
