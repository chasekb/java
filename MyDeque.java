
import java.util.Iterator;
import java.util.ConcurrentModificationException;

// 1.3.33 Doubly linked version
//Supports adding and removing items at both ends
public class MyDeque<Item> implements Iterable<Item>
{
	private int N;
	private Node head;
	private Node tail;
	private Counter modCount = new Counter("modCount");
	
	private class Node
	{
		private Item item;
		private Node next;
		private Node previous;
	}
	
	public MyDeque()
	{
		head = null;
		tail = null;
		N = 0;
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void pushLeft(Item item)
	{
		// add new element to left side of deque
		if (item == null) { throw new java.lang.NullPointerException(); }
		if (N == 0)
		{
			head = new Node();
			head.item = item;
			head.next = null;
			head.previous = null;
			tail = head;
		}
		else
		{
			Node oldHead = head;
			head = new Node();
			head.item = item;
			head.next = oldHead;
			head.previous = null;
			oldHead.previous = head;
		}
		N++;
		modCount.increment();
	} 
	
	public void pushRight(Item item)
	{
		// add new element to right side of deque
		if (item == null) { throw new java.lang.NullPointerException(); }
		if (N == 0)
		{
			tail = new Node();
			tail.item = item;
			tail.next = null;
			tail.previous = null;
			head = tail;
		}
		else
		{
			Node oldTail = tail;
			tail = new Node();
			tail.item = item;
			tail.previous = oldTail;
			oldTail.next = tail;
		}
		N++;
		modCount.increment();
	}
	
	public Item popLeft()
	{
		// remove item from left side of deque
		if (N == 0) { throw new java.util.NoSuchElementException(); }
		else
		{ 
			Item item = head.item;
			if (N != 1)
			{
				head = head.next;
				head.previous = null;
			}
			else
			{
				head = null;
				tail = null;
			}
			N--;
			modCount.increment();
			return item;
		}	
	}
	
	public Item popRight()
	{
		// remove item from right side of deque
		if (N == 0) { throw new java.util.NoSuchElementException(); }
		else
		{
			Item item = tail.item;
			if (N != 1)
			{
				tail = tail.previous;
				tail.next = null;
			}
			else
			{
				head = null;
				tail = null;
			}
			N--;
			modCount.increment();
			return item;
		}		
	}		
	
    // 1.3.50
	public Iterator<Item> iterator() { return new DequeIterator(); }
	
	private class DequeIterator implements Iterator<Item>
	{
		private Node current = head;
		private Counter expectedModCount = modCount;
		
		public boolean hasNext()
		{
		    if (modCount != expectedModCount)
		    {
		    	// Throw a java.util.ConcurrentModificationException if the client modifies.
		    	throw new ConcurrentModificationException();
		    }
			return current != null;
		}
		
		public void remove() { throw new java.lang.UnsupportedOperationException(); }
		
		public Item next()
		{	
		    if (modCount != expectedModCount)
		    {
		    	// Throw a java.util.ConcurrentModificationException if the client modifies.
		    	throw new ConcurrentModificationException();
		    }
			if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
		}
	}
	
	// 1.3.47 -- you can destroy "that"
	public void concat(MyDeque<Item> that)
	// concatenates two queues, stacks, or steques
	{
		tail.next = head;
		head.previous = tail;
		while(!that.isEmpty()) { pushRight(that.popLeft()); }		
	}
		
	
	// Write a test to ensure that your methods work!
	public static void main (String args[])
	{
		// create deque d
		MyDeque<Integer> d = new MyDeque<Integer>();
		int M = 5;
		int rando;
		StdOut.println("Deque D: pushRight in");

		// fill d with random ints pushRight
		for (int i = 0; i < M; i++)
		{
			rando = StdRandom.uniform(10, 20);
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
		MyDeque<Integer> e = new MyDeque<Integer>();
		int L = 5;
		int rando2;
		StdOut.println("Deque E: pushLeft in\n");

		// fill e with random ints pushLeft
		for (int i = 0; i < L; i++)
		{
			rando2 = StdRandom.uniform(10, 20);
			e.pushLeft(rando2);
			StdOut.println("pushLeft " + rando2 + " (" + e.size() + " left on deque)");
		}

		// print contents of Deque e
		StdOut.println("\nContents of Deque e");
		for (int s : e) { StdOut.println(s); }
		StdOut.println("\n");
		
		// popLeft deque e
		StdOut.println("popLeft Deque e");
		while (!e.isEmpty())
		{
			StdOut.println("popLeft  " + e.popLeft() + " (" + e.size() + " left on deque)");
		}

		StdOut.println("\n");
		// create deque f
		MyDeque<Integer> f = new MyDeque<Integer>();
		int K = 5;
		int rando3;
		StdOut.println("Deque F: pushLeft in");

		// fill f with random ints pushLeft
		for (int i = 0; i < K; i++)
		{
			rando3 = StdRandom.uniform(10, 20);
			f.pushLeft(rando3);
			StdOut.println("pushLeft  " + rando3 + " (" + f.size() + " left on deque)");
		}

		// print contents of Deque f
		StdOut.println("\nContents of Deque f");
		for (int s : f) { StdOut.println(s); }
		StdOut.println("\n");
		
		// create deque g
		MyDeque<Integer> g = new MyDeque<Integer>();
		int I = 5;
		int rando4;
		StdOut.println("Deque G: pushRight in");

		// fill g with random ints
		for (int i = 0; i < I; i++)
		{
			rando4 = StdRandom.uniform(10, 20);
			g.pushRight(rando4);
			StdOut.println("pushRight " + rando4 + " (" + g.size() + " left on deque)");
		}

		// print contents of Deque g
		StdOut.println("\nContents of Deque g");
		for (int s : g) { StdOut.println(s); }
		StdOut.println("\n");
		
		// concat g to f
		StdOut.println("\nconcatenating deque g to deque f");
		f.concat(g);
		StdOut.println("\nconcat complete");
		
		// print contents of Deque f
		StdOut.println("\nContents of Deque f");
		for (int s : f) { StdOut.println(s); }
		StdOut.println("\n");

		StdOut.println("\n");
		// popRight deque f
		StdOut.println("popRight Deque f");
		while (!f.isEmpty())
		{
			StdOut.println("popRight " + f.popRight() + " (" + f.size() + " left on deque)");
		}
	}
}