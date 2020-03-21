package jerra.effect;

import java.io.Serializable;

import jerra.api.Copyable;

/**
 * Effect Interface
 * An Effect represents a mutation that can be applied to an Entity.
 */
public interface Effect<Target> extends Copyable<Effect<Target>>, Serializable {
	
	/**
	 * Combines the two Effects.
	 * Specifically, applying a combined Effect should have the same behavior as
	 * applying this Effect first and then the other effect.
	 * Should usually not modify this Effect.
	 * @param other an Effect, to be composed on top of this Effect
	 * @return a Effect, the combination of the two Effects
	 */
	public Effect<Target> combine(Effect<Target> other);
	
	/**
	 * Applies this Effect to its Target
	 * @param target a Target, will be modified based on this Effect
	 */
	public void apply(Target target);

}
