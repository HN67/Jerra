package jerra.core;

/**
 * Vector
 * @author Ryan Allard
 */
public class Vector {

    private final int x;
    private final int y;

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

    public Vector add(int x, int y) {
        return new Vector(this.x() + x, this.y() + y);
    }

    /**
     * Returns a new Vector equal to the the element-wise sum of this and the provided
     * @param other The second Vector to add with
     * @return The sum of this vector and the provided vector
     */
    public Vector add(Vector other) {
        return this.add(other.x(), other.y());
    }

    public Vector subtract(int x, int y) {
        return new Vector(this.x() - x, this.y() - y);
    }

    /**
     * Returns a new Vector equal to the element-wise difference of this and the provided
     * @param other The second vector to subtract with
     * @return The difference between this Vector and the other Vector (this - other)
     */
    public Vector subtract(Vector other) {
        return this.subtract(other.x(), other.y());
    }

    public Vector scale(int x, int y) {
        return new Vector(this.x() * x, this.y() * y);
    }

    /**
     * Returns a new Vector equal to the element-wise product of this and the provided
     * @param scale The second Vector to multiply with
     * @return The product of this vector and the provided vector
     */
    public Vector scale(Vector scale) {
        return this.scale(scale.x(), scale.y());
    }

    /**
     * Returns the additive negative of this Vector.
     * Vector + Vector.negate() = 0
     * @return a Vector, the opposite of this Vector
     */
    public Vector negate() {
        return this.scale(-1, -1);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Vector other) {
        return (this.x() == other.x() && this.y() == other.y());
    }
    
}
