
/**
 * The Rectangle class will handle the features of the Rectangles that will be
 * added to the BST.
 */

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/10/17 (6:02 PM)
 *
 */
public class Rectangle implements Comparable<Rectangle> {

    private String name;
    private int x;
    private int y;
    private int w; // width
    private int h; // height

    /**
     * Empty constructor for the Rectangle class, sets all values to 0
     */
    public Rectangle() {
        name = "";
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    /**
     * Constructor for a new Rectangle object.
     * 
     * @param name
     *            the name of the rectangle
     * @param x
     *            the x coordinate of the rectangle
     * @param y
     *            the y coordinate of the rectangle
     * @param w
     *            the width of the rectangle
     * @param h
     *            the height of the rectangle
     */
    public Rectangle(String name, int x, int y, int w, int h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Checks to see if the Rectangle is valid by checking if it meets the
     * following conditions:
     * 
     * 1. The height and width are not greater than 0. 2. The rectangle is
     * within the 1024 x 1024 world box
     * 
     * @return true if the Rectangle is valid, false otherwise
     */
    public boolean isValid() {
        return (validName() && validCoordinates() && validRange()
                && validDimensions());
    }

    /**
     * Checks to see if the name is valid. The name is invalid if it begins with
     * a digit.
     * 
     * @return true if the name is Valid, false otherwise
     */
    private boolean validName() {
        if (!name.equals("")) {
            char first = name.charAt(0);
            return !Character.isDigit(first);
        }
        return false;

    }

    /**
     * helper method that checks x and y coordinates
     * 
     * @return true if x and y are both greater than 0
     */
    private boolean validCoordinates() {
        return (x >= 0 && y >= 0);
    }

    /**
     * helper method that checks that the rectangle doesn't leave the bounds
     * 
     * @return true if x + w and y + h are less than bounds
     */
    private boolean validRange() {
        return ((x + w <= 1024) && (y + h <= 1024));
    }

    /**
     * helper method that checks that the rectangle has real dimension
     * 
     * @return true if w and h are both greater than 0
     */
    private boolean validDimensions() {
        return (w > 0 && h > 0);
    }

    /**
     * Getter for the name of a Rectangle object
     * 
     * @return the name of the rectangle
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the x coordinate of the Rectangle object
     * 
     * @return the x coordinate of the rectangle
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y coordinate of the Rectangle object
     * 
     * @return the y coordinate of the rectangle
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for the width of the Rectangle object
     * 
     * @return the width of the rectangle
     */
    public int getWidth() {
        return w;
    }

    /**
     * Getter for the height of the Rectangle object
     * 
     * @return the height of the rectangle
     */
    public int getHeight() {
        return h;
    }

    @Override
    public int compareTo(Rectangle rect2) {
        return (getName().compareTo(rect2.getName()));
    }

    /**
     * Checks to see which rectangles intersect one another.
     * 
     * @param other
     *            the rectangle to check for intersection with
     * @return true if rectangles intersect, false otherwise
     */
    public boolean intersect(Rectangle other) {
        if (other == null) {
            return false;
        }

        double xMax1 = this.getX() + this.getWidth();
        double xMax2 = other.getX() + other.getWidth();
        double yMax1 = this.getY() + this.getHeight();
        double yMax2 = other.getY() + other.getHeight();

        double xDistance = Math.max(0,
                Math.min(xMax2, xMax1) - Math.max(this.getX(), other.getX()));
        double yDistance = Math.max(0,
                Math.min(yMax2, yMax1) - Math.max(this.getY(), other.getY()));

        return (xDistance > 0 && yDistance > 0);

    }

    /**
     * Prints the rectangles name, coordinates, and dimensions
     * 
     * @return A string of format ("name, x, y, w, h")
     */
    @Override
    public String toString() {
        return "(" + this.getName() + ", " + this.getX() + ", " + this.getY()
                + ", " + this.getWidth() + ", " + this.getHeight() + ")";
    }

}
