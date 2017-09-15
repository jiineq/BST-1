import java.util.Iterator;
import java.util.Stack;

/**
 * @author ianimp96
 * @author nickeda
 * @version 09/05/17 (11:30 AM)
 * 
 *          The BST class will handle the processes of the Binary Search Tree
 *          data structure to assist in our Rectangle1 program.
 * 
 *
 */
public class BST<T extends Comparable<? super T>> implements Iterator<T> {

	/**
	 * Creates a basic node to be used in the Binary Search Tree.
	 * 
	 * @param <T>
	 *            The type of data element contained in the node.
	 */
	static class BinaryNode<T> {

		T element; // the data in the node
		BinaryNode<T> left; // the left child
		BinaryNode<T> right; // the right child

		// Constructors
		/**
		 * Creates a node with no children.
		 * 
		 * @param elem
		 *            the element to store in this node.
		 */
		@SuppressWarnings("unused")
		BinaryNode(T elem) {
			this(elem, null, null);
		}

		/**
		 * Creates a node with children.
		 * 
		 * @param elem
		 *            the element to store in the node
		 * @param lt
		 *            the node of the left child
		 * @param rt
		 *            the node of the right child
		 */
		BinaryNode(T elem, BinaryNode<T> lt, BinaryNode<T> rt) {
			this.element = elem;
			this.left = lt;
			this.right = rt;
		}
	}

	private BinaryNode<T> root;
	private Stack<BinaryNode<T>> nodeStack;

	/**
	 * Constructs an empty tree.
	 */
	public BST() {
		root = null;
	}

	/**
	 * This method sets the root node of the tree to null.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * This method determines if the root is set to null.
	 * 
	 * @return true if root is null, else false
	 */
	public boolean isEmpty() {
		return (root == null);
	}

	/**
	 * This function finds the minimum value in the tree.
	 * 
	 * @param root
	 *            the current element being looked at
	 * @return the minimum node in the tree
	 */
	private BinaryNode<T> findMin(BinaryNode<T> root) {
		if (root == null) {
			return null;
		} else if (root.left == null) {
			return root;
		}
		return findMin(root.left);
	}

	/**
	 * This function finds the maximum value in the tree.
	 * 
	 * @param root
	 *            the current element being looked at
	 * @return the maximum node in the tree
	 */
	@SuppressWarnings("unused")
	private BinaryNode<T> findMax(BinaryNode<T> root) {
		if (root == null) {
			return null;
		} else if (root.right == null) {
			return root;
		}
		return findMax(root.right);
	}

	/**
	 * This method finds a node with the given element as its key
	 * 
	 * @param element
	 *            the key value we are searching for
	 * @return true if the element is in the BST, else false
	 */
	public boolean contains(T element) {
		return this.contains(element, root);
	}

	/**
	 * Helper function for the contains method.
	 * 
	 * @param element
	 *            the element we are searching for
	 * @param root
	 *            the current node being looked at
	 * @return true if the value is in the tree, false otherwise
	 */
	private boolean contains(T element, BinaryNode<T> root) {
		// sanity check - base case
		if (root == null) {
			return false;
		}
		int compareResult = element.compareTo(root.element);

		if (compareResult < 0) {
			return contains(element, root.left);
		} else if (compareResult > 0) {
			return contains(element, root.right);
		} else {
			return true; // match
		}
	}

	/**
	 * This method inserts a new object into the tree
	 * 
	 * @param element
	 *            the element to insert
	 */
	public void insert(T element) {
		root = this.insert(element, root);
	}

	/**
	 * Helper function for the insert method.
	 * 
	 * @param element
	 *            the element to insert
	 * @param root
	 *            the current node being looked at
	 * @return the root node of the altered tree
	 */
	private BinaryNode<T> insert(T element, BinaryNode<T> root) {
		// if the tree is empty, create a new tree
		if (root == null) {
			return new BinaryNode<T>(element, null, null);
		}

		int compareResult = element.compareTo(root.element);

		// element goes to the left subtree
		if (compareResult < 0) {
			root.left = insert(element, root.left);
		}
		// element goes to the right subtree
		else if (compareResult > 0) {
			root.right = insert(element, root.right);
		} else {
			// duplicate insert
			BinaryNode<T> successor = this.findMin(root.right);
			successor.left = root;
		}
		return root;
	}

	/**
	 * This method removes an element from the tree
	 * 
	 * @param element
	 *            the element to remove
	 */
	public void remove(T element) {
		root = this.remove(element, root);
	}

	/**
	 * Helper function to the remove method
	 * 
	 * @param element
	 *            the element to be removed
	 * @param root
	 *            the current node being looked at
	 * @return the root node of the altered tree
	 */
	private BinaryNode<T> remove(T element, BinaryNode<T> root) {
		if (root == null) {
			return root;
		}

		int compareResult = element.compareTo(root.element);

		// search the left subtree
		if (compareResult < 0) {
			root.left = remove(element, root.left);
		}
		// search the right subtree
		else if (compareResult > 0) {
			root.right = remove(element, root.right);
		}
		// found the item to delete,determine 1 or 2 children
		else if (root.left != null && root.right != null) {
			root.element = findMin(root.right).element;
			root.right = remove(root.element, root.right);
		} else {
			root = (root.left != null) ? root.left : root.right;

		}
		return root;
	}

	/**
	 * A method to search for a given element in the tree
	 * 
	 * @param x
	 *            The value of the element to look for
	 * @return the element searched for
	 */
	public T find(T x) {
		if (x == null) {
			return null;
		}
		return find(x, root);
	}

	/**
	 * This method provides client access to data objects within the tree
	 * 
	 * NOTE: this method grants access to the key values, which could ruin
	 * properties of the structure.
	 * 
	 * @param x
	 *            the element to search for
	 * @param root
	 *            the current node being looked at
	 * @return the element searched for
	 */
	private T find(T x, BinaryNode<T> root) {
		if (root == null) {
			return null;
		}

		int compareResult = x.compareTo(root.element);

		if (compareResult < 0) {
			return find(x, root.left);
		} else if (compareResult > 0) {
			return find(x, root.right);
		} else {
			return root.element;
		}
	}

	/**
	 * Function to acquire the depth of a specific element.
	 * 
	 * @param binaryNode
	 *            the element whose depth we are looking for
	 * @param root
	 *            the root of the tree
	 * @return the depth of the specified element
	 */
	public int getDepth(T binaryNode, BinaryNode<T> root) {
		return getDepthHelper(binaryNode, root, 0);
	}

	/**
	 * Helper function for getDepth
	 * 
	 * @param binaryNode
	 *            the element whose depth we are looking for
	 * @param root
	 *            the current node we are visiting
	 * @param depth
	 *            the current depth of the node we are visiting
	 * @return the depth of the element
	 */
	private int getDepthHelper(T binaryNode, BinaryNode<T> root, int depth) {
		if (root == null) {
			return 0;
		}
		if (root.element == binaryNode) {
			return depth;
		}

		int down = getDepthHelper(binaryNode, root.left, depth + 1);
		if (down != 0) {
			return down;
		} else {
			down = getDepthHelper(binaryNode, root.right, depth + 1);
			return down;
		}
	}

	/**
	 * Getter for the root
	 * 
	 * @return the root of the tree
	 */
	public BinaryNode<T> getRoot() {
		return root;
	}

	/**
	 * Find method that takes and returns a String object
	 * 
	 * @param next
	 *            the String name of the element to search for
	 * @param root
	 *            the current node being looked at
	 * @return the String of the element being search for
	 */
	public String find(String name, BinaryNode<T> root) {
		if (root == null) {
			return "";
		}

		int compareResult = name.compareTo(root.element.toString());

		if (compareResult < 0) {
			return find(name, root.left);
		} else if (compareResult > 0) {
			return find(name, root.right);
		} else {
			return root.element.toString();
		}

	}

	@Override
	public boolean hasNext() {
		if (nodeStack == null) {
			return false;
		}
		if (nodeStack.isEmpty()) {
			return false;
		}
		return (nodeStack.peek() != null);
	}

	@Override
	public T next() {
		if (nodeStack.isEmpty()) {
			return null;
		} else {
			BinaryNode<T> curr = nodeStack.pop();
			return curr.element;
		}

	}

	/**
	 * In-order iterator that will create a stack of nodes in the BST.
	 */
	public void inorder_iterator() {
		if (root != null) {
			// properly positions first node
			nodeStack = new Stack<BinaryNode<T>>();
			goLeftFrom(root);

			BinaryNode<T> curr = nodeStack.peek();

			if (curr.right != null) {
				goLeftFrom(curr.right);
				nodeStack.pop();
				goRightFrom(curr.right);
			}
		}
	}

	/**
	 * Helper function to push nodes along the right branch, starting from the root
	 * until we reach a node with no left child.
	 * 
	 * @param t
	 *            the current node of the tree to be pushed
	 */
	private void goRightFrom(BinaryNode<T> t) {
		while (t != null) {
			nodeStack.push(t);
			t = t.right;
		}

	}

	/**
	 * Helper function to push nodes along the leftmost branch, starting from the
	 * root until we reach a node with no left child.
	 * 
	 * @param t
	 *            the current node of the tree to be pushed
	 */
	private void goLeftFrom(BinaryNode<T> t) {
		while (t != null) {
			nodeStack.push(t);
			t = t.left;
		}
	}

	/**
	 * Getter for the node stack
	 * 
	 * @return the node stack
	 */
	public Stack<BinaryNode<T>> getStack() {
		return nodeStack;
	}

}
