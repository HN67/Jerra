package jerra.item;
import jerra.effect.DamageEffect;
import jerra.effect.Effect;
import jerra.stats.Character;
import jerra.stats.Stats;

/**
 * Medkit
 * puts you back in the fight.
 */

public class Medkit extends Item {

    private static final long serialVersionUID = 0L;

    private int healAmount = 5;
    private Effect<Character> heal = new DamageEffect(-healAmount);
    
    @Override
    public String name() {
        return "Medkit";
    }

    @Override
    public String description() {
        return "Get back into the fight.";
    }

    public void apply(Character character) {
        int totalVit = character.getStats().getValue(Stats.Type.VITALITY);
        if (character.getStats().getValue(Stats.Type.HEALTH) <= totalVit - healAmount) {
        heal.apply(character);
        }
        else {
            character.getStats().setValue(Stats.Type.HEALTH, totalVit);
        }
    }

    

}