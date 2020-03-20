package jerra.effect;

import java.util.List;
import java.util.ArrayList;

import jerra.api.Physical;

import jerra.core.Vector;

public class AccelerationEffect implements Effect<Physical> {

	private static final long serialVersionUID = 0;

	private Vector acceleration;

	private List<Effect<Physical>> links;
	
	public AccelerationEffect(Vector acceleration) {
		this.acceleration = acceleration;
		this.links = new ArrayList<Effect<Physical>>();
	}
	
	@Override
	public AccelerationEffect combine(Effect<Physical> effect) {
		// Combines by maintaining a list of effects
		return this.copy().addLink(effect);
	}

	@Override
	public void apply(Physical entity) {
		entity.getPresence().setVelocity(entity.getPresence().getVelocity().add(this.getAcceleration()));
		// Apply every composed effect
		for (Effect<Physical> effect: this.links) {
			effect.apply(entity);
		}
	}

	@Override
	public AccelerationEffect copy() {
		return new AccelerationEffect(this.acceleration).setLinks(new ArrayList<Effect<Physical>>(this.links));
	}

	// Mutates, use with care
	private AccelerationEffect setLinks(ArrayList<Effect<Physical>> links) {
		this.links = links;
		return this;
	}

	// Mutates, use with care
	private AccelerationEffect addLink(Effect<Physical> link) {
		this.links.add(link);
		return this;
	}

	public void setAcceleration(Vector vector) {
		this.acceleration = vector;
		
	}

	public Vector getAcceleration() {
		return this.acceleration;
	}

}
