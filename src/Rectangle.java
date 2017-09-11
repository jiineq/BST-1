
/**
 * The Rectangle class will handle the features of the Rectangles that will be added 
 * to the BST. 
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
	 * Checks to see if the Rectangle is valid by checking if it meets the following
	 * conditions:
	 * 
	 * 1. The height and width are not greater than 0. 2. The rectangle is within
	 * the 1024 x 1024 world box
	 * 
	 * @return true if the Rectangle is valid, false otherwise
	 */
	public boolean isValid() {
		return (h > 0 && w > 0 && x >= 0 && y >= 0 && x <= 1024 && y <= 1024);
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
	 * @return
	 */
	public boolean intersect(Rectangle other) {
		if (other == null)
			return false;

		double x_Max1 = this.getX() + this.getWidth();
		double x_Max2 = other.getX() + other.getWidth();
		double y_Max1 = this.getY() + this.getHeight();
		double y_Max2 = other.getX() + other.getHeight();

		double x_Distance = Math.max(0, Math.min(x_Max2, x_Max1) - Math.max(this.getX(), other.getX()));
		double y_Distance = Math.max(0, Math.min(y_Max2, y_Max1) - Math.max(this.getY(), other.getY()));

		return (x_Distance > 0 && y_Distance > 0);

	}
	// return true if the rectangles intersect

}
