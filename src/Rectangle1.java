
import java.io.*;
import java.util.ArrayList;
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

    public static StringBuilder printString; // the data to print
    private static BST<Rectangle> tree;
    private static ArrayList<Rectangle> rectangleList;
    @SuppressWarnings("unused")
    private static Output outputTextFile;

    /**
     * The main function will process the commands invoked on the program.
     * 
     * @param args
     *            the arguments entered
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // invoked as java Rectangle1 {command-file}
        if (0 < args.length) {
            File input = new File(args[0]);
 //           File output = new File("Output.txt");
             File output = new File(args[0].substring(0, args[0].length()-4) + "Output.txt");
            

            tree = new BST<Rectangle>();
            printString = new StringBuilder();
            rectangleList = new ArrayList<Rectangle>();

            try {
                FileReader inputReader = new FileReader(input);
                FileWriter outputFile = new FileWriter(output);

                Scanner scan = new Scanner(inputReader);
                scan.useDelimiter("\n");

                // process the commands
                while (scan.hasNext()) {
                    @SuppressWarnings("unused")
                    Commands commands = new Commands(scan.next(), tree, 
                            rectangleList);
                }

                // create and write to the output file
                 outputTextFile = new Output(outputFile,
                 printString.toString());

                scan.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                printString.append("The file was not found.");
                System.err.println("The file was not found.");
            }
//             catch (IOException e) {
//             e.printStackTrace();
//             printString.append("Error while writing.");
//             System.err.println("Error while writing.");
//             }
        }
        else {
            System.err.println("Check arguments");
        }

    }

}
