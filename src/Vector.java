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

    /**
     * Returns a new Vector equal to the the element-wise sum of this and the provided
     * @param other The second Vector to add with
     * @return The sum of this vector and the provided vector
     */
    public Vector add(Vector other) {
        return new Vector(this.x() + other.x(), this.y() + other.y());
    }

    /**
     * Returns a new Vector equal to the element-wise product of this and the provided
     * @param scale The second Vector to multiply with
     * @return The product of this vector and the provided vector
     */
    public Vector scale(Vector scale) {
        return new Vector(this.x() * scale.x(), this.y() * scale.y());
    }

    /**
     * Returns a new Vector equal to the element-wise product of this and the provided
     * @param x The scalar multiplied to x
     * @param y The scalar multiplied to y
     * @return The product of this vector and the provided vector
     */
    public Vector scale(int x, int y) {
        return this.scale(new Vector(x, y));
    }

    /**
     * Returns a new Vector which obtained by multiplying the current vector
     * object with a scalar
     * @param x The scalar
     * @return The product of this vector and the provided vector
     */
    public Vector scale(int scalar) {
        return this.scale(scalar, scalar);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
}
