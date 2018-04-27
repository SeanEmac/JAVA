/** QuickSort.java: 
 *  Michael Madden, NUI Galway.
 *  Based on code from Goodrich & Tamassia, “Data Structures and Algorithms in Java”
 *  With some simplifications for clarity, and test code at the end.
 */

import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class QuickSort
{
	public static void main(String[] args)
	{
		String array[] = makeArray(1000);//original random array
		
		String randomArray[] = array.clone();//Clone the Original to be tested by Quicksort
		String ascendingArray[] = array.clone();//Clone the original to be put into Ascending order
		quickSort(ascendingArray, new StringComparator());
		
		// We now have 2 Arrays to test, Random Unsorted, Random Sorted Ascending.
		//randomArray
		//ascendingArray
		
		double randomstart = System.nanoTime();
		quickSort(randomArray, new StringComparator());
	    double randomend = System.nanoTime();
		System.out.println("RandomArrayTime:" +((randomend-randomstart)/1000000));
		
		double ascstart = System.nanoTime();
		quickSort(ascendingArray, new StringComparator());
	    double ascend = System.nanoTime();
		System.out.println("AscendingArrayTime:" +((ascend-ascstart)/1000000));
		
	}
	public static void quickSort (Object[] arr, Comparator c) {
	    if (arr.length < 2) return; // the array is already sorted in this case
	    
	    //Code for leftBound
		Object temp = arr[(arr.length-1)];
		arr[(arr.length-1)] = arr[0];
		arr[0] = temp;
	    
		//Code for midBound
		Object temp = arr[arr.length-1];
		arr[arr.length-1] = arr[(arr.length-1) / 2];
		arr[0 + (arr.length-1 - 0) / 2] = temp;
		
	    quickSortStep(arr, c, 0, arr.length-1); // call the recursive sort method
	}
	
	private static void quickSortStep (Object[] s, Comparator c,
	                              int leftBound, int rightBound ) 
	{
		if (leftBound >= rightBound) return; // the indices have crossed
	   Object temp;  // temp object used for swapping
	    
	   // Set the pivot to be the last element
	   Object pivotValue = s[rightBound];
	    
	   // Now partition the array 
	   int upIndex = leftBound;     // will scan rightward, 'up' the array
	   int downIndex = rightBound-1; // will scan leftward, 'down' the array
	   while (upIndex <= downIndex) 
	   { 
	       // scan right until larger than the pivot
	       while ( (upIndex <= downIndex) && (c.compare(s[upIndex], pivotValue)<=0) )
	    	   upIndex++; 
	       // scan leftward to find an element smaller than the pivot
	       while ( (downIndex >= upIndex) && (c.compare(s[downIndex], pivotValue)>=0))
	    	   downIndex--;
	       if (upIndex < downIndex) { // both elements were found
	          temp = s[downIndex]; 
		      s[downIndex] = s[upIndex]; // swap these elements
		      s[upIndex] = temp;
	       }
	   } // the loop continues until the indices cross
	    
	   int pivotIndex = upIndex;
	   temp = s[rightBound]; // swap pivot with the element at upIndex
	   s[rightBound] = s[pivotIndex]; 
	   s[pivotIndex] = temp; 
	 
	   // the pivot is now at upIndex, so recursively quicksort each side
	   quickSortStep(s, c, leftBound, pivotIndex-1);
	   quickSortStep(s, c, pivotIndex+1, rightBound);
	}

	/** M Madden: utility method to return string representation of array of strings */
	private static String array2String(String[] a)
	{
		String text="[";
		for (int i=0; i<a.length; i++) {
			text += a[i];
			if (i<a.length-1)
				text += ",";
		}
		text += "]";
		return text;
	}
	
	private static String[] makeArray(int size) {// make an array of doubles of size size 
		String randomArray[];
		randomArray = new String[size];
		for(int i  = 0; i < size; i++) {
			randomArray[i]= String.valueOf(Math.random());
		}
		return randomArray;//returned as a string
	}
}

/** M Madden: Comparator class for case-insensitive comaprison of strings */
class StringComparator implements Comparator
{
	public int compare(Object ob1, Object ob2)
	{
		String s1 = (String)ob1;
		String s2 = (String)ob2;
		//return s1.compareTo(s2); // use compareTo for case-sensitive comparison
		return s1.compareToIgnoreCase(s2);
	}
}