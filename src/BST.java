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
public class BST<T extends Comparable<? super T>> {

	/**
	 * Creates a basic node to be used in the Binary Search Tree.
	 * 
	 * @param <T>
	 *            The type of data element contained in the node.
	 */
	private static class BinaryNode<T> {

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
	 * @param t
	 *            the current element being looked at
	 * @return the minimum node in the tree
	 */
	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		}
		return findMin(t.left);
	}

	/**
	 * This function finds the maximum value in the tree.
	 * 
	 * @param t
	 *            the current element being looked at
	 * @return the maximum node in the tree
	 */
	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t == null) {
			return null;
		} else if (t.right == null) {
			return t;
		}
		return findMax(t.right);
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
	 * @param t
	 *            the current node being looked at
	 * @return true if the value is in the tree, false otherwise
	 */
	private boolean contains(T element, BinaryNode<T> t) {
		// sanity check - base case
		if (t == null) {
			return false;
		}
		int compareResult = element.compareTo(t.element);

		if (compareResult < 0) {
			return contains(element, t.left);
		} else if (compareResult > 0) {
			return contains(element, t.right);
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
	 * @param t
	 *            the current node being looked at
	 * @return the root node of the altered tree
	 */
	private BinaryNode<T> insert(T element, BinaryNode<T> t) {
		// if the tree is empty, create a new tree
		if (t == null) {
			return new BinaryNode<T>(element, null, null);
		}

		int compareResult = element.compareTo(t.element);

		// element goes to the left subtree
		if (compareResult < 0) {
			t.left = insert(element, t.left);
		}
		// element goes to the right subtree
		else if (compareResult > 0) {
			t.right = insert(element, t.right);
		} else {
			// duplicate insert
			BinaryNode<T> successor = this.findMin(t.right);
			successor.left = t;
		}
		return t;
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
	 * @param t
	 *            the current node being looked at
	 * @return the root node of the altered tree
	 */
	private BinaryNode<T> remove(T element, BinaryNode<T> t) {
		if (t == null) {
			return t;
		}

		int compareResult = element.compareTo(t.element);

		// search the left subtree
		if (compareResult < 0) {
			t.left = remove(element, t.left);
		}
		// search the right subtree
		else if (compareResult > 0) {
			t.right = remove(element, t.right);
		}
		// found the item to delete,determine 1 or 2 children
		else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else {
			t = (t.left != null) ? t.left : t.right;

		}
		return t;
	}

	/**
	 * A method to search for a given element in the tree
	 * 
	 * @param x
	 *            The value of the element to look for
	 * @return the element searched for
	 */
	public T find(T x) {
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
	 * @param t
	 *            the current node being looked at
	 * @return the element searched for
	 */
	private T find(T x, BinaryNode<T> t) {
		if (t == null) {
			return null;
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			return find(x, t.left);
		} else if (compareResult > 0) {
			return find(x, t.right);
		} else {
			return t.element;
		}
	}

}
