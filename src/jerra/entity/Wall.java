package jerra.entity;

import jerra.api.Physical;
import jerra.core.Rect;
import jerra.presence.Presence;

/**
 * Wall
 */
public class Wall extends DefaultEntity {
    
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
    public String symbol() {
        return "W";
    }

    @Override
    public void interact(Entity other) {
        super.interact(other);
    }

    @Override
    public boolean collides(Physical other) {
        return super.collides(other);
    }

    @Override
    public void deflect(Physical other) {
        if(!other.solid()) {
            return;
        }

        Rect otherPosition = other.getPresence().getPosition();

        // If another entity collides against the wall's right side,
        // then align the other entity's left side against 
        // the right side of the wall.
        if(
            otherPosition.left() <= this.getPresence().getPosition().right() &&
            otherPosition.left() > this.getPresence().getPosition().centerX() &&
            other.getPresence().getVelocity().x() < 0 &&
            otherPosition.right() > this.getPresence().getPosition().right()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignLeft(this.getPresence().getPosition().right())
            );  
        }

        // If another entity collides against the wall's left side,
        // then align the other entity's right side against 
        // the left side of the wall.
        if(
            otherPosition.right() >= this.getPresence().getPosition().left() &&
            otherPosition.right() < this.getPresence().getPosition().centerX() &&
            other.getPresence().getVelocity().x() > 0 &&
            otherPosition.left() < this.getPresence().getPosition().left()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignRight(this.getPresence().getPosition().left())
            );
        }

        // If another entity collides against the wall's top side,
        // then align the other entity's bottom side against 
        // the right top of the wall.
        if(
            otherPosition.bottom() >= this.getPresence().getPosition().top() && 
            otherPosition.bottom() < this.getPresence().getPosition().centerY() && 
            other.getPresence().getVelocity().y() > 0 &&
            otherPosition.top() < this.getPresence().getPosition().top()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignBottom(this.getPresence().getPosition().top())
            );
        }

        // If another entity collides against the wall's bottom side,
        // then align the other entity's top side against 
        // the bottom side of the wall.
        if(
            otherPosition.top() <= this.getPresence().getPosition().bottom() && 
            otherPosition.top() > this.getPresence().getPosition().centerY() &&
            other.getPresence().getVelocity().y() < 0 &&
            otherPosition.bottom() > this.getPresence().getPosition().bottom()
        ) {
            other.getPresence().setPosition(
                otherPosition.alignTop(this.getPresence().getPosition().bottom())
            );
        }
    }

    @Override
    public void kill(boolean dead) {
        // Unkillable!
    }
}