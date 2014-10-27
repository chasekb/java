/*************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *  
 *************************************************************************/

import java.util.Arrays;

public class MyBinarySearch {

	// precondition: array a[] is sorted
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if      (key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}

	public static void main(String[] args) {
		args = new String[] { "data/tinyW.txt", "data/tinyT.txt" };
		int[] whitelist = new In(args[0]).readAllInts();
		Arrays.sort(whitelist);
		
		// determine number of unique keys
		int N = whitelist.length;
		int unique = 0;
		for (int i = 0; i < (N - 1); i++) {
			if (whitelist[i] == whitelist[i + 1]) {
				unique++;
			}
		}
		// remove any duplicate keys in the whitelist, save to minusDups
		int j = 0;
		int minusDupsLength = N - unique;
		int[] minusDups = new int[minusDupsLength];
		for (int i = 0; i < (N - 1); i++) {
			if (whitelist[i] != whitelist[i + 1]) {
				minusDups[j++] = whitelist[i];
				}
			if (whitelist[N - 1] != whitelist[N - 2]) {
				minusDups[j] = whitelist[N - 1];
				}
			}

		StdIn.fromFile(args[1]);
		// read key; print if not in minusDups
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, minusDups) == -1)
				StdOut.println(key);
		}
	}
} // end of BinarySearch

