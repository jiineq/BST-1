import student.TestCase;

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/19/17 (5:32 PM)
 * 
 *          The RectangleTest class will test the different methods in the
 *          Rectangle class.
 *
 */
public class RectangleTest extends TestCase {

    private Rectangle rect;

    /**
     * Sets up the test methods
     */
    @Override
    public void setUp() {
        rect = new Rectangle("rectangle1", 0, 0, 5, 5);

    }

    /**
     * tests that the following methods word as intended: Rectangle(),
     * getName(), getX(), getY(), getW(), getH()
     */
    public void testDefaultConstructor() {
        rect = new Rectangle();
        assertEquals("", rect.getName());
        assertEquals(0, rect.getX());
        assertEquals(0, rect.getY());
        assertEquals(0, rect.getWidth());
        assertEquals(0, rect.getHeight());
    }

    /**
     * tests that the following methods word as intended: Rectangle(),
     * getName(), getX(), getY(), getW(), getH()
     */
    public void testConstructor() {
        assertEquals("rectangle1", rect.getName());
        assertEquals(0, rect.getX());
        assertEquals(0, rect.getY());
        assertEquals(5, rect.getWidth());
        assertEquals(5, rect.getHeight());
    }

    /**
     * tests that toSring() works as intended
     */
    public void testToString() {
        assertEquals("(rectangle1, 0, 0, 5, 5)", rect.toString());
    }

    /**
     * tests that compareTo() works as intended
     */
    public void testCompareTo() {
        Rectangle rectOther = new Rectangle("rectangle2", 1, 2, 3, 4);
        Rectangle rectSame = new Rectangle("rectangle1", 0, 0, 5, 5);
        assertTrue(rect.compareTo(rectOther) < 0);
        assertTrue(rectOther.compareTo(rect) > 0);
        assertEquals(0, rect.compareTo(rectSame));
    }

    /**
     * tests that intersect() works as intended
     */
    public void testIntersectNull() {
        assertFalse(rect.intersect(null));
    }

    /**
     * tests that intersect() works as intended
     */
    public void testIntersectValid() {
        rect = new Rectangle("", 0, 0, 50, 50);
        Rectangle rectValid = new Rectangle("rect", 0, 25, 25, 25);
        assertTrue(rect.intersect(rectValid));
    }

    /**
     * tests that intersect() works as intended
     */
    public void testIntersectionInvalid() {
        // borders on x plane
        Rectangle rectBorderX = new Rectangle("rect", 15, 0, 10, 10);
        assertFalse(rect.intersect(rectBorderX));

        // borders on y plane
        Rectangle rectBorderY = new Rectangle("rect", 0, 15, 10, 10);
        assertFalse(rect.intersect(rectBorderY));

        Rectangle center = new Rectangle("", 25, 25, 5, 5);
        Rectangle left = new Rectangle("", 0, 25, 5, 5);
        Rectangle right = new Rectangle("", 35, 25, 5, 5);
        Rectangle above = new Rectangle("", 25, 0, 1, 1);
        Rectangle below = new Rectangle("", 25, 40, 5, 5);

        assertFalse(center.intersect(left));
        assertFalse(center.intersect(right));
        assertFalse(center.intersect(above));
        assertFalse(center.intersect(below));
    }

    /**
     * tests that isValid() works as intended
     */
    public void testIsValidTrue() {
        // all conditions are met
        assertTrue(rect.isValid());
    }

    /**
     * tests that isValid() works as intended
     */
    public void testIsValidFalse() {
        // x is < 0
        Rectangle rect1 = new Rectangle("", -1, 0, 5, 5);
        Rectangle rect2 = new Rectangle("", 0, -1, 5, 5);
        Rectangle rect3 = new Rectangle("", 0, 0, 0, 5);
        Rectangle rect4 = new Rectangle("", 0, 0, 5, 0);
        Rectangle rect5 = new Rectangle("", 1000, 0, 25, 5);
        Rectangle rect6 = new Rectangle("", 0, 1000, 5, 25);

        assertFalse(rect1.isValid());
        // y is < 0
        assertFalse(rect2.isValid());
        // w is <= 0
        assertFalse(rect3.isValid());
        // h is <= 0
        assertFalse(rect4.isValid());
        // x + w > 1024
        assertFalse(rect5.isValid());
        // y + h > 1024
        assertFalse(rect6.isValid());
    }

}
