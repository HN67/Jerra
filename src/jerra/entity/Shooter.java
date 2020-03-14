package jerra.entity;

/**
 * Shooter
 */
public interface Shooter extends Entity, Spawner<Entity> {
    
    @Override
    public Shooter copy();

}
