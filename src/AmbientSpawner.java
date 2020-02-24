import java.lang.Math;

/**
 * Ambient Spawner, an object that periodically spawns Entities around its position.
 */
public class AmbientSpawner implements Spawner {
    
    private int period;
	private int tick;
	private Entity entity;
	private Vector origin;
	private int range;

    /**
     * Constructs an AmbientSpawner that spawns copies of the given entity at nearby positions
     * @param entity a Entity, this spawner creates copies of this Entity
     * @param origin a Vector, the center of the spawning area
     * @param range a int, the radius of the spawning area. Specifically, the origin coordinate
     * of the spawned entity is <= origin -+ range.
     * @param period, a int, the Entity is spawned every this many ticks
     */
    public AmbientSpawner(Entity entity, Vector origin, int range, int period) {
        // Save origin and range and entity
    	this.entity = entity;
    	this.origin = origin;
        this.range = range;
        // Initalize tick and period
        this.tick = 0;
        this.period = period;
    }

    @Override
    public boolean spawns(String command) {
        // Count the number of ticks, return true every period amount
    	this.tick += 1;
    	if (this.tick >= period) {
    		this.tick = 0;
    		return true;
    	} else {
            return false;
        }
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
    	negative = (int)(Math.random()*(2));
    	int x = 0;
    	if (negative == 0) {
    		x = (int)(Math.random()*(this.range+1));
    	} else if(negative == 1) {
    		x = (int)(Math.random()*(this.range+1)*-1);
    	}
    	Entity newEntity = this.entity.copy();
    	newEntity.getPresence().setPosition(origin);
    	newEntity.getPresence().getOrigin().add(x, y);
    	return newEntity;
    }

    @Override
    public boolean alive() {
        return true;
    }
    
}
