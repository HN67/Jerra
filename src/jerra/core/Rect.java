package jerra.core;

import java.io.Serializable;

/**
 * Rect object that represents a rectangular bounding area. Rects use a
 * coordinate system with a top-left zero origin, so down and right are positive
 * directions A Rect inhabits the space between its left and right edges, and
 * top and bottom edges. Edges are pre-indexed before the pixels, so a Rect with
 * an origin of (1, 1) and size of (1, 1) (i.e. a single pixel) has a right and
 * bottom edge of 2, but will not intersect with a Rect at 2.
 */
public class Rect implements Serializable {

    private static final long serialVersionUID = 0;
    
    private final Vector origin;
    private final Vector size;

    /**
     * Constructs a Rect using an origin (top left) and size (width / height).
     * Coordinate system is positive down-right
     * @param origin a Vector, the top left origin
     * @param size a Vector, the size, where x is width and y is height
     */
    public Rect(Vector origin, Vector size) {
        this.origin = origin;
        this.size = size;
    }

    /**
     * Constructs a Rect with an origin at (x, y) and size of (width, height).
     * @param x a int, the x coordinate of this Rect's origin
     * @param y a int, the y coordinate of this Rect's origin
     * @param width a int, the width of this Rect's size
     * @param height a int, the height of this Rect's size
     */
    public Rect(int x, int y, int width, int height) {
        this(new Vector(x, y), new Vector(width, height));
    }

    /**
     * Constructs a Rect that is identical to the given one
     * @param other a Rect, to be copied
     */
    public Rect(Rect other) {
        this.origin = new Vector(other.origin);
        this.size = new Vector(other.size);
    }

    /**
     * Named constructor to make a Rect using corners
     * @param topleft a Vector, the coordinates of this Rect's top left corner
     * @param bottomright a Vector, the coordinates of this Rect's bottom right corner
     * @return a Rect, constructed based on topleft and bottomright corners
     */
    public static Rect fromCorners(Vector topleft, Vector bottomright) {
        return new Rect(topleft, bottomright.subtract(topleft));
    }

    /**
     * Named constructor to make a Rect using edge coordinates
     * @param left a int, the x coordinate of the left edge
     * @param top a int, the y coordinate of the top edge
     * @param right a int, the x coordinate of the right edge
     * @param bottom a int, the y coordinate of the bottom edge
     * @return a Rect, constructed based on given edge coordinates
     */
    public static Rect fromEdges(int left, int top, int right, int bottom) {
        return fromCorners(new Vector(left, top), new Vector(right, bottom));
    }
    
    /**
     * Returns the origin of this Rect, identical to the top left corner
     * @return a Vector, represents the x,y of the Rect origin / top left
     */
    public Vector getOrigin() {
        return this.origin;
    }

    /**
     * Returns the size of this Rect
     * Note that left + width = right, and top + height = bottom
     * @return a Vector, whose x is the width, and y the height of this Rect
     */
    public Vector getSize() {
        return this.size;
    }

    /**
     * Returns the x coordinate of this Rect's origin.
     * Identical to Rect.getOrigin().x()
     * Also identical to the left edge of the Rect.
     * @return a int, the x coordinate of Rect origin
     */
    public int x() {
        return origin.x();
    }

    /**
     * Returns the y coordinate of this Rect's origin.
     * Identical to Rect.getOrigin().y()
     * Also identical to the top edge of the Rect
     * @return a int, the y coordinate of Rect origin.
     */
    public int y() {
        return origin.y();
    }

    /**
     * Returns the width of this Rect.
     * left + width = right.
     * @return a int, the number of pixels this Rect occupies horizontally
     */
    public int width() {
        return size.x();
    }

    /**
     * Returns the height of this Rect.
     * top + height = bottom
     * @return a int, the number of pixels this Rect occupies vertically
     */
    public int height() {
        return size.y();
    }

    /**
     * Returns the y coordinate of the top edge of this Rect.
     * The bounded area of this Rect is below this line.
     * @return a int, the y coordinate of this Rect's top edge.
     */
    public int top() {
        return origin.y();
    }

    /**
     * Returns the x coordinate of the left edge of this Rect.
     * The bounded area of this Rect is to the right of this line.
     * @return a int, the x coordinate of this Rect's left edge.
     */
    public int left() {
        return origin.x();
    }

    /**
     * Returns the x coordinate of the right edge of this Rect.
     * The bounded area of this Rect is to the left of this line.
     * @return a int, the x coordinate of this Rect's right edge.
     */
    public int right() {
        return origin.x() + this.width();
    }

    /**
     * Returns the y coordinate of the bottom edge of this Rect.
     * The bounded area of this Rect above this line.
     * @return a int, the y coordinate of this Rect's bottom edge.
     */
    public int bottom() {
        return origin.y() + this.height();
    }

    /**
     * Returns the topleft point of this Rect.
     * Has the same data as top and left methods
     * Identical to the origin of the Rect.
     * @return a Vector, whose x is the left edge and y is top edge of this Rect.
     */
    public Vector topleft() {
        return this.origin;
    }

    /**
     * Returns the bottomright point of this Rect.
     * Has the same data as bottom and right methods
     * Identical to the origin of the Rect plus the size.
     * @return a Vector, whose x is the right edge and y is bottom edge of this Rect.
     */
    public Vector bottomright() {
        return this.origin.add(this.size);
    }

    /**
     * Returns the center of this Rect as a Vector.
     * Has data identical to centerX and centerY calls, packaged into a Vector
     * @return a Vector, the average of this Rect's edges, truncated
     */
    public Vector center() {
        return new Vector(this.centerX(), this.centerY());
    }

    /**
     * Returns the x coordinate of the center of this Rect.
     * May behave strangly for small Rects
     * @return a int, the average of the left and right edge of this Rect, truncated
     */
    public int centerX() {
        return (this.left() + this.right()) / 2;
    }

    /**
     * Returns the y coordinate of the center of this Rect.
     * May behave strangly for small Rects
     * @return a int, the average of the top and bottom edge of this Rect, truncated
     */
    public int centerY() {
        return (this.top() + this.bottom()) / 2;
    }

    /**
     * Checks if there is an intersection between this Rect and the other Rect
     * If the two Rects share an edge (e.g. left and right) they are not considered overlapping.
     * @param other a Rect, the other Rect to check for an intersection with
     * @return a boolean, true if there is an intersection
     */
    public boolean intersects(Rect other) {
        // All four of the following conditions must be true for there to be an intersection
        // If one is false, then they are seperated on the respective side
        return 
            (other.left() < this.right())
            && (other.right() > this.left())
            && (other.top() < this.bottom())
            && (other.bottom() > this.top())
        ;
    }

    /**
     * Checks if a Vector is contained by this Rect.
     * The bottom and right edge of the Rect is not considered contained by the Rect
     * This method should act the same as calling intersects with a 1x1 point
     * @param point a vector, the point checked if it is contained by this Rect. Should not be null 
     * @return a boolean, true if this Rect contains the point
     */
    public boolean contains(Vector point) {
        // Almost identical logic to intersects method, checks if point is contained by the bounds
        // Bottom right edges are not contained
        return 
            (point.x() < this.right())
            && (point.x() >= this.left())
            && (point.y() < this.bottom())
            && (point.y() >= this.top())
        ;
    }

    /**
     * Returns a new Rect displaced from this Rect by the given displacements.
     * The displacements are applied to the origin of the Rect.
     * Does not modify this Rect.
     * @param x a int, the horizontal displacement (can be any number, negative, zero, or positive)
     * @param y a int, the vertical displacement (can also be any number)
     * @return a new Rect with the applied displacement
     */
    public Rect move(int x, int y) {
        return new Rect(this.getOrigin().add(x, y), this.getSize());
    }

    /**
     * Returns a new Rect identical to this one, but with a new top edge.
     * The size of the Rect is maintained, the bototm edge is changed appropriately.
     * @param edge a int, the new coordinate for the top edge
     * @return a Rect, with the same size and left/right edges as this one, but with a new top edge
     */
    public Rect alignTop(int edge) {
        // Aligns the origin y, maintains size
        return new Rect(this.getOrigin().alignY(edge), this.getSize());
    }

    /**
     * Returns a new Rect identical to this one, but with a new bottom edge.
     * The size of the rect is maintained, the top edge is changed appropriately.
     * @param edge a int, the new coordinate for the bottom edge
     * @return a Rect, with the same size and left/right edges as this one, but with a new bottom edge
     */
    public Rect alignBottom(int edge) {
        // Aligns the orign y with the new bottom - size.y
        return new Rect(this.getOrigin().alignY(edge - this.getSize().y()), this.getSize());
    }

    /**
     * Returns a new Rect identical to this one, but with a new left edge.
     * The size of the rect is maintained, the right edge is changed appropriately.
     * @param edge a int, the new coordinate for the left edge
     * @return a Rect, with the same size and top/bottom edges as this one, but with a new left edge
     */
    public Rect alignLeft(int edge) {
        // Aligns the origin x, maintains size
        return new Rect(this.getOrigin().alignX(edge), this.getSize());
    }

    /**
     * Returns a new Rect identical to this one, but with a new right edge.
     * The size of the rect is maintained, the left edge is changed appropriately.
     * @param edge a int, the new coordinate for the right edge
     * @return a Rect, with the same size and top/bottom edges as this one, but with a new right edge
     */
    public Rect alignRight(int edge) {
        // Aligns the origin x with the new right - size.x
        return new Rect(this.getOrigin().alignX(edge - this.getSize().x()), this.getSize());
    }

    /**
     * Returns a new Rect displaced from this Rect by the given Vector displacement
     * The origin of this Rect is displaced by the given Vector.
     * Does not modify this Rect.
     * @param displacement a Vector, containing the horizontal and vertical displacement
     * @return a new Rect with the applied displacement
     */
    public Rect move(Vector displacement) {
        return move(displacement.x(), displacement.y());
    }

    public String toString() {
        return "[" + this.topleft().toString() + " to " + this.bottomright().toString() + "]";
    }
    
}