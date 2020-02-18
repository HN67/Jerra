
public interface Effect {
	
	public Effect combineEffect(Effect effect);
	
	public void applyEffect(Entity entity);
	
	public void setDamage(int x);
	public void getDamage();
	
	public void setAcceleration(int x);
	public void getAcceleration();
}
