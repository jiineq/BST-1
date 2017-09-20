import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import student.TestCase;

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/19/17 (8:01 PM)
 * 
 *          The RectangleTest class will test the different methods in the
 *          Rectangle class.
 *
 */
public class OutputTest extends TestCase {

    @SuppressWarnings("unused")
    private Output output;

    @Override
    public void setUp() throws Exception {
        output = new Output();
    }

    /**
     * Tests the output method
     * 
     * @throws IOException
     *             throws exception if an error occurs while writing
     */
    public void test() throws IOException {
        File outputFile = new File("OutputFile.txt");
        FileWriter fileWrite = new FileWriter(outputFile);
        output = new Output(fileWrite, "Testing");
        assertNotNull(output);
    }

}
