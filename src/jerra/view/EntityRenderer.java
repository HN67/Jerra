package jerra.view;

import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import jerra.entity.Entity;
import jerra.core.Rect;
import jerra.core.Resources;

/**
 * EntityRenderer
 */
public class EntityRenderer extends View<Entity> {

    private String imagePath;
    private Image image;

    private Canvas canvas;

    public EntityRenderer(Entity entity, String imagePath, Canvas canvas) {
        // Save parameters, store entity as model
        super(entity);
        this.imagePath = imagePath;
        this.canvas = canvas;
        // Load image
        this.image = Resources.loadImage(this.imagePath);
    } 

    private GraphicsContext getContext() {
        return this.canvas.getGraphicsContext2D();
    }

    protected Image getImage() {
        return this.image;
    }

    @Override
    public void render() {

        Rect pos = this.getModel().getPosition();
        GraphicsContext context = this.getContext();
        
        // Align center of image and position
        context.drawImage(this.getImage(), pos.centerX() - this.getImage().getWidth()/2, pos.centerY() - this.getImage().getHeight()/2);
    
    }
    
}