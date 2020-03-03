package jerra.effect;

import jerra.core.Vector;
import jerra.entity.Entity;

public class ObjectEffect implements Effect{
	private int damage;
	private Vector acceleration;
	
	public ObjectEffect(int damage, Vector acceleration) {
		this.damage = damage;
		this.acceleration = acceleration;
	}
	
	public Effect combine(Effect effect) {
		// TODO Auto-generated method stub
		return new ObjectEffect(this.damage + effect.getDamage(), effect.getAcceleration().add(this.acceleration));
		
	}


	@Override
	public void apply(Entity entity) {
		entity.getPresence().setVelocity(entity.getPresence().getVelocity().add(this.getAcceleration()));
		
	}

	@Override
	public void setDamage(int x) {
		this.damage = x;
		
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void setAcceleration(Vector vector) {
		// TODO Auto-generated method stub
		this.acceleration = vector;
		
	}

	@Override
	public Vector getAcceleration() {
		// TODO Auto-generated method stub
		return this.acceleration;
	}

}
