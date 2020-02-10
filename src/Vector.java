/**
 * Vector
 * @author Ryan Allard
 */
public class Vector {

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
        this.x = other.x();
        this.y = other.y();
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public Vector add(Vector other) {
        return new Vector(this.x() + other.x(), this.y() + other.y());
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
}
