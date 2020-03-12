package jerra.api;

import jerra.effect.Effect;
import jerra.entity.Entity;
import jerra.presence.Presence;

/**
 * Physical
 */
public interface Physical {

    public Presence getPresence();
    public void setPresence(Presence presence);
    public boolean collides(Physical other);
    public void deflect(Physical other);

    public void hit();
    public void hit(Effect<? super Entity> effect);
    public void unHit();
    public boolean isHit();

    default public boolean solid() {
        return true;
    }

}
