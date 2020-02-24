/**
 * Effect Interface
 * An Effect represents a mutation that can be applied to an Entity
 */
public interface Effect {
	
	public Effect combine(Effect effect);
	
	public void apply(Entity entity);
	
	public void setDamage(int x);
	public int getDamage();
	
	public void setAcceleration(Vector vector);
	/**
	 * Acceleration represents the change to the Entity's velocity
	 */
	public Vector getAcceleration();

}
