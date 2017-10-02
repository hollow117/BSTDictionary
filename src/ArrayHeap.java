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

import java.util.NoSuchElementException;

/**
 * ArrayHeap.java will implementw the PriorityQueueADT interface using an 
 * array-based implementation of a max heap. In addition to the methods 
 * specified in the PriorityQueueADT interface, the ArrayHeap class provides
 * two constructors: a default (no argument) constructor and a constructor 
 * that takes an initial size (an integer) for the underlying array.
 * ArrayHeap class must compare elements in the heap using the values returned 
 * by getPriority.
 * 
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */
public class ArrayHeap<E extends Prioritizable> implements PriorityQueueADT<E> {

    // fields
    private static final int INIT_SIZE = 100;
    private E[] info;
    private int numElem;
    
    /**
   	 * This is a generic constructor.
   	 * 
   	 * @param none
   	 * @return none
   	 */
    public ArrayHeap() {
    	this(INIT_SIZE);
    	numElem = 0;
    }
    
    /**
   	 * This is one argument constructor.
   	 * 
   	 * @param int size	size of the initial array
   	 * @return none
   	 */
    public ArrayHeap (int size){
    	if (size <0) {
    		throw new IllegalArgumentException();
    	}
    	info = (E[]) new Prioritizable[size+1];
    	numElem = 0;
    }
    
   
    /**
	 * This method checks whether the heap is empty 
	 *
	 * @param 	none
	 * @return 	true/false	depends on whether heap is empty
	 */
    public boolean isEmpty() {
    	if(numElem == 0)
    		return true;
    	else
    		return false;
    }
    
    /**
	 * This method inserts a new item into the heap using standard
	 * heap rules
	 *
	 * @param 	E item
	 * @return 	none
	 */
    public void insert(E item) {
    	if(info.length ==numElem + 1){
    		E[] newinfo = (E[]) new Prioritizable [info.length * 2];
    		for(int i = 0 ; i< info.length ; i++){
    			newinfo[i] = info[i];
    		}
    		info = newinfo;
    	}
       	numElem++;
       	int index = numElem;
       	info[numElem] = item;
       	while(index > 1 && info[index].getPriority() > 
       			(info[index/2].getPriority())){
       		info[index] = info[index/2];
       		info[index/2] = item;
       		index = index/2;
       	}
    }

    /**
   	 * This method removes the value with the max priority from the heap and 
   	 * rearranges the heap accordingly
   	 *
   	 * @param 	none
   	 * @return 	eToReturn	value with max priority
   	 */
    public E removeMax() {
    	if(numElem == 0) 
    		return null;
    	else{
    		E eToReturn = info[1];
    		info[1] = info[numElem];
    		info[numElem] = null;
    		numElem--;
    		int index = 1;
    		E temp;
    		while(index * 2 < numElem && (info[index].getPriority() < 
    				info[index * 2].getPriority() || 
    				info[index].getPriority() < 
    				info[index * 2 + 1].getPriority())){
    			temp = info[index];
    			if(info[index * 2].getPriority() > 
    					info[index * 2 + 1].getPriority()){
    				info[index] = info[index * 2];
    				info[index * 2] = temp;
    				index = index * 2;
    			} else {
    				info[index] = info[index * 2 + 1];
    				info[index * 2 + 1] = temp;
    				index = index * 2 + 1;
    			}
    		}
    		if(index * 2 == numElem && info[index].getPriority() < 
    				info[index * 2].getPriority()){
    			temp = info[index];
    			info[index] = info[index * 2];
    			info[index * 2] = temp;
    		}
    		return eToReturn;
    	}
    }

    /**
   	 * This method returns the max value without removing it
   	 *
   	 * @param 	none
   	 * @return 	info[1]	max value stored in heap
   	 */
    public E getMax() {
    	if (numElem == 0){
    		throw new NoSuchElementException();
    	}
        return info[1]; 
    }

    /**
   	 * This method returns the number of elements in the priority queue
   	 *
   	 * @param 	none
   	 * @return 	numElem	number of elements stored in heap
   	 */
    public int size() {
        return numElem;
    }
}
