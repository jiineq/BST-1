import java.util.Iterator;
import java.util.Stack;

/**
 * @author Ian Imperial (ianimp96)
 * @author Nick Eda (nickeda)
 * @version 09/05/17 (11:30 AM)
 * 
 *          The BST class will handle the processes of the Binary Search Tree
 *          data structure to assist in our Rectangle1 program.
 *
 * @param <T>
 *            the data type
 */
public class BST<T extends Comparable<? super T>> implements Iterator<T> {

    /**
     * Creates a basic node to be used in the Binary Search Tree.
     * 
     * @param <T>
     *            The type of data element contained in the node.
     */
    static class BinaryNode<T> {

        /**
         * the data in the node
         */
        T element;
        /**
         * the left child
         */
        BinaryNode<T> left;
        /**
         * the right child
         */
        BinaryNode<T> right;

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
    private Stack<BinaryNode<T>> nodeStack;
    private Stack<BinaryNode<T>> internalStack;
    private int size;

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
     * @param root2
     *            the current element being looked at
     * @return the minimum node in the tree
     */
    private BinaryNode<T> findMin(BinaryNode<T> root2) {
        if (root2 == null) {
            return null;
        }
        else if (root2.left == null) {
            return root2;
        }
        return findMin(root2.left);
    }

    /**
     * This function finds the maximum value in the tree.
     * 
     * @param root2
     *            the current element being looked at
     * @return the maximum node in the tree
     */
    @SuppressWarnings("unused")
    private BinaryNode<T> findMax(BinaryNode<T> root2) {
        if (root2 == null) {
            return null;
        }
        else if (root2.right == null) {
            return root2;
        }
        return findMax(root2.right);
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
     * @param root2
     *            the current node being looked at
     * @return true if the value is in the tree, false otherwise
     */
    private boolean contains(T element, BinaryNode<T> root2) {
        // sanity check - base case
        if (root2 == null) {
            return false;
        }
        int compareResult = element.compareTo(root2.element);

        if (compareResult < 0) {
            return contains(element, root2.left);
        }
        else if (compareResult > 0) {
            return contains(element, root2.right);
        }
        else {
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
     * @param root2
     *            the current node being looked at
     * @return the root node of the altered tree
     */
    private BinaryNode<T> insert(T element, BinaryNode<T> root2) {
        // if the tree is empty, create a new tree
        if (root2 == null) {
            return new BinaryNode<T>(element, null, null);
        }

        int compareResult = element.compareTo(root2.element);

        // element goes to the left subtree
        if (compareResult < 0) {
            root2.left = insert(element, root2.left);
        }
        // element goes to the right subtree
        else if (compareResult > 0) {
            root2.right = insert(element, root2.right);
        }
        else {
            // duplicate insert
            BinaryNode<T> successor = this.findMin(root2.right);
            successor.left = root2;
        }
        size++;
        return root2;
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
     * @param root2
     *            the current node being looked at
     * @return the root node of the altered tree
     */
    private BinaryNode<T> remove(T element, BinaryNode<T> root2) {
        if (root2 == null) {
            return root2;
        }

        int compareResult = element.compareTo(root2.element);

        // search the left subtree
        if (compareResult < 0) {
            root2.left = remove(element, root2.left);
        }
        // search the right subtree
        else if (compareResult > 0) {
            root2.right = remove(element, root2.right);
        }
        // found the item to delete,determine 1 or 2 children
        else if (root2.left != null && root2.right != null) {
            root2.element = findMin(root2.right).element;
            root2.right = remove(root2.element, root2.right);
        }
        else {
            root2 = (root2.left != null) ? root2.left : root2.right;

        }
        size--;
        return root2;
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
     * @param root2
     *            the current node being looked at
     * @return the element searched for
     */
    private T find(T x, BinaryNode<T> root2) {
        if (root2 == null) {
            return null;
        }

        int compareResult = x.compareTo(root2.element);

        if (compareResult < 0) {
            return find(x, root2.left);
        }
        else if (compareResult > 0) {
            return find(x, root2.right);
        }
        else {
            return root2.element;
        }
    }

    /**
     * Function to acquire the depth of a specific element.
     * 
     * @param binaryNode
     *            the element whose depth we are looking for
     * @param root2
     *            the root of the tree
     * @return the depth of the specified element
     */
    public int getDepth(T binaryNode, BinaryNode<T> root2) {
        return getDepthHelper(binaryNode, root2, 0);
    }

    /**
     * Helper function for getDepth
     * 
     * @param binaryNode
     *            the element whose depth we are looking for
     * @param root2
     *            the current node we are visiting
     * @param depth
     *            the current depth of the node we are visiting
     * @return the depth of the element
     */
    private int getDepthHelper(T binaryNode, BinaryNode<T> root2, int depth) {
        if (root2 == null) {
            return 0;
        }
        if (root2.element == binaryNode) {
            return depth;
        }

        int down = getDepthHelper(binaryNode, root2.left, depth + 1);
        if (down != 0) {
            return down;
        }
        else {
            down = getDepthHelper(binaryNode, root2.right, depth + 1);
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
     * Getter for the size of the tree
     * 
     * @return the size of the tree
     */
    public int getSize() {
        return size;
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

    /**
     * hasNext() method for an internal stack
     * 
     * @return true if there is next item in the stack
     */
    public boolean hasNextInner() {
        if (internalStack == null) {
            return false;
        }
        if (internalStack.isEmpty()) {
            return false;
        }
        return (internalStack.peek() != null);
    }

    @Override
    public T next() {
        if (nodeStack.isEmpty()) {
            return null;
        }
        else {
            BinaryNode<T> curr = nodeStack.peek();
            nodeStack.pop();

            if (curr.right != null) {
                goLeftFrom(curr.right);
            }
            return curr.element;
        }
    }

    /**
     * next() method for an internal stack
     * 
     * @return nextInner
     */
    public T nextInner() {
        if (internalStack.isEmpty()) {
            return null;
        }
        else {
            BinaryNode<T> curr = internalStack.peek();
            internalStack.pop();

            if (curr.right != null) {
                innerGoLeftFrom(curr.right);
            }
            return curr.element;
        }
    }

    /**
     * In-order iterator that will create a stack of nodes in the BST.
     */
    public void inorderIterator() {
        if (root != null) {
            // properly positions first node
            nodeStack = new Stack<BinaryNode<T>>();

            goLeftFrom(root);
        }
    }

    /**
     * Helper function to push nodes along the leftmost branch, starting from
     * the root until we reach a node with no left child.
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
     * Helper function to push nodes along the leftmost branch, starting from
     * the root until we reach a node with no left child to an internal stack.
     * 
     * @param t
     *            the current node of the tree to be pushed
     */
    private void innerGoLeftFrom(BinaryNode<T> t) {
        while (t != null) {
            internalStack.push(t);
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

    /**
     * Sets internalStack to be a deep copy of the original stack
     */
    public void setOtherStack() {
        this.internalStack = deepCopy(this.getStack());
    }

    /**
     * Getter for the internal node stack
     * 
     * @return the internal node stack
     */
    public Stack<BinaryNode<T>> getInternalStack() {
        return this.internalStack;
    }

    /**
     * private helper method for setOtherStack
     * 
     * @param x
     *            originalStack
     * @return deep copy of x
     */
    private Stack<BinaryNode<T>> deepCopy(Stack<BinaryNode<T>> x) {
        if (x == null)
            return null;
        // else
        Stack<BinaryNode<T>> copy = new Stack<BinaryNode<T>>();
        for (int i = x.size() - 1; i >= 0; i--) {
            copy.push(x.elementAt(i));
        }
        return copy;
    }

}
