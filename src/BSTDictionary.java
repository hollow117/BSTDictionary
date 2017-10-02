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
import java.util.Iterator;

/**
 * This class implements the DictionaryADT interface using a binary search tree 
 * of BSTnodes. Binary search tree implementation is expected to perform the 
 * insert, delete, and lookup operations with a worst-case complexity of 
 * O(height of BST). All necessary comparisons use the compareTo method. 
 * 
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */
public class BSTDictionary<K extends Comparable<K>> implements DictionaryADT<K> {
    // fields
	private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the dictionary

    /**
   	 * This is a generic constructor.
   	 * 
   	 * @param none
   	 * @return none
   	 */
    public BSTDictionary() {
    	root = null;
    	numItems = 0;
    }
    
    /**
	 * This method inserts a new KeyWord with a given key into the 
	 * BSTDictionary
	 *
	 * @param 	K key	key that will be added
	 * @return 	none
	 */
    public void insert(K key) throws DuplicateException {
    	root = insert(root, key);
    }
    
    /**
  	 * This method is a helper method for insert that handles recursive calls
  	 *
  	 * @param	BSTnode<K> n	tree to insert key
  	 * @param 	K key			key that will be added
  	 * @return 	BSTnode<K> n	new root of tree
  	 */
    private BSTnode<K> insert(BSTnode<K> n, K key) throws DuplicateException {
    	if (n == null) {
        	numItems++;
            return new BSTnode<K>(key, null, null);
        }
        if (n.getKey().equals(key)) {
            throw new DuplicateException();
        }
        if (key.compareTo(n.getKey()) < 0) {
            // add key to the left subtree
            n.setLeft( insert(n.getLeft(), key) );
            return n;
        }
        else {
            // add key to the right subtree
            n.setRight( insert(n.getRight(), key) );
            return n;
        }
    }

    /**
	 * This method removes keywords from the dictionary
	 *
	 * @param 	K key
	 * @return 	true / false	depending on whether key exists in the tree	
	 */
    public boolean delete(K key) {
    	if (lookup(key) != null){
    	root = delete(root, key); 
    	numItems--;
    	return true;
    }
    	return false;
    }
    
    /**
   	 * This method is delete helper method
   	 *
   	 * @param	BSTnode<K> n	root of tree before delete
   	 * @param 	K key			key to delete
   	 * @return 	BSTnode<K> n	root of new tree
   	 */
    private BSTnode<K> delete(BSTnode<K> n, K key) {
        if (n == null) {
            return null;
        }
        if (key.equals(n.getKey())) {
           // n is the node to be removed
        	 if (n.getLeft() == null && n.getRight() == null) {
                 return null;
             }
             if (n.getLeft() == null) {
                 return n.getRight();
             }
             if (n.getRight() == null) {
                 return n.getLeft();
             }
             K smallVal = smallest(n.getRight());
             n.setKey(smallVal);
             n.setRight( delete(n.getRight(), smallVal) );
             return n; 
        } else if (key.compareTo(n.getKey()) < 0) {
            n.setLeft( delete(n.getLeft(), key) );
            return n;
        } else {
            n.setRight( delete(n.getRight(), key) );
            return n;
        }
    }
    
    /**
  	 * This method finds smallest value, used in delete method
  	 *
  	 * @param 	BSTnode<K> n
  	 * @return 	smallest key	
  	 */
    private K smallest(BSTnode<K> n){
    // precondition: n is not null
    if (n == null) {
    	return null;
    }
     // postcondition: return the smallest value in the subtree rooted at n
     if (n.getLeft() == null) {
         return n.getKey();
     } else {
         return smallest(n.getLeft());
     }
 }
    
    /**
  	 * This method finds and returns a keyword
  	 *
  	 * @param 	K key
  	 * @return 	key from helper method	
  	 */
    public K lookup(K key) {
   	 	return lookup(root, key); // replace this stub with your code
    }
    
   
    /**
  	 * This method is a helper method for lookup
  	 *
  	 * @param 	BSTnode<K> n	root of tree where key will be looked up
  	 * @param   K key			key that will be looked up
  	 * @return 	key found in the tree
  	 */
    private K lookup(BSTnode<K> n, K key) {
        if (n == null) {
            return null;
        }
        if (n.getKey().equals(key)) {
            return n.getKey();
        }
        if (key.compareTo(n.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return lookup(n.getLeft(), key);
        } else {
            // key > this node's key; look in right subtree
            return lookup(n.getRight(), key);
        }
    }
    
    /**
  	 * This method provides whether tree is empty
  	 *
  	 * @param 	none
  	 * @return 	whether tree is empty	
  	 */
    public boolean isEmpty() {
        return numItems == 0; 
    }

    /**
  	 * This method provides size of the tree
  	 *
  	 * @param 	none
  	 * @return 	numItems	
  	 */
    public int size() {
        return numItems;
    }
    
    /**
  	 * This method provides total path length of tree
  	 *
  	 * @param 	none
  	 * @return 	total path of tree
  	 */
    public int totalPathLength() {
    	if(root==null) // path length is zero
    		return 0;
    	else
    		return totalPathLength(root, 0);
    }
    /**
  	 * This method is a helper method for providing total path length of tree
  	 *
  	 * @param 	BSTnode<K> root
  	 * @param 	int depth
  	 * @return 	numItems	
  	 */
    private int totalPathLength(BSTnode<K> root, int depth){
        int totalCount;
        int depthTree= depth;
        int totalCountleft = 0;
        int totalCountright = 0;
        
        if(root.getLeft()== null && root.getRight() == null){
        	return depthTree;
        }
        if (root.getLeft() != null){
        	totalCountleft += totalPathLength(root.getLeft(), depthTree+1);
        } else {
        	totalCountleft+= 0;
        }
        if (root.getRight() != null) {
        	totalCountright += totalPathLength(root.getRight(), depthTree+1);
        } else {
        	totalCountright +=0;
        }
        totalCount = depthTree + totalCountleft + totalCountright ;
        return totalCount;
        }
    
    /**
  	 * This method iterates the tree
  	 *
  	 * @param 	none
  	 * @return 	iterate over the tree	
  	 */
    public Iterator<K> iterator() {
        return new BSTDictionaryIterator<K>(root);  
    }
}
