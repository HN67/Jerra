package jerra.api;

import jerra.core.Rect;

/**
 * Visual, indicates that the object could be displayed
 */
public interface Visual {

    public String image();

    public Rect getPosition();
    
}
