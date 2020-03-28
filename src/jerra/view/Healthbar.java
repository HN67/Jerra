package jerra.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jerra.entity.DefaultCharacter;
import jerra.stats.Stats.Type;

/**
 * A view that renders an DefaultCharacter's Health as a healthbar.
 */
class Healthbar extends View<DefaultCharacter> {

    private int tick = 0;
    private Color color;
    private Canvas canvas;
    private boolean show;
    private int hideAfter = -1;

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
    public Healthbar(DefaultCharacter model, Canvas canvas, Color color, int hideAfter) {
        this(model, canvas, color);
        this.setHideAfter(hideAfter);
    }

    public Healthbar(DefaultCharacter model, Canvas canvas, Color color) {
        super(model);

        this.canvas = canvas;
        this.color = color;
    }

    public Canvas getCanvas() {
		return this.canvas;
    }

    @Override
    public void render() {
        if(!this.show) {
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
        this.tick++;

        if(this.hideAfter > 0 && this.tick > this.hideAfter) {
            this.tick = 0;
            this.hide();
        }
    }

    /**
     * Sets the instance variable show as true.
     * If it is, this ensures that the healthbar is shown
     * when it is rendered.
     */
    public void show() {
        this.show = true;
    }

    /**
     * Sets the instance variable show as false;
     * If it is, this ensures that the healthbar is hidden
     * when it is rendered.
     */
    public void hide() {
        this.show = false;
    }

    /**
     * Sets the instance variable hideAfter;
     * @param hideAfter
     */
    public void setHideAfter(int hideAfter) {
        this.hideAfter = hideAfter;
    }
    
}