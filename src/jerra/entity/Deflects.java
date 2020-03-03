package jerra.entity;

import jerra.core.Rect;

public interface Deflects extends Entity {

    /**
     * Aligns another entity against this object's side where
     * the entity was approaching towards. For example, if an entity is approaching
     * this object's left side. The entity's right is then aligned to this
     * object's left side.
     * 
     * @param other an Entity, that is approaching this object.
     */
    default void deflect(Entity other) {
        // Stop the other entity.
        other.getPresence().getVelocity().scale(0);

        Rect otherPosition = other.getPosition();

        // If another entity collides against the wall's right side,
        // then align the other entity's left side against 
        // the right side of the wall.
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

        // If another entity collides against the wall's left side,
        // then align the other entity's right side against 
        // the left side of the wall.
        if(
            otherPosition.right() >= this.getPosition().left() &&
            otherPosition.right() < this.getPosition().centerX() &&
            other.getPresence().getVelocity().x() > 0
        ) {
            other.getPresence().setPosition(
                otherPosition.alignRight(this.getPosition().left())
            );
            return;
        }

        // If another entity collides against the wall's top side,
        // then align the other entity's bottom side against 
        // the right top of the wall.
        if(
            otherPosition.bottom() >= this.getPosition().top() && 
            otherPosition.bottom() < this.getPosition().centerY() && 
            other.getPresence().getVelocity().y() > 0
        ) {
            other.getPresence().setPosition(
                otherPosition.alignBottom(this.getPosition().top())
            );
            return;
        }

        // If another entity collides against the wall's bottom side,
        // then align the other entity's top side against 
        // the bottom side of the wall.
        if(
            otherPosition.top() <= this.getPosition().bottom() && 
            otherPosition.top() > this.getPosition().centerY() &&
            other.getPresence().getVelocity().y() < 0
        ) {
            other.getPresence().setPosition(
                otherPosition.alignTop(this.getPosition().bottom())
            );
            return;
        }
    }

    /**
     * Prevents another entity from going through the wall.
     */
    @Override
    default void interact(Entity other) {
        this.deflect(other);
    }
}