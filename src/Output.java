import java.io.FileWriter;
import java.io.IOException;

/**
 * Prints the results to an output text file.
 */

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/10/17 (4:08 PM)
 *
 */
public class Output {

	/**
	 * Constructor of the Output class. Takes the information parsed in
	 * Rectangle1 class and writes output to a text file.
	 * 
	 * @param outputFile
	 *            the file to print to
	 * @param str
	 *            contains the data to print
	 */
	public Output(FileWriter outputFile, String str) {

		try {
			outputFile.write(str);
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
