import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author ianimp96
 * @author nickeda
 * @version 09/04/17 (11:30 AM)
 * 
 *          The Rectangle1 program reads and processes commands to handle a
 *          collection of rectangles in a database. The program allows the user
 *          to insert, delete, and perform searches on the database using the
 *          Binary Search Tree data structure.
 *
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
//
// Ian Imperial
// Nick Eda

public class Rectangle1 {

	/**
	 * The main function will process the commands invoked on the program.
	 * 
	 * @param args
	 *            the arguments entered
	 */
	public static void main(String[] args) {
		// invoked as java Rectangle1 {command-file}
		// parse input file
		// perform command
		// create and write to output file
		if (0 < args.length) {
			File input = new File(args[0]);
			File output = new File("Output.txt");

			String currLine;
			try {
				FileReader inputReader = new FileReader(input);
				FileWriter outputFile = new FileWriter(output);

				Scanner scan = new Scanner(inputReader);
				scan.useDelimiter("\n");
				while (scan.hasNext()) {
					processCommands(scan.next());
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("The file was not found.");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error while writing.");
			}
		} else {
			System.out.println("Check arguments");
			System.exit(0);
		}

	}

	/**
	 * This function will decide which command is currently being executed and will
	 * process the command accordingly.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processCommands(String next) {
		if (next.contains("insert")) {
			processInsert(next);
		} else if (next.contains("remove")) {
			processRemove(next);
		} else if (next.contains("regionsearch")) {
			processRegionSearch(next);
		} else if (next.contains("intersections")) {
			processIntersections(next);
		} else if (next.contains("search")) {
			processSearch(next);
		} else if (next.contains("dump")) {
			processDump(next);
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
	private static void processInsert(String next) {
		Scanner insertScan = new Scanner(next);
		while (insertScan.hasNext()) {
			//System.out.println(insertScan.next());
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
	private static void processRemove(String next) {
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
	private static void processRegionSearch(String next) {
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
	private static void processIntersections(String next) {

	}

	/**
	 * If a rectangle of the input name exists, this function shall return
	 * information about said rectangle.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processSearch(String next) {
		Scanner searchScan = new Scanner(next);
		while (searchScan.hasNext()) {

		}

	}

	/**
	 * Prints out each node of the BST's value and number of pointers it contains
	 * via in-order traversal.
	 * 
	 * @param next
	 *            the current line being processed
	 */
	private static void processDump(String next) {

	}

}
