package jerra.entity;

import jerra.core.Rect;
import jerra.presence.Presence;

/**
 * Wall
 */
public class Wall extends DefaultEntity{
    public Wall(Presence presence) {
        super(presence);
    }

    @Override
    public String getName() {
        return "WALL";
    }

    @Override
    public void interact(Entity other) {
        // Stop other entity.
        other.getPresence().getVelocity().scale(0);

        Rect otherPosition = other.getPosition();

        if(
            otherPosition.left() <= this.getPosition().right() &&
            otherPosition.left() > this.getPosition().centerX() &&
            other.getPresence().getVelocity().x() < 0
        ) {
            other.getPresence().setPosition(
                otherPosition.alignLeft(this.getPosition().right())
            );  
            return;
        }

        if(
            otherPosition.right() >= this.getPosition().left() &&
            otherPosition.right() < this.getPosition().centerX() &&
            other.getPresence().getVelocity().x() > 0
        ) {
            System.out.println("aligntt right");
            other.getPresence().setPosition(
                otherPosition.alignRight(this.getPosition().left())
            );
            return;
        }

        if(
            otherPosition.bottom() >= this.getPosition().top() && 
            otherPosition.bottom() < this.getPosition().centerY()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignBottom(this.getPosition().top())
            );
            return;
        }

        if(
            otherPosition.top() <= this.getPosition().bottom() && 
            otherPosition.top() > this.getPosition().centerY()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignTop(this.getPosition().bottom())
            );
            return;
        }

        
    }
}