package jerra.effect;

import java.util.List;
import java.util.ArrayList;

import jerra.stats.Stats;
import jerra.stats.Character;

public class DamageEffect implements Effect<Character> {

	private static final long serialVersionUID = 0;

	private int damage;

	private List<Effect<Character>> links;
	
	public DamageEffect(int damage) {
		this.damage = damage;
		this.links = new ArrayList<Effect<Character>>();
	}
	
	@Override
	public DamageEffect combine(Effect<Character> effect) {
		// Combines by maintaining a list of effects
		return this.copy().addLink(effect);
	}

	@Override
	public void apply(Character entity) {
		entity.getStats().changeValue(Stats.Type.HEALTH, -this.damage);
		// Apply every composed effect
		for (Effect<Character> effect: this.links) {
			effect.apply(entity);
		}
	}

	@Override
	public DamageEffect copy() {
		return new DamageEffect(this.damage).setLinks(new ArrayList<Effect<Character>>(this.links));
	}

	// Mutates, use with care
	private DamageEffect setLinks(ArrayList<Effect<Character>> links) {
		this.links = links;
		return this;
	}

	// Mutates, use with care
	private DamageEffect addLink(Effect<Character> link) {
		this.links.add(link);
		return this;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return this.damage;
	}

}
