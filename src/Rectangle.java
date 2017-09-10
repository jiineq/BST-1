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
	
	public boolean isValid() {
		return (h > 0 && w > 0 && x > 0 && y > 0 && x < 1024 && y < 1024);
	}
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}

	@Override
	public int compareTo(Rectangle rect2) {
		return (getName().compareTo(rect2.getName()));
	}

	// intersect:
	// return true if the rectangles intersect

}
