package jerra.entity;

import jerra.api.Copyable;
import jerra.api.Updatable;
import jerra.api.Interactive;
import jerra.api.Mortal;
import jerra.api.Physical;
import jerra.api.Affiliate;

import jerra.stats.Character;

import jerra.core.Rect;

/**
 * Entity
 */
public interface Entity extends Updatable, Interactive, Mortal, Physical, Affiliate, Character, Copyable<Entity> {

    public Rect getPosition();

    public void interact(Entity other);

    @Override
    public String toString();
    public String symbol();
    
}