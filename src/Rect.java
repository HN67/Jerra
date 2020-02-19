/**
 * Rect object that represents a rectangular bounding area
 */
public class Rect {

    private Vector origin;
    private Vector size;

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

    public boolean intersects(Rect other) {
        return 
            (other.left() < this.right())
            && (other.right() > this.left())
            && (other.top() < this.bottom())
            && (other.bottom() > this.top())
        ;
    }

    public String toString() {
        return "[" + this.topleft().toString() + " to " + this.bottomright().toString() + "]";
    }
    
}