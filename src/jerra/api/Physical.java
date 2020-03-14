package jerra.api;

import jerra.presence.Presence;

/**
 * Physical
 */
public interface Physical {

    public Presence getPresence();
    public void setPresence(Presence presence);
    public boolean collides(Physical other);
    public void deflect(Physical other);
    default public boolean solid() {
        return true;
    }

}
