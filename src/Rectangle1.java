import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author ianimp96
 * @author nickeda
 * @version 09/05/17 (11:30 AM)
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
	
	private static StringBuilder printString; // the data to print
	private static BST<Rectangle> tree; 
	
	/**
	 * The main function will process the commands invoked on the program.
	 * 
	 * @param args
	 *            the arguments entered
	 */
	public static void main(String[] args) {
		// invoked as java Rectangle1 {command-file}
		if (0 < args.length) {
			File input = new File(args[0]);
			File output = new File("Output.txt");
			
			tree = new BST<Rectangle>();
			printString = new StringBuilder();
			String currLine;
			
			try {
				FileReader inputReader = new FileReader(input);
				FileWriter outputFile = new FileWriter(output);

				Scanner scan = new Scanner(inputReader);
				scan.useDelimiter("\n");
				
				// process the commands
				while (scan.hasNext()) {
					Commands commands = new Commands(scan.next(), tree);
				}
				
				// create and write to the output file
				Output outputTextFile = new Output(outputFile, printString.toString());

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

}
