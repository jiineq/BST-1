
import student.TestCase;

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
		assertTrue(emptyBST.getRoot() == null);
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
	    assertFalse(emptyBST.isEmpty());
	    assertTrue(emptyBST.contains("beyonce"));
	}
	
	/**
	 * tests that remove() works as intended
	 */
	public void testRemove() {
	    assertTrue(oneNode.contains("root"));
	    assertFalse(oneNode.isEmpty());
	    oneNode.remove("root");
	    assertFalse(oneNode.contains("root"));
	    assertTrue(oneNode.isEmpty());
	}
	
	/**
	 * tests that find() works as intended
	 */
	public void testFind() {
	 assertNull(emptyBST.find("nothing"));
	 assertEquals("root", oneNode.find("root"));
	}
	
	/**
	 * test that getDepth() and getRoot() works as intended
	 */
	public void testDepth() {
	    oneNode.insert("another one");
	    assertEquals(0, emptyBST.getDepth("root", emptyBST.getRoot()));
	    assertEquals(0, oneNode.getDepth("root", oneNode.getRoot()));
	    assertEquals(1, oneNode.getDepth("another one", oneNode.getRoot()));
	}
	
	/**
	 * tests that getStack(), inorder_iterator(), hasNext(), and next() work 
	 * as intended
	 */
	public void testIterator() {
	   // on empty bst
	    assertNull(emptyBST.getStack());
	    emptyBST.inorderIterator();
	    assertNull(emptyBST.getStack());
	    assertFalse(emptyBST.hasNext());
	    
	    // on non-empty bst
	    assertNull(oneNode.getStack());
	    oneNode.inorderIterator();
	    assertNotNull(oneNode.getStack());
	    assertTrue(oneNode.hasNext());
	    assertEquals("root", oneNode.next());
	    assertFalse(oneNode.hasNext());
	    
	    // on two node bst
	    BST<String> threeNodes = new BST<String>();
        threeNodes.insert("banana");
        threeNodes.insert("apple");
	    threeNodes.insert("carrot");
	    assertNull(threeNodes.getStack());
	    assertTrue(threeNodes.contains("apple"));
	    assertTrue(threeNodes.contains("banana"));
	    assertTrue(threeNodes.contains("carrot"));
	    assertEquals("banana", threeNodes.getRoot().element);
	    assertEquals("apple", threeNodes.getRoot().left.element);
	    assertEquals("carrot", threeNodes.getRoot().right.element);
	    threeNodes.inorderIterator();
	    assertNotNull(threeNodes.getStack());
	    assertEquals(3, threeNodes.getStack().size());
	    assertTrue(threeNodes.hasNext());
	    assertEquals("apple", threeNodes.next());
	    assertTrue(threeNodes.hasNext());
	    assertEquals("banana", threeNodes.next());
	    assertTrue(threeNodes.hasNext());
	    assertEquals("carrot", threeNodes.next());
	    assertFalse(threeNodes.hasNext());
	}	
}