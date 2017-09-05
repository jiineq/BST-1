/**
 * @author Ian
 * @author Nick
 * @version 09/05/17 (11:40 AM)
 *
 *	
 */
public class BST<T extends Comparable<? super T>> {

	// Generic node for the BST
	private static class BinaryNode<T>
	{
		T element; // the data in the node
		BinaryNode<T> left; // the left child
		BinaryNode<T> right; // the right child 
		
		// Constructors
		BinaryNode ( T theElement) {
			this(theElement, null, null);
		}
		
		BinaryNode ( T theElement, BinaryNode<T> lt, BinaryNode <T> rt) {
			this.element = theElement;
			this.left = lt;
			this.right = rt;
		}
	}
	
	private BinaryNode<T> root;
	
	// Constructors
	
	/**
	 * 
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
	 * @return true if root is null, else false
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null) {
			return null;
		}
		else if (t.left == null) {
			return t;
		}
		return findMin(t.left);
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t == null) {
			return null;
		}
		else if (t.right == null) {
			return t;
		}
		return findMax(t.right);
	}
	
	/**
	 * This method finds a node with the given element as its key
	 * @param element the key value we are searching for
	 * @return true if the element is in the BST, else false
	 */
	public boolean contains( T element) {
		return this.contains(element, root);
	}

	private boolean contains(T element, BinaryNode<T> t) {
		// sanity check - base case  
		if (t == null)
		{
			return false;
		}				
		int compareResult = element.compareTo( t.element);
		
		if (compareResult < 0) {
			return contains(element, t.left);
		}
		else if (compareResult > 0) {
			return contains(element, t.right);
		}
		else {
			return true; // match
		}
	}
	
	/**
	 * This method inserts a new object into the tree
	 * @param element the element to insert
	 */
	public void insert( T element) {
		root = this.insert(element, root);
	}
	
	private BinaryNode<T> insert( T element, BinaryNode<T> t) {
		// sanity check
		if (t == null) {
			return new BinaryNode<T>( element, null, null );
		}
		
		int compareResult = element.compareTo( t.element);
		
		if (compareResult < 0) {
			t.left = insert(element, t.left);
		}
		else if (compareResult > 0) {
			t.right = insert(element, t.right);
		}
		else {
			// duplicate insert
			BinaryNode<T> successor = this.findMin(t.right);
			successor.left = t;
		}
		return t;
	}
	
	public void remove(T element) {
		root = this.remove(element, root);
	}
	
	private BinaryNode<T> remove(T element, BinaryNode<T> t) {
		if (t == null) {
			return t;
		}
		int compareResult = element.compareTo(t.element);
		
		if ( compareResult < 0) {
			t.left = remove( element, t.left);
		}
		else if (compareResult > 0) {
			t.right = remove( element, t.right);
		}
		// found the item to delete,determine 1 or 2 children
		else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove (t.element, t.right);
		}
		else {
			t = (t.left != null) ? t.left : t.right;
			
		}
		return t;
	}
	
	
	public T find(T x) {
		return find(x, root);
	}
	
	/**
	 * This method provides client access to data objects within the tree
	 * 
	 * NOTE: this method grants access to the key values, which could ruin
	 * 		 properties of the structure.
	 * @param x 
	 * @param t 
	 * @return
	 */
	private T find(T x, BinaryNode<T> t) {
		if ( t == null) {
			return null;
		}
		
		int compareResult = x.compareTo(t.element);
		
		if (compareResult < 0) {
			return find(x, t.left);
		}
		else if (compareResult > 0) {
			return find(x, t.right);
		}
		else {
			return t.element;
		}
	}
	
	
	
	
}
	
	
	
	

