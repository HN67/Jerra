package jerra.entity;

import jerra.presence.Presence;

/**
 * Wall
 */
public class Wall extends DefaultEntity implements Deflects {
    
    public Wall(Presence presence) {
        super(presence);
    }

    @Override
    public String getName() {
        return "WALL (" + 
            this.getPosition().height()  + 
            "," +
            this.getPosition().width()  +  
            ")";
    }

    @Override
    public void interact(Entity other) {
        this.deflect(other);

        super.interact(other);
    }
}