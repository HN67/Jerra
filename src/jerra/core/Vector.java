package jerra.core;

import java.io.Serializable;

/**
 * Vector
 * 
 * @author Ryan Allard
 */
public class Vector implements Serializable {

    private static final long serialVersionUID = 0;

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

    /**
     * Returns a new Vector identical to this one, but with an updated x value.
     * This method does not affect the original Vector.
     * @param x a int, the new x coordinate of the returned Vector
     * @return a Vector, with the given x and this Vector's y
     */
    public Vector alignX(int x) {
        return new Vector(x, this.y());
    }

    /**
     * Returns a new Vector identical to this one, but with an updated y value.
     * This method does not affect the original Vector.
     * @param y a int, the new y coordinate of the returned Vector
     * @return a Vector, with the given y and this Vector's x
     */
    public Vector alignY(int y) {
        return new Vector(this.x(), y);
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
    
    public Vector scale(int scale) {
        return this.scale(scale, scale);
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

    /**
     * Returns a Vector containing the signs of this Vectors components
     * Sign of n is calculated as following: n < 0 -> -1, n > 0 -> +1, n = 0 -> 0
     * @return a Vector, equal to Vector(sign(this.x()), sign(this.y()))
     */
    public Vector sign() {
        return new Vector(sign(this.x()), sign(this.y()));
    }

    private static int sign(int num) {
        if (num > 0) {
            return 1;
        } else if (num < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Vector other) {
        return (this.x() == other.x() && this.y() == other.y());
    }
    
}
