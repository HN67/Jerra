/**
 * Vector
 * @author Ryan Allard
 */
public class Vector implements InterfaceVector {

    private int x;
    private int y;

    public Vector() {
        this(0, 0);
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
}
