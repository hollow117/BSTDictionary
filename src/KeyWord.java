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

/**
 * KeyWord objects will be stored in dictionary, each of which contains a 
 * word and a non-negative integer representing the number of times the word 
 * occurs in the input file. For the purposes of the KeyWord class, a word is 
 * a non-empty sequence of characters in which all the letters have been 
 * converted to lower-case. 
 * 
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */
public class KeyWord implements Comparable <KeyWord> , Prioritizable {
	
	// fields
	private String keyWord; // word found in file
	private int numOccur; // how many times the word is found in the file
	
    /**
   	 * This is one argument constructor.
   	 * 
   	 * @param String keyWord	a keyWord
   	 * @return none
   	 */
	public KeyWord(String keyWord) {
		this.keyWord = keyWord;
		this.numOccur = 0;
	}
	
	 /**
		 * This method is used to build binary search tree dictionary
		 *
		 * @param 	KeyWord other 	the other keyword to compare to
		 * @return 	1 if keyword has higher priority, 0 if the same, -1
		 * 			if priority is less
		 */
	public int compareTo (KeyWord other){
		return (this.keyWord.toLowerCase()).compareTo(other.getWord().toLowerCase());
	}
	
	/**
	 * This method is used to compare two objects for equality
	 *
	 * @param 	Object other 	object to compare to 
	 * @return 	true if they are the same, false otherwise
	 */
	@Override
	public boolean equals(Object other){
		if (other != null && this.keyWord.equals(((KeyWord)other).getWord())){
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used to get number of times word occurred in document
	 *
	 * @param 	none
	 * @return 	number of occurrences
	 */
	public int getOccurrences(){
		return numOccur;
	}

	/**
	 * This method is used to return number of occurrences of this keyword
	 *
	 * @param 	none
	 * @return 	number of occurrences
	 */
	public int getPriority(){
		return numOccur;
	}
	
	/**
	 * This method is used to return keyword
	 *
	 * @param 	none
	 * @return 	keyword
	 */
	public String getWord() {
		return keyWord;
	}
	
	/**
	 * This method is used to increment the number of occurrences
	 *
	 * @param 	none
	 * @return 	increase number of occurrences
	 */
	public void increment() {
		numOccur++;
	}
}
