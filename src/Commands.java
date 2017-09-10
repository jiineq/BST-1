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
	public Commands(String command, BST<Rectangle> tree) {
		parseCommands(command, tree);
	}

	/**
	 * This function will decide which command is currently being executed and will
	 * process the command accordingly.
	 * 
	 * @param command
	 *            the current line to be parsed from the input file
	 */
	private void parseCommands(String command, BST<Rectangle> tree) {
		if (command.contains("insert")) {
			processInsert(command, tree);
		} else if (command.contains("remove")) {
			processRemove(command, tree);
		} else if (command.contains("regionsearch")) {
			processRegionSearch(command, tree);
		} else if (command.contains("intersections")) {
			processIntersections(command, tree);
		} else if (command.contains("search")) {
			processSearch(command, tree);
		} else if (command.contains("dump")) {
			processDump(command, tree);
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
	private static void processInsert(String next, BST<Rectangle> tree) {
		Scanner insertScan = new Scanner(next);
		while (insertScan.hasNext()) {
//			System.out.println(insertScan.next());
			insertScan.next();
			String name = insertScan.next();
			String xString = insertScan.next();
			int x = Integer.parseInt(xString);
			String yString = insertScan.next();
			int y = Integer.parseInt(yString);
			String wString = insertScan.next();
			int w = Integer.parseInt(wString);
			String hString = insertScan.next();
			int h = Integer.parseInt(hString);

			Rectangle rect = new Rectangle(name, x, y, w, h);
			if (rect.isValid()) {
				tree.insert(rect, tree.getRoot());
			}
			
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
	private static void processRemove(String next, BST<Rectangle> tree) {
		Scanner removeScan = new Scanner(next);
		while (removeScan.hasNext()) {
			// Need to differentiate between name and x y w h
		}

	}

	/**
	 * Gives a list of all the rectangles in the database that intersect the
	 * rectangle specified by the parameters. Each rectangle will have its name and
	 * coordinates. If the height or width is smaller than or equal to 0, the search
	 * will be rejected.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processRegionSearch(String next, BST<Rectangle> tree) {
		Scanner regionScan = new Scanner(next);
		while (regionScan.hasNext()) {

		}
	}

	/**
	 * Gives all pairs of rectangles in the database that intersect
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processIntersections(String next, BST<Rectangle> tree) {

	}

	/**
	 * If a rectangle of the input name exists, this function shall return
	 * information about said rectangle.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processSearch(String next, BST<Rectangle> tree) {
		Scanner searchScan = new Scanner(next);
		while (searchScan.hasNext()) {

		}

	}

	/**
	 * Prints out each node of the BST's value and number of pointers it contains
	 * via inorder traversal.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processDump(String next, BST<Rectangle> tree) {
		tree.printTree();
	}
}
