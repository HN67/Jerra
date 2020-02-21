/**
 * Rect object that represents a rectangular bounding area.
 * Rects use a coordinate system with a top-left zero origin, so down and right are positive directions
 * A Rect inhabits the space between its left and right edges, and top and bottom edges.
 * Edges are pre-indexed before the pixels,
 * so a Rect with an origin of (1, 1) and size of (1, 1) (i.e. a single pixel) has a right and bottom edge of 2,
 * but will not intersect with a Rect at 2.
 */
public class Rect {

    private Vector origin;
    private Vector size;

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
     * Constructs a Rect that is identical to the given one
     * @param other a Rect, to be copied
     */
    public Rect(Rect other) {
        this.origin = new Vector(other.origin);
        this.size = new Vector(other.size);
    }

    public Vector getOrigin() {
        return this.origin;
    }

    public Vector getSize() {
        return this.size;
    }

    public int x() {
        return origin.x();
    }

    public int y() {
        return origin.y();
    }

    public int width() {
        return size.x();
    }

    public int height() {
        return size.y();
    }

    public int top() {
        return origin.y();
    }

    public int left() {
        return origin.x();
    }

    public int right() {
        return origin.x() + this.width();
    }

    public int bottom() {
        return origin.y() + this.height();
    }

    public Vector topleft() {
        return this.origin;
    }

    public Vector bottomright() {
        return this.origin.add(this.size);
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