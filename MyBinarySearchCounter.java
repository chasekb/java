import java.util.Arrays;

public class MyBinarySearchCounter {

	// precondition: array a[] is sorted
	public static int rank(int key, int[] a, Counter c) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			c.increment();
			if (key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		args = new String[] { "data/tinyW.txt", "data/tinyT.txt" };
		int[] whitelist = new In(args[0]).readAllInts();
		Arrays.sort(whitelist);
		
		// remove any duplicate keys in the whitelist, save to minusDups
		int N = whitelist.length;
		int[] minusDups = new int[N];
		for (int i = 0; i < N; i++) {
			minusDups[i] = whitelist[i];
		}

		StdIn.fromFile(args[1]);
		// read key; print if not in minusDups
		Counter c = new Counter("keys");
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, minusDups, c) == -1)
				StdOut.println(key);
		}
		
		// print total keys examined
		StdOut.println("Total number of keys examined: " + c);
	}
} // end of BinarySearch