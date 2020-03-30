package jerra.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jerra.entity.DefaultCharacter;
import jerra.stats.Stats.Type;
import jerra.stats.StatsDisplay;

/**
 * A view that renders an DefaultCharacter's Health as a healthbar.
 */
class Healthbar extends View<DefaultCharacter> {

    private StatsDisplay stats;
    private Color color;
    private Canvas canvas;

    /**
     * Creates a new healthbar
     * @param model a DefaultCharacter, whose health must be rendered 
     *  as a healthbar
     * @param canvas a Canvas, where the healthbar should be drawn
     * @param color a Color, the color of the healthbar
     * @param hideAfter an int, number of ticks it takes to hide the healthbar 
     *  after it is shown; negativenumbers enable the healthbar to 
     *  never be hidden after it is shown
     */
    public Healthbar(DefaultCharacter model, StatsDisplay stats, Canvas canvas, Color color) {
        super(model);

        this.stats = stats;
        this.canvas = canvas;
        this.color = color;
    }

    public Canvas getCanvas() {
		return this.canvas;
    }

    @Override
    public void render() {
        if(!this.stats.visible()) {
            return;
        }

        DefaultCharacter entity = this.getModel();
        Canvas canvas = this.getCanvas();
        GraphicsContext context = canvas.getGraphicsContext2D();

        DefaultCharacter character = entity;

        context.setFill(this.color);
        if(character.getStats().getValue(Type.VITALITY) == 0) {
            return;
        }

        context.fillRect(entity.getPosition().left(), entity.getPosition().centerY() - 25, entity.getPosition().width()*character.getStats().getValue(Type.HEALTH)/character.getStats().getValue(Type.VITALITY), 4);
        this.stats.tick();
    }
    
}