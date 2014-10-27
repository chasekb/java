import java.util.Iterator;

public class ResizingArrayDeque<Item> implements Iterable<Item>
{
	private Item[] a = (Item[]) new Object[1];
	private int N = 0;
	
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
	private void resize(int max)
	{
		// Move stack to a new array of size max
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) { temp[i] = a[i]; }
		a = temp;
	}
	
	public void pushLeft(Item item)
	{
		// add item to left end of array a
		if (N == 0)
		{
			resize(2);
			a[N++] = item;
			return;
		}
		else
		{
			if (N == a.length) { resize(2*a.length); }
			Item[] temp = (Item[]) new Object[a.length];
			for (int i = 0; i < a.length; i++)
			{
				temp[i] = a[i];
			}
			
			a[0] = item;			
			for (int i = 0; i < N; i++)
			{
				a[i + 1] = temp[i];
				//StdOut.println("a[" + i + "] " + a[i] + " temp [" + i + "] " + temp[i]);
			}
		}
		N++;
	}
	
	public void pushRight(Item item)
	{
		// add item to right end of array a
		if (N == 0)
		{
			resize(2);
			a[N++] = item;
			return;
		}
		else if (N == a.length) { resize(2*a.length); }
		//StdOut.println("R N: " + N + " a.length: " + a.length);		
		a[N++] = item;
	}
	
	public Item popLeft()
	{
		// remove item from left end of array a
		Item item = a[0];
		//StdOut.println("L N: " + N + " a.length: " + a.length + " a[0] " + a[0] + " a[1] " + a[1]);	
		for (int i = 0; i < (a.length - 1); i++)
		{
			a[i] = a[i + 1];
		}
		N--;
		if (N > 0 && N == a.length/4) resize(a.length/2);
		return item;
	}
	
	public Item popRight()
	{
		// remove item from right end of array a
		//StdOut.println("R N: " + N + " a.length: " + a.length + " a[" + (N - 1) + "] " + a[N - 1]);		
		Item item = a[--N];
		if (N > 0 && N == a.length/4) resize(a.length/2);
		return item;
	}
	
	public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

	private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = N;
		public boolean hasNext() { return i > 0; }
		public void remove() { }
		public Item next() { return a[--i]; }
	}
	
	// Write a test to ensure that your methods work!
	public static void main (String args[])
	{
		// create resizing array deque d
		ResizingArrayDeque<Integer> d = new ResizingArrayDeque<Integer>();
		int M = 65;
		int rando;

		// fill d with random ints pushRight
		for (int i = 0; i < M; i++)
		{
			rando = StdRandom.uniform(10, 99);
			d.pushRight(rando);
			StdOut.println("pushRight " + rando + " (" + d.size() + " left on deque)");
		}

		// print contents of Deque d
		StdOut.println("\nContents of Deque d");
		for (int s : d) { StdOut.println(s); }
		StdOut.println("\n");
		
		// popRight deque d
		StdOut.println("popRight Deque d");
		while (!d.isEmpty())
		{
			StdOut.println("popRight " + d.popRight() + " (" + d.size() + " left on deque)");
		}

		StdOut.println("\n");
		// create deque e
		ResizingArrayDeque<Integer> e = new ResizingArrayDeque<Integer>();
		int L = 65;
		int rando2;
		StdOut.println("Deque E: pushLeft in\n");

		// fill e with random ints pushLeft
		for (int i = 0; i < L; i++)
		{
			rando2 = StdRandom.uniform(10, 99);
			e.pushLeft(rando2);
			StdOut.println("pushLeft " + rando2 + " (" + e.size() + " left on deque)");
		}

		/*// print contents of Deque e
		StdOut.println("\nContents of Deque e");
		for (int s : e) { StdOut.println(s); }*/
		StdOut.println("\n");
		
		// popLeft deque e
		StdOut.println("popLeft Deque e");
		while (!e.isEmpty())
		{
			StdOut.println("popLeft  " + e.popLeft() + " (" + e.size() + " left on deque)");
		}
	}		
}
