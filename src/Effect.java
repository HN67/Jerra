
public interface Effect {
	
	public Effect combine(Effect effect);
	
	public void apply(Entity entity);
	
	public void setDamage(int x);
	public Damage getDamage();
	
	public void setAccl(Vector vector);
	public Vector getAccl();
}
