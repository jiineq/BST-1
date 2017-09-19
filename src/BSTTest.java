
import student.TestCase;

/**
 * @author ianimp96
 * @author nickeda
 * @version 9/10/17 (7:05 PM)
 * 
 *          The BSTTest class will test the different methods in the BST class.
 *
 */
public class BSTTest extends TestCase {

    private BST<String> emptyBST;
    private BST<String> oneNode;

    /**
     * This method sets up the other test methods
     */
    @Override
    public void setUp() {
        emptyBST = new BST<String>();
        oneNode = new BST<String>();
        oneNode.insert("root");
    }

    /**
     * tests that the constructor works as intended
     */
    public void testConstructor() {
        assertNull(emptyBST.getRoot());
    }

    /**
     * tests that makeEmpty() and isEmpty() work as intended
     */
    public void testEmpties() {
        assertFalse(oneNode.isEmpty());
        oneNode.makeEmpty();
        assertTrue(oneNode.isEmpty());
    }

    /**
     * tests that insert() and contains()
     */
    public void testInsert() {
        assertTrue(emptyBST.isEmpty());
        assertFalse(emptyBST.contains("beyonce"));
        emptyBST.insert("beyonce");
        emptyBST.insert("cardi");
        assertFalse(emptyBST.isEmpty());
        assertFalse(emptyBST.contains("CArdi"));
        assertTrue(emptyBST.contains("beyonce"));
        
    }
    
    /**
     * tests that insert() works with duplicates
     */
    public void testInsertDuplicate() {
        assertTrue(emptyBST.isEmpty());
        assertFalse(emptyBST.contains("beyonce"));
        emptyBST.insert("beyonce");
        assertTrue(emptyBST.contains("beyonce"));
        emptyBST.insert("beyonce"); //duplicate 1
        emptyBST.insert("vance");
        emptyBST.insert("zhu");
        emptyBST.insert("xxx");
        emptyBST.insert("vance"); // duplicate 2
        assertEquals(6, emptyBST.getSize());
        assertTrue(emptyBST.contains("vance"));
        emptyBST.remove("vance");
        assertTrue(emptyBST.contains("vance"));
       
    }

    /**
     * tests that remove() works as intended
     */
    public void testRemoveEmpty() {
        // test on empty BST
        emptyBST.remove("root");
        assertNull(emptyBST.getRoot());
        
        assertTrue(oneNode.contains("root"));
        assertFalse(oneNode.isEmpty());
    }
    
    public void testRemoveRoot() {
        //populate BST
        oneNode.insert("root"); // dupe
        oneNode.insert("beyonce");
        oneNode.insert("cardi");
        oneNode.insert("adelle");
        oneNode.insert("dvsn");
        assertEquals(6, oneNode.getSize());
        
        //remove current root
        oneNode.remove("root");
        assertTrue(oneNode.contains("root"));
        assertEquals("root", oneNode.getRoot().element);
        assertFalse(oneNode.isEmpty());
        assertEquals(5, oneNode.getSize());
    }
        
    public void testRemoveTwoChildren() {
        //populate BST
        oneNode.insert("root"); // dupe
        oneNode.insert("beyonce");
        oneNode.insert("cardi");
        oneNode.insert("adelle");
        oneNode.insert("dvsn");
        assertEquals(6, oneNode.getSize());
        
        //remove item with two children
        oneNode.remove("beyonce");
        assertFalse(oneNode.contains("beyonce"));
        assertEquals("root", oneNode.getRoot().element);
        assertFalse(oneNode.isEmpty());
        assertEquals(5, oneNode.getSize());        
    }
    
    /**
     * tests that remove() works as intended
     */
    public void testRemoveRightChild() {
        //populate BST
        oneNode.insert("beyonce");
        oneNode.insert("cardi");
        oneNode.insert("dvsn");
        assertEquals(4, oneNode.getSize());
        
        //remove item with one child
        assertEquals("beyonce", oneNode.getRoot().left.element);
        assertNull(oneNode.getRoot().left.left);
        assertEquals("cardi", oneNode.getRoot().left.right.element);
        assertNull(oneNode.getRoot().left.right.left);
        assertEquals("dvsn", oneNode.getRoot().left.right.right.element);
        oneNode.remove("cardi");
        assertFalse(oneNode.contains("cardi"));
        assertEquals("root", oneNode.getRoot().element);
        assertFalse(oneNode.isEmpty());
        assertEquals(3, oneNode.getSize());        
    }
    
    /**
     * tests that remove() works as intended
     */
    public void testRemoveLeftChild() {
        //populate BST
        oneNode.insert("beyonce");
        oneNode.insert("dvsn");
        oneNode.insert("cardi");
        assertEquals(4, oneNode.getSize());
        
        //remove item with one child
        assertEquals("beyonce", oneNode.getRoot().left.element);
        assertNull(oneNode.getRoot().left.left);
        assertEquals("dvsn", oneNode.getRoot().left.right.element);
        assertNull(oneNode.getRoot().left.right.right);
        assertEquals("cardi", oneNode.getRoot().left.right.left.element);
        oneNode.remove("dvsn");
        assertFalse(oneNode.contains("dvsn"));
        assertEquals("root", oneNode.getRoot().element);
        assertFalse(oneNode.isEmpty());
        assertEquals(3, oneNode.getSize());        
    }

    /**
     * tests that find() works as intended
     */
    public void testFindEmpty() {
        assertNull(emptyBST.find("nothing"));
        assertNull(emptyBST.find(null));
    }
    
    /**
     * tests that find() works as intended
     */
    public void testFindNonEmpty() {
        assertEquals("root", oneNode.find("root"));
        oneNode.insert("beyonce");
        oneNode.insert("nicki");
        oneNode.insert("sza");
        assertEquals("beyonce", oneNode.find("beyonce"));
        assertEquals("nicki", oneNode.find("nicki"));
        assertEquals("sza", oneNode.find("sza"));
    }

    /**
     * test that getDepth() and getRoot() works as intended
     */
    public void testDepth() {
        oneNode.insert("beyonce");
        oneNode.insert("adele");
        oneNode.insert("sza");
        assertEquals(0, emptyBST.getDepth("root", emptyBST.getRoot()));
        assertEquals(0, oneNode.getDepth("root", oneNode.getRoot()));
        assertEquals(2, oneNode.getDepth("adele", oneNode.getRoot()));
        assertEquals(1, oneNode.getDepth("beyonce", oneNode.getRoot()));
        assertEquals(1, oneNode.getDepth("sza", oneNode.getRoot()));
    }

    /**
     * tests that getStack(), inorder_iterator(), hasNext(), and next() work as
     * intended
     */
    public void testIteratorEmpty() {
        // on empty bst
        assertNull(emptyBST.getStack());
        emptyBST.inorderIterator();
        assertNull(emptyBST.getStack());
        assertFalse(emptyBST.hasNext());
        emptyBST.insert(null);
        emptyBST.inorderIterator();
        assertFalse(emptyBST.hasNext());
        assertNull(emptyBST.next());
    }
    
    /**
     * tests the hasNext() and next() methods of iterator
     */
    public void testIteratorOneNode() {
        // on non-empty bst
        assertNull(oneNode.getStack());
        oneNode.inorderIterator();
        assertNotNull(oneNode.getStack());
        assertTrue(oneNode.hasNext());
        assertEquals("root", oneNode.next());
        assertFalse(oneNode.hasNext());
        assertNull(oneNode.next());
    }
    
    /**
     * tests the hasNext() and next() methods of iterator
     */
    public void testIteratorThreeNodes() {
        // populate tree
        BST<String> threeNodes = new BST<String>();
        threeNodes.insert("banana");
        threeNodes.insert("apple");
        threeNodes.insert("carrot");
        assertEquals("banana", threeNodes.getRoot().element);
        assertEquals("apple", threeNodes.getRoot().left.element);
        assertEquals("carrot", threeNodes.getRoot().right.element);
        
        // generate stack
        threeNodes.inorderIterator();
        assertNotNull(threeNodes.getStack());
        
        assertEquals(2, threeNodes.getStack().size());
        assertTrue(threeNodes.hasNext());
        assertEquals("apple", threeNodes.next());
        assertTrue(threeNodes.hasNext());
        assertEquals("banana", threeNodes.next());
        assertTrue(threeNodes.hasNext());
        assertEquals("carrot", threeNodes.next());
        assertFalse(threeNodes.hasNext());
    }

    /**
     * tests that getStack(), inorder_iterator(), hasNext(), and next() work as
     * intended.
     */
    public void testInnerIteratorEmpty() {
        // on empty bst
        assertNull(emptyBST.getStack());
        emptyBST.inorderIterator();
        emptyBST.setOtherStack();
        assertNull(emptyBST.getStack());
        assertNull(emptyBST.getInternalStack());
        assertFalse(emptyBST.hasNext());
        assertFalse(emptyBST.hasNextInner());
        emptyBST.insert(null);
        emptyBST.inorderIterator();
        emptyBST.setOtherStack();
        assertFalse(emptyBST.hasNextInner());
        assertNull(emptyBST.nextInner());
    }
    
    /**
     * tests the inner methods of iterator
     */
    public void testInnerIteratorOneNode() {
        assertNull(oneNode.getStack());
        oneNode.inorderIterator();
        oneNode.setOtherStack();
        assertNotNull(oneNode.getStack());
        assertNotNull(oneNode.getInternalStack());
        assertTrue(oneNode.hasNext());
        assertTrue(oneNode.hasNextInner());
        assertEquals("root", oneNode.next());
        assertFalse(oneNode.hasNext());
        assertTrue(oneNode.hasNextInner());
        assertEquals("root", oneNode.nextInner());
        assertFalse(oneNode.hasNextInner());
        assertNull(oneNode.nextInner());
    }
    
    /**
     * test the inner methods of iterator
     */
    public void testInnerIteratorThreeNodes() {
        BST<String> threeNodes = new BST<String>();
        threeNodes.insert("banana");
        threeNodes.insert("apple");
        threeNodes.insert("carrot");
        assertNull(threeNodes.getStack());
        assertEquals("banana", threeNodes.getRoot().element);
        assertEquals("apple", threeNodes.getRoot().left.element);
        assertEquals("carrot", threeNodes.getRoot().right.element);
        threeNodes.inorderIterator();
        assertEquals("apple", threeNodes.next());
        threeNodes.setOtherStack();
        assertTrue(threeNodes.hasNextInner());
        assertEquals("banana", threeNodes.nextInner());
        assertEquals("carrot", threeNodes.nextInner());
        assertTrue(threeNodes.hasNext());
        assertFalse(threeNodes.hasNextInner());
    }

}