///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  WordCloudGenerator.java
// File:             WordCloudGenerator.java, ArrayHeap.java, 
//					 BSTDictionary.java, BSTDictionaryIterator.java,
//					 KeyWord.java
// Semester:         CS367 Summer 2016
//
// Author:           Jason Choe choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Amanda Strominger
// Lab Section:      001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jason Leduc
// Email:            jlleduc@wisc.edu
// CS Login:         leduc
// Lecturer's Name:  Amanda Strominger
// Lab Section:      001
//
////////////////////////////////////////////////////////////////////////////////
import java.util.*;

/**
 * BSTDictionaryIterator implements an iterator for a binary search tree (BST)
 * implementation of a Dictionary.  The iterator iterates over the tree in 
 * order of the key values (from smallest to largest).
 * 
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */

public class BSTDictionaryIterator<K> implements Iterator<K> {
	// This class uses a Stack and push/pop nodes as you iterate through the 
    // BST. The constructor pushes all the nodes needed so the *first* call 
    // to next() returns the value in the node with the smallest key.

	// the stack
	private Stack <BSTnode<K>> top;
	
	 /**
   	 * This is one argument constructor.
   	 * 
   	 * @param BSTnode<K> root	root of iterable tree
   	 * @return none
   	 */
	// constructor
	public BSTDictionaryIterator (BSTnode<K> root) {
		top = new Stack <BSTnode<K>> ();
		BSTnode<K> treeNode = root;
		while (treeNode != null) {
			top.push(treeNode);
			treeNode = treeNode.getLeft();
		}
	}
	
	 /**
	 * This method tells whether there are more elements left in the stack
	 *
	 * @param 	none
	 * @return 	true/false	depends on whether there is next element
	 */
    public boolean hasNext() {
        return !top.isEmpty(); 
    }

    /**
   	 * This method gets the next element from the dictionary
   	 *
   	 * @param 	none
   	 * @return 	next element from the dictionary
   	 */
    public K next() {
    	BSTnode<K> popped = top.pop();
    	if (popped.getRight() != null){
    		BSTnode<K> childTraverse = popped.getRight();
    		while (childTraverse != null){
    		top.push(childTraverse);
    		childTraverse = childTraverse.getLeft();
    		}
    	}
        return popped.getKey();
    }

    // we won't use a remove method
    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }    
}
