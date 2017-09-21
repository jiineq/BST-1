import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Commands class will read, process, and parse the commands from the input
 * file.
 */

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/10/17 (3:36 PM)
 *
 */
public class Commands extends Rectangle1 {

    /**
     * Empty constructor
     */
    public Commands() {
        // intentionally blank
    }

    /**
     * Constructor of the Commands file
     * 
     * @param command
     *            the command to be processed
     * @param tree
     *            the BST holding the data
     * @param arr
     *            the arrayList of Rectangle objects
     * @param printString
     *            the printString that will be used to print
     */
    public Commands(String command, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        parseCommands(command, tree, arr, printString);
    }

    /**
     * This function will decide which command is currently being executed and
     * will process the command accordingly.
     * 
     * @param command
     *            the current line to be parsed from the input file
     */
    private void parseCommands(String command, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {

        Scanner commandLine = new Scanner(command);
        String commandOrder = "";
        if (commandLine.hasNext()) {
            commandOrder = commandLine.next();
            commandLine.close();
        }

        if (commandOrder.equals("insert")) {
            processInsert(command, tree, arr, printString);
        }
        else if (commandOrder.equals("remove")) {
            processRemove(command, tree, arr, printString);
        }
        else if (commandOrder.equals("regionsearch")) {
            processRegionSearch(command, tree, arr, printString);
        }
        else if (commandOrder.equals("intersections")) {
            processIntersections(command, tree, arr, printString);
        }
        else if (commandOrder.equals("search")) {
            processSearch(command, tree, arr, printString);
        }
        else if (commandOrder.equals("dump")) {
            processDump(command, tree, arr, printString);
        }
    }

    /**
     * This function will insert a rectangle of the given name into the
     * specified coordinates.
     * 
     * @param next
     *            the current line being processed
     */
    private static void processInsert(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        Scanner insertScan = new Scanner(next);
        insertScan.next(); // skips the token that has the word insert
        String name = insertScan.next();

        // get all of the coordinate values in int form
        String xString = insertScan.next();
        int x = Integer.parseInt(xString);
        String yString = insertScan.next();
        int y = Integer.parseInt(yString);
        String wString = insertScan.next();
        int w = Integer.parseInt(wString);
        String hString = insertScan.next();
        int h = Integer.parseInt(hString);

        insertScan.close();

        // create a new rectangle and add it to the tree and ArrayList
        Rectangle rect = new Rectangle(name, x, y, w, h);
        if (rect.isValid()) {
            tree.insert(rect);
            arr.add(rect);
            printString.append("Rectangle accepted: " + rect.toString() + "\n");
            System.out.println("Rectangle accepted: " + rect.toString());

        }
        else { // reject an invalid Rectangle
            printString.append("Rectangle rejected " + rect.toString() + "\n");
            System.out.println("Rectangle rejected " + rect.toString());
        }

    }

    /**
     * This function shall remove a rectangle with a given name or with matching
     * coordinates. If two or more rectangles have the same name and/or
     * coordinates, an arbitrary one of those rectangles shall be removed. If no
     * such rectangle exists of a given name and/or coordinates, the function
     * will report that none was found.
     * 
     * @param next
     *            the current line being processed
     */
    private static void processRemove(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        Scanner removeScan = new Scanner(next);
        removeScan.next(); // skips the token that has the word remove
        String tok = removeScan.next();

        // if the scanner hasNext() then remove by coordinates
        if (removeScan.hasNext()) {

            // get all of the coordinate values in int form
            int x = Integer.parseInt(tok);
            String yString = removeScan.next();
            int y = Integer.parseInt(yString);
            String wString = removeScan.next();
            int w = Integer.parseInt(wString);
            String hString = removeScan.next();
            int h = Integer.parseInt(hString);

            removeScan.close();

            // see if the coordinate pairs are in the tree
            Rectangle result = checkCoordinates(x, y, w, h, arr);

            // reject the rectangle if not in the tree
            if (result == null) {
                printString.append("Rectangle rejected (" + tok + ", " + yString
                        + ", " + wString + ", " + hString + ")\n");
                System.out.println("Rectangle rejected (" + tok + ", " + yString
                        + ", " + wString + ", " + hString + ")");
            }
            else { // remove the rectangle if it is in the tree
                tree.remove(result);
                arr.remove(result);
            }
        }
        else { // remove by name
            Rectangle result = null;
            ArrayList<Rectangle> matches = findRect(tok, arr);
            if (!matches.isEmpty()) {
                result = matches.get(0);
            }

            // reject the rectangle if not in the tree
            if (result == null) {
                printString.append("Rectangle rejected " + tok + "\n");
                System.out.println("Rectangle rejected " + tok);
            }
            else { // remove the rectangle if it is in the tree
                tree.remove(result);
                arr.remove(result);
            }

        }

    }

    /**
     * Helper function that checks the coordinates against each rectangle in the
     * ArrayList to see if it's in the tree.
     * 
     * @param x
     *            the x coordinate from the input file
     * @param y
     *            the y coordinate from the input file
     * @param w
     *            the width from the input file
     * @param h
     *            the height from the input file
     * @param arr
     *            the ArrayList of Rectangles
     * @return the Rectangle, if it is in the list, null otherwise
     */
    private static Rectangle checkCoordinates(int x, int y, int w, int h,
            ArrayList<Rectangle> arr) {
        if (arr.size() == 0) {
            return null;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (x == arr.get(i).getX() && y == arr.get(i).getY()
                    && w == arr.get(i).getWidth()
                    && h == arr.get(i).getHeight()) {
                return arr.get(i);
            }
        }
        return null;

    }

    /**
     * Gives a list of all the rectangles in the database that intersect the
     * rectangle specified by the parameters. Each rectangle will have its name
     * and coordinates. If the height or width is smaller than or equal to 0,
     * the search will be rejected.
     * 
     * @param next
     *            the current line being processed
     * @return true if valid region and found something, else false
     */
    private static boolean processRegionSearch(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        Scanner regionScan = new Scanner(next);
        regionScan.next(); // skips over the token that has the word
                           // regionsearch

        // get all of the coordinate values in int form
        int x = Integer.parseInt(regionScan.next());
        int y = Integer.parseInt(regionScan.next());
        int w = Integer.parseInt(regionScan.next());
        int h = Integer.parseInt(regionScan.next());

        regionScan.close();

        if (w <= 0 || h <= 0) {
            printString.append("Rectangle rejected " + "(" + x + ", " + y + ", "
                    + w + ", " + h + ")\n");
            System.out.println("Rectangle rejected " + "(" + x + ", " + y + ", "
                    + w + ", " + h + ")");
            return false;
        }

        // treat the region being searched as a "ghost" rectangle.
        Rectangle region = new Rectangle("Region", x, y, w, h);

        printString.append("Rectangles intersecting region " + "(" + x + ", "
                + y + ", " + w + ", " + h + "):\n");
        System.out.println("Rectangles intersecting region " + "(" + x + ", "
                + y + ", " + w + ", " + h + "):");

        boolean found = false;
        for (Rectangle rect : arr) {
            if (region.intersect(rect)) {
                if (!found) {
                    found = true;
                }
                printString.append(rect.toString() + "\n");
                System.out.println(rect.toString());
            }
        }
        return found;
    }

    /**
     * Gives all pairs of rectangles in the database that intersect
     * 
     * @param next
     *            the current line being processed
     */
    private static void processIntersections(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        printString.append("Intersections pairs:\n");
        System.out.println("Intersections pairs:");
        tree.inorderIterator();
        while (tree.hasNext()) {
            Rectangle rect = tree.next();
            tree.setOtherStack();
            while (tree.hasNextInner()) {
                Rectangle rectOuter = tree.nextInner();
                if (rect.intersect(rectOuter)) {
                    printString.append(rect.toString() + " : "
                            + rectOuter.toString() + "\n");
                    System.out.println(
                            rect.toString() + " : " + rectOuter.toString());
                }
            }
        }
    }

    /**
     * If a rectangle of the input name exists, this function shall return
     * information about said rectangle.
     * 
     * @param next
     *            the current line being processed
     */
    private static void processSearch(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        Scanner searchScan = new Scanner(next);
        searchScan.next(); // skips over the token that has the word search
        String name = searchScan.next();

        searchScan.close();

        ArrayList<Rectangle> matches = findRect(name, arr);

        if (matches.isEmpty()) {
            printString.append("Rectangle not found: " + name + "\n");
            System.out.println("Rectangle not found: " + name);
        }
        while (!matches.isEmpty()) {
            Rectangle result = matches.get(0);
            matches.remove(0);
            // prints the results of the search
            printString.append("Rectangle found: " + result.toString() + "\n");
            System.out.println("Rectangle found: " + result.toString());

        }

    }

    /**
     * Prints out each node of the BST's value and number of pointers it
     * contains via inorder traversal.
     * 
     * @param next
     *            the current line being processed
     */
    private static void processDump(String next, BST<Rectangle> tree,
            ArrayList<Rectangle> arr, StringBuilder printString) {
        printString.append("BST dump:\n");
        System.out.println("BST dump:");
        tree.inorderIterator();

        if (tree.getRoot() == null) {
            printString.append(
                    "Node has depth 0, Value (" + tree.getRoot() + ")\n");
            System.out.println(
                    "Node has depth 0, Value (" + tree.getRoot() + ")");
        }
        else {
            while (tree.hasNext()) {
                Rectangle rect = tree.getStack().peek().element;
                Rectangle rect2 = tree.next();
                printString.append(
                        "Node has depth " + tree.getDepth(rect, tree.getRoot())
                                + ", Value " + rect2 + "\n");
                System.out.println(
                        "Node has depth " + tree.getDepth(rect, tree.getRoot())
                                + ", Value " + rect2);
            }
        }
        printString.append("BST size is: " + tree.getSize() + "\n");
        System.out.println("BST size is: " + tree.getSize());
    }

    /**
     * Searches for a rectangle object in the rectangleList by name
     * 
     * @param name
     *            the name of the rectangle being searched for
     * @return the Rectangle object being looked for, or null if it is not found
     */
    private static ArrayList<Rectangle> findRect(String name,
            ArrayList<Rectangle> arr) {
        ArrayList<Rectangle> names = new ArrayList<Rectangle>();
        if (!arr.isEmpty()) {
            for (int i = 0; i < arr.size(); i++) {
                if (name.compareTo(arr.get(i).getName()) == 0) {
                    names.add(arr.get(i));
                }
            }
        }
        return names;
    }
}