import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Commands class will read, process, and parse the commands from the input file.
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
	 * Constructor of the Commands file.
	 * 
	 * @param command
	 *            the current line to be parsed from the input file
	 */
	public Commands(String command, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		parseCommands(command, tree, arr);
	}

	/**
	 * This function will decide which command is currently being executed and will
	 * process the command accordingly.
	 * 
	 * @param command
	 *            the current line to be parsed from the input file
	 */
	private void parseCommands(String command, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		if (command.contains("insert")) {
			processInsert(command, tree, arr);
		} else if (command.contains("remove")) {
			processRemove(command, tree, arr);
		} else if (command.contains("regionsearch")) {
			processRegionSearch(command, tree, arr);
		} else if (command.contains("intersections")) {
			processIntersections(command, tree, arr);
		} else if (command.contains("search")) {
			processSearch(command, tree, arr);
		} else if (command.contains("dump")) {
			processDump(command, tree, arr);
		} else {
			// do nothing, process next line
		}
	}

	/**
	 * This function will insert a rectangle of the given name into the specified
	 * coordinates.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processInsert(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
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
			System.out.println("Rectangle accepted: (" + name + ", " + rect.getX() + ", " + rect.getY() + ", "
					+ rect.getWidth() + ", " + rect.getHeight() + ")");
		} else { // reject an invalid Rectangle
			System.out.println("Rectangle rejected: " + "(" + name + ", " + xString + ", " + yString + ", " + wString
					+ ", " + hString + ")");
		}

	}

	/**
	 * This function shall remove a rectangle with a given name or with matching
	 * coordinates. If two or more rectangles have the same name and/or coordinates,
	 * an arbitrary one of those rectangles shall be removed. If no such rectangle
	 * exists of a given name and/or coordinates, the function will report that none
	 * was found.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processRemove(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
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
				System.out.println("Rectangle rejected: " + tok + " " + yString + " " + wString + " " + hString);
			} else { // remove the rectangle if it is in the tree
				tree.remove(result);
				arr.remove(result);
				System.out.println("Rectangle removed: (" + tok + ", " + result.getX() + ", " + result.getY() + ", "
						+ +result.getWidth() + ", " + result.getHeight() + ")");
			}

		} else { // remove by name
			Rectangle result = tree.find(findRect(tok, arr));

			// reject the rectangle if not in the tree
			if (result == null) {
				System.out.println("Rectangle not removed: " + tok);
			} else { // remove the rectangle if it is in the tree
				tree.remove(result);
				arr.remove(result);
				System.out.println("Rectangle removed: (" + tok + ", " + result.getX() + ", " + result.getY() + ", "
						+ +result.getWidth() + ", " + result.getHeight() + ")");
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
	private static Rectangle checkCoordinates(int x, int y, int w, int h, ArrayList<Rectangle> arr) {
		if (arr.size() == 0) {
			return null;
		}
		for (int i = 0; i < arr.size(); i++) {
			if (x == arr.get(i).getX() && y == arr.get(i).getY() && w == arr.get(i).getWidth()
					&& h == arr.get(i).getHeight()) {
				return arr.get(i);
			}
		}
		return null;

	}

	/**
	 * Gives a list of all the rectangles in the database that intersect the
	 * rectangle specified by the parameters. Each rectangle will have its name and
	 * coordinates. If the height or width is smaller than or equal to 0, the search
	 * will be rejected.
	 * 
	 * @param next
	 *            the current line being processed
	 * @return true if valid region and found something, else false
	 */
	private static boolean processRegionSearch(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		Scanner regionScan = new Scanner(next);
		regionScan.next(); // skips over the token that has the word regionsearch

		// get all of the coordinate values in int form
		int x = Integer.parseInt(regionScan.next());
		int y = Integer.parseInt(regionScan.next());
		int w = Integer.parseInt(regionScan.next());
		int h = Integer.parseInt(regionScan.next());

		regionScan.close();

		if (w <= 0 || h <= 0) {
			System.out.println("Rectangle rejected: " + "(" + x + ", " + y + ", " + w + ", " + h + ")");
			return false;
		}

		// treat the region being searched as a "ghost" rectangle.
		Rectangle region = new Rectangle("Region", x, y, w, h);

		System.out.println("Rectangles intersecting region " + "(" + x + ", " + y + ", " + w + ", " + h + "):");

		boolean found = false;
		for (Rectangle rect : arr) {
			if (region.intersect(rect)) {
				if (!found) {
					found = true;
				}
				System.out.println("(" + rect.getName() + ", " + rect.getX() + ", " + rect.getY() + ", "
						+ rect.getWidth() + ", " + rect.getHeight() + ")");
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
	private static void processIntersections(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		System.out.println("Intersection pairs: ");
	}

	/**
	 * If a rectangle of the input name exists, this function shall return
	 * information about said rectangle.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processSearch(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		Scanner searchScan = new Scanner(next);
		searchScan.next(); // skips over the token that has the word search
		String name = searchScan.next();

		searchScan.close();

		Rectangle result = tree.find(findRect(name, arr));

		// prints the results of the search
		if (result == null || result.getName().equals("")) {
			System.out.println("Rectangle not found: " + name);
		} else {
			System.out.println("Rectangle found: (" + name + ", " + result.getX() + ", " + result.getY() + ", "
					+ result.getWidth() + ", " + result.getHeight() + ")");
		}

	}

	/**
	 * Prints out each node of the BST's value and number of pointers it contains
	 * via inorder traversal.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processDump(String next, BST<Rectangle> tree, ArrayList<Rectangle> arr) {
		System.out.println("BST Dump:");
		tree.inorder_iterator();

		if (tree.getRoot() == null) {
			System.out.println("Node has depth 0, Value (" + tree.getRoot() + ")");
		} else {
			for (int i = 0; i < tree.getStack().size(); i++) {
				System.out.println("Node has depth " + tree.getDepth(tree.getStack().elementAt(i).element, tree.getRoot())
						+ ", Value (" + tree.getStack().elementAt(i).element.getName() + ", "
						+ tree.getStack().elementAt(i).element.getX() + ", "
						+ tree.getStack().elementAt(i).element.getY() + ", "
						+ tree.getStack().elementAt(i).element.getWidth() + ", "
						+ tree.getStack().elementAt(i).element.getHeight() + ")");
			}
			System.out.println("BST size is: " + tree.getStack().size());
		}
	}

	/**
	 * Searches for a rectangle object in the rectangleList by name
	 * 
	 * @param name
	 *            the name of the rectangle being searched for
	 * @return the Rectangle object being looked for, or null if it is not found
	 */
	private static Rectangle findRect(String name, ArrayList<Rectangle> arr) {
		if (arr.size() == 0) {
			return null;
		}
		for (int i = 0; i < arr.size(); i++) {
			if (name.compareTo(arr.get(i).getName()) == 0) {
				return arr.get(i);
			}
		}
		return null;
	}
}
