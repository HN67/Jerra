package tests;

import org.junit.Assert;
import org.junit.Test;

import jerra.core.Vector;
import jerra.core.Rect;

/**
 * Test Class for testing the Rect class.
 */
public class RectTest {

    @Test
    public void test_EdgeConstructor_NormalRect() {

        Rect rect = Rect.fromEdges(3, 1, 5, 8);
        Assert.assertEquals("Created rect from (3, 1, 5, 8) edges, expecting origin x at 3", 3, rect.x());
        Assert.assertEquals("Created rect from (3, 1, 5, 8) edges, expecting origin y at 1", 1, rect.y());
        Assert.assertEquals("Created rect from (3, 1, 5, 8) edges, expecting width of 2", 2, rect.width());
        Assert.assertEquals("Created rect from (3, 1, 5, 8) edges, expecting height of 7", 7, rect.height());

    }

    @Test
    public void test_AlignRight_NormalRect() {

        Rect rect = new Rect(0, 0, 5, 5).alignRight(2);
        Assert.assertEquals("Align 5-wide rect right edge at 2, expecting left edge at -3", -3, rect.left());
        Assert.assertEquals("Align 5-wide rect right edge at 2, expecting right edge at 2", 2, rect.right());
        Assert.assertEquals("Align 0-5 height rect right edge at 2, expecting top edge at 0", 0, rect.top());
        Assert.assertEquals("Align 0-5 height rect right edge at 2, expecting bottom edge at 5", 5, rect.bottom());
        
    }

    @Test
    public void test_Contains_InnerPoint() {

        Rect rect = Rect.fromEdges(1, 1, 5, 5);
        Assert.assertEquals("Check if (3, 3) is inside a (1, 1, 5, 5) edges, expect true", true, rect.contains(new Vector(3, 3)));

    }

    @Test
    public void test_Contains_OuterPoint() {

        Rect rect = Rect.fromEdges(1, 1, 5, 5);
        Assert.assertEquals("Check if (7, 3) is inside a (1, 1, 5, 5) edges, expect false", false, rect.contains(new Vector(7, 3)));

    }

    @Test
    public void test_Contains_LeftEdge() {

        Rect rect = Rect.fromEdges(1, 1, 5, 5);
        Assert.assertEquals("Check if (1, 3) is inside a (1, 1, 5, 5) edges, expect true", true, rect.contains(new Vector(1, 3)));

    }

    @Test
    public void test_Contains_RightEdge() {

        Rect rect = Rect.fromEdges(1, 1, 5, 5);
        Assert.assertEquals("Check if (5, 3) is inside a (1, 1, 5, 5) edges, expect false", false, rect.contains(new Vector(5, 3)));

    }

}
