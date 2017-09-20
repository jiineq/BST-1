import java.util.ArrayList;

import student.TestCase;

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/19/17 (3:30 PM)
 * 
 *          The CommandsTest class will test the different methods in the
 *          Commands class.
 *
 */
public class CommandsTest extends TestCase {

    @SuppressWarnings("unused")
    private Commands command;
    private BST<Rectangle> tree;
    private ArrayList<Rectangle> arr;
    private StringBuilder printString;

    /**
     * This method sets up the other test methods
     */
    @Override
    public void setUp() {
        command = new Commands();
        tree = new BST<Rectangle>();
        arr = new ArrayList<Rectangle>();
        printString = new StringBuilder();
    }

    /**
     * Tests that the insert method works for valid cases
     */
    public void testInsertValid() {
        assertTrue(tree.isEmpty());
        assertTrue(arr.isEmpty());
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        assertFalse(tree.isEmpty());
        assertNotNull(tree.find(rect));
        assertFalse(arr.isEmpty());
        assertNotNull(arr.get(0));
    }

    /**
     * Tests that the insert method returns proper values for invalid cases
     */
    public void testInsertInvalid() {
        assertTrue(tree.isEmpty());
        assertTrue(arr.isEmpty());
        command = new Commands("insert rectangle1 50 50 0 0", tree, arr,
                printString);
        assertTrue(tree.isEmpty());
        assertTrue(arr.isEmpty());
    }

    /**
     * Tests that the remove method works for valid cases for coordinates
     */
    public void testValidRemoveCoordinates() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 50, 50, 50, 50);

        // try to remove rectangle that's not in the tree yet
        command = new Commands("remove 0 0 50 50", tree, arr, printString);
        assertTrue(arr.isEmpty());

        // insert rectangles then remove one
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("remove 0 0 50 50", tree, arr, printString);
        assertNull(tree.find(rect));
        assertNotNull(tree.find(rect2));

    }

    /**
     * Tests that the remove method returns proper values for invalid cases for
     * coordinates
     */
    public void testInvalidRemoveCoordinates() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 50, 50, 50, 50);

        // insert rectangles
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);

        // remove invalid coordinates
        command = new Commands("remove -50 0 50 50", tree, arr, printString);
        command = new Commands("remove 0 -50 100 50", tree, arr, printString);
        command = new Commands("remove 0 0 -50 50", tree, arr, printString);
        command = new Commands("remove 0 0 50 -50", tree, arr, printString);
        command = new Commands("remove -50 -50 -50 -50", tree, arr,
                printString);

        assertNotNull(tree.find(rect));
        assertNotNull(tree.find(rect2));
    }

    /**
     * Tests that the remove method works for valid cases for names
     */
    public void testValidRemoveName() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 50, 50, 50, 50);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("remove rectangle1", tree, arr, printString);
        assertNull(tree.find(rect));
        assertNotNull(tree.find(rect2));
    }

    /**
     * Tests that the remove method returns proper values for invalid cases for
     * names
     */
    public void testInvalidRemoveName() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 50, 50, 50, 50);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("remove rectangle3", tree, arr, printString);
        assertNotNull(tree.find(rect));
        assertNotNull(tree.find(rect2));
    }

    /**
     * Tests that the region search method works for valid cases
     */
    public void testValidRegionSearch() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 10, 10, 50, 50);
        Rectangle rect3 = new Rectangle("rectangle3", 0, 0, 50, 5);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("regionsearch 10 10 50 50", tree, arr,
                printString);
        command = new Commands("regionsearch 0 25 25 25", tree, arr,
                printString);
        assertTrue(rect.intersect(rect2));
        assertTrue(rect.intersect(rect3));
        assertFalse(rect2.intersect(rect3));
    }

    /**
     * Tests that the region search method works for invalid cases
     */
    public void testInvalidRegionSearch() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 0, 0, -50, -50);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("regionsearch 0 0 -50 50", tree, arr,
                printString);
        command = new Commands("regionsearch 0 0 50 -50", tree, arr,
                printString);
        assertFalse(rect.intersect(rect2));
    }

    /**
     * Tests that the intersections method works
     */
    public void testIntersections() {
        command = new Commands("intersections", tree, arr, printString);
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        Rectangle rect2 = new Rectangle("rectangle2", 50, 50, 50, 50);
        Rectangle rect3 = new Rectangle("rectangle3", 100, 101, 102, 103);
        Rectangle rect4 = new Rectangle("rectangle4", 2, 2, 500, 500);
        Rectangle rect5 = new Rectangle("rectangle5", 1, 1, 450, 400);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle3 100 101 102 103", tree, arr,
                printString);
        command = new Commands("insert rectangle4 2 2 500 500", tree, arr,
                printString);
        command = new Commands("insert rectangle5 1 1 450 400", tree, arr,
                printString);
        command = new Commands("intersections", tree, arr, printString);
        assertFalse(rect.intersect(rect2));
        assertTrue(rect.intersect(rect5));
        assertTrue(rect.intersect(rect4));
        assertFalse(rect.intersect(rect3));
        assertTrue(rect2.intersect(rect4));
        assertTrue(rect2.intersect(rect5));
        assertTrue(rect3.intersect(rect4));
        assertTrue(rect3.intersect(rect5));
    }

    /**
     * Tests that the search method works for valid cases
     */
    public void testValidSearch() {
        Rectangle rect = new Rectangle("rectangle1", 0, 0, 50, 50);
        command = new Commands("search rectangle1", tree, arr, printString);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("search rectangle1", tree, arr, printString);
        assertNotNull(tree.find(rect));

    }

    /**
     * Tests that the search method works for invalid cases
     */
    public void testInvalidSearch() {
        Rectangle rect = new Rectangle("rectangle3", 50, 50, 100, 100);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("search rectangle3", tree, arr, printString);
        assertNull(tree.find(rect));
    }

    /**
     * Tests that the dump method works
     */
    public void testDump() {
        command = new Commands("dump", tree, arr, printString);
        assertNull(tree.getRoot());
        Rectangle rect = new Rectangle("rectangle1", 50, 50, 100, 100);
        command = new Commands("insert rectangle1 0 0 50 50", tree, arr,
                printString);
        command = new Commands("insert rectangle2 50 50 50 50", tree, arr,
                printString);
        command = new Commands("dump", tree, arr, printString);
        assertEquals(0, tree.getDepth(rect, tree.getRoot()));
        assertEquals(2, tree.getSize());

        // test an invalid command
        command = new Commands("dumb", tree, arr, printString);
    }

}
