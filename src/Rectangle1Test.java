import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

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
public class Rectangle1Test extends TestCase {

    private String[] args;
    private ByteArrayOutputStream errContent;

    @Override
    public void setUp() throws Exception {
        new Rectangle1();
        errContent = new ByteArrayOutputStream();
        args = new String[1];
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Test for invalid arguments case
     * 
     * @throws IOException
     *             throws an exception if no arguments
     */
    public void testErrors() throws IOException {
        args = new String[0];
        Rectangle1.main(args);
        assertTrue(errContent.toString().contains("Check arguments"));
    }

    /**
     * Tests the main method
     * 
     * @throws FileNotFoundException
     *             throws an exception if no file exists
     */
    public void testMain() throws FileNotFoundException {
        args[0] = "SyntaxTest1.txt";

        Exception caught = null;
        try {
            Rectangle1.main(args);
        }
        catch (Exception e) {
            caught = e;
        }
        assertNull(caught);

        FileReader output = new FileReader("SyntaxTest1Output.txt");
        Scanner scan = new Scanner(output);

        assertEquals("Rectangle accepted: (rectangle4, 2, 2, 500, 500)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle6, 460, 460, 10, 10)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle1, 0, 0, 50, 50)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle2, 50, 50, 50, 50)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle3, 100, 101, 102, 103)",
                scan.nextLine());
        assertEquals("Rectangle accepted: (rectangle1, 3, 3, 50, 50)",
                scan.nextLine());
        assertEquals("Rectangle rejected: (99, 90, 95, 94)", scan.nextLine());

        assertEquals("BST Dump:", scan.nextLine());
        assertEquals("Node has depth 1, Value (rectangle1, 0, 0, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 3, Value (rectangle1, 3, 3, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 2, Value (rectangle2, 50, 50, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 3, Value (rectangle3, 100, 101, 102, 103)",
                scan.nextLine());
        assertEquals("Node has depth 0, Value (rectangle4, 2, 2, 500, 500)",
                scan.nextLine());
        assertEquals("Node has depth 1, Value (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals("Node has depth 2, Value (rectangle6, 460, 460, 10, 10)",
                scan.nextLine());
        assertEquals("BST size is: 7", scan.nextLine());

        assertEquals("BST Dump:", scan.nextLine());
        assertEquals("Node has depth 1, Value (rectangle1, 0, 0, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 3, Value (rectangle1, 3, 3, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 2, Value (rectangle2, 50, 50, 50, 50)",
                scan.nextLine());
        assertEquals("Node has depth 3, Value (rectangle3, 100, 101, 102, 103)",
                scan.nextLine());
        assertEquals("Node has depth 0, Value (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals("Node has depth 1, Value (rectangle6, 460, 460, 10, 10)",
                scan.nextLine());
        assertEquals("BST size is: 6", scan.nextLine());

        assertEquals("Rectangle found: (rectangle1, 0, 0, 50, 50)",
                scan.nextLine());
        assertEquals("Rectangle found: (rectangle2, 50, 50, 50, 50)",
                scan.nextLine());
        assertEquals("Rectangles intersecting region (10, 10, 50, 50):",
                scan.nextLine());
        assertEquals("(rectangle5, 1, 1, 450, 400)", scan.nextLine());
        assertEquals("(rectangle1, 0, 0, 50, 50)", scan.nextLine());
        assertEquals("(rectangle2, 50, 50, 50, 50)", scan.nextLine());
        assertEquals("(rectangle1, 3, 3, 50, 50)", scan.nextLine());
        assertEquals("Rectangle rejected: (10, 10, 0, 50)", scan.nextLine());
        assertEquals("Rectangle rejected: (10, 10, 50, 0)", scan.nextLine());
        assertEquals("Rectangle rejected: (10, 10, 0, 0)", scan.nextLine());
        assertEquals("Intersection pairs:", scan.nextLine());
        assertEquals(
                "(rectangle1, 0, 0, 50, 50) : (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals("(rectangle1, 0, 0, 50, 50) : (rectangle1, 3, 3, 50, 50)",
                scan.nextLine());
        assertEquals(
                "(rectangle1, 3, 3, 50, 50) : (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals(
                "(rectangle1, 3, 3, 50, 50) : (rectangle2, 50, 50, 50, 50)",
                scan.nextLine());
        assertEquals(
                "(rectangle2, 50, 50, 50, 50) : (rectangle5, 1, 1, 450, 400)",
                scan.nextLine());
        assertEquals("(rectangle3, 100, 101, 102, 103) : "
                + "(rectangle5, 1, 1, 450, 400)", scan.nextLine());

        assertFalse(scan.hasNext());
        scan.close();
    }

    /**
     * Tests the file not found exception
     * 
     * @throws IOException
     *             throws an exception if the file is not found
     */
    public void testFileNotFound() throws IOException {

        args[0] = ("FAKEFILE");
        Rectangle1.main(args);
        assertTrue(errContent.toString().contains("The file was not found."));
    }

}
