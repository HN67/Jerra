package jerra.api;

/**
 * Affiliate interface, indicates the object belongs to a team
 * Based off of a char team system
 */
public interface Affiliate {

    /**
     * Returns the team of this Affiliate
     * @return a char, each unique char is a unique team
     */
    public char getTeam();
    /**
     * Sets the team of this Affiliate
     * @param team a char, each unique char is a unique team
     * @return a Affiliate, this Affiliate to allow chaining
     */
    public Affiliate setTeam(char team);
    /**
     * Checks if the two Affiliates are on the same team.
     * Usually checks if their team is the same
     * @param other a Affiliate, check between this Affiliate and the other Affiliate
     * @return a boolean, true if the two Affiliates are on the same team
     */
    public boolean friendly(Affiliate other);
    
}
