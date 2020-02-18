
public interface Effect {
	public Effect combineEffect(Effect effect);
	public void applyEffect(Entity entity);
}
