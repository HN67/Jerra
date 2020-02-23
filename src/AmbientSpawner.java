/**
 * Ambient Spawner, an object that periodically spawns Entities around its position.
 */
public class AmbientSpawner implements Spawner {

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area
     */
	private int frequency;
	private Entity entity;
	private Vector vector;
	private int range;
	
    public AmbientSpawner(Entity entity, Vector origin, int range) {
        // TODO
        // Save origin and range and entity
    	this.entity = entity;
    	this.vector = origin;
    	this.range = range;
    }

    @Override
    public boolean spawns(String command) {
        // TODO
        // Keep tick incrementer, return true every 10 ticks or something
        // maybe add frequency to constructor
    	this.frequency += 1;
    	if (this.frequency == 10) {
    		this.frequency = 0;
    		return true;
    	}
    	return false;
    }

    @Override
    public Entity spawn() {
        // TODO
        // copy the saved entity, set its position to something within the spawning area
        // and return it
        // may need to add methods to Rect/Vector (remember Rect/Vector is immutable)
    	int negative = (int)(Math.random()*(2));
    	int y = 0;
    	if (negative == 0) {
    		y = (int)(Math.random()*(this.range+1));
    	} else if(negative == 1) {
    		y = (int)(Math.random()*(this.range+1)*-1);
    	}
    	
    }

    @Override
    public boolean alive() {
        return true;
    }
    
}
