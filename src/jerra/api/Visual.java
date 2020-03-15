package jerra.api;

import javafx.scene.image.Image;

import jerra.core.Rect;

/**
 * Visual, indicates that the object could be displayed
 */
public interface Visual {

    public Image image();

    public Rect getPosition();
    
}
