
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBag implements Iterable<Integer> {
	public static int N;         // number of elements in bag
	private Node first;    // beginning of bag

	// helper linked list class
	private class Node {
		private int item;
		private Node next;
	}

	public MyBag() {
		first = null;
		N = 0;
	}

    // 1.3.26
	public void remove (int item) {
		// remove the item from MyBag
		Node prev = null;
		if (N == 1 && first.item == item) {
			first = null;
			N--;
		}
		
		for ( Node x = first; x != null; x = x.next) {  
			if (x.item != item) {
				prev = x;
			}
			
			else if (prev == null) {
				if (x.next != null) {
					first.next = x.next.next;
					first.item = x.next.item;
					N--;
				}
				else {
					first = null;
					N--;
				}
			}
			
			else {
				prev.next = x.next;
				N--;
			}
		}
	}

    // 1.3.27
	public int max () { return max (first); }
	public static int max (Node n) {
		//
		int m = 0;
		for (Node cur = n; cur != null; cur = cur.next) {
			if (cur.item > m) {
				m = cur.item;
			}
		}
		
		return m;
	}
    
    // 1.3.28
	public int maxRecursive () { return maxRecursive (first); }
	private static int maxRecursive (Node n) {
		if (n == null) {
			return 0;
		}
		
		return Math.max(n.item, maxRecursive(n.next));
	}

    // 1.3.30
	public void reverse () {
		// reverses the linked list
		  if (first == null) return;  
		  Node prev = null; 
		  
		  for ( Node next = first.next; next != null; next = first.next) {  
		    first.next = prev;  
		    prev = first;  
		    first = next;  
		  }  
		  
		  first.next = prev;  
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void add(int item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Iterator<Integer> iterator()  {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Integer> {
		private Node current = first;

		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Integer next() {
			if (!hasNext()) throw new NoSuchElementException();
			Integer item = current.item;
			current = current.next;
			return item;
		}
	}

	private static void print (String s, MyBag b) {
		StdOut.print (s + ": ");
		for (int i : b)
			StdOut.print (i + " ");
		StdOut.println ();
	}

	public static void main (String args[]) {
		MyBag b1 = new MyBag ();
		b1.reverse ();
		print ("reverse empty", b1);
		b1.add (1);
		print ("singleton", b1);
		b1.reverse ();
		print ("reverse singleton", b1);
		b1.add (2);
		print ("two", b1);
		b1.reverse ();
		print ("reverse two", b1);
		b1.reverse ();
		print ("reverse again", b1);
		for (int i = 3; i < 7; i++) {
			b1.add (i);
			b1.add (i);
		}
		print ("bigger list", b1);
		b1.reverse ();
		print ("reversed", b1);

		b1 = new MyBag ();
		b1.remove (4);
		print ("removed 4 from empty", b1);
		b1.add (1);
		b1.remove (4);
		print ("removed 4 from singelton", b1);
		b1.remove (1);
		print ("removed 1 from singelton", b1);
		for (int i = 1; i < 5; i++) {
			b1.add (i);
			b1.add (i);
		}
		for (int i = 1; i < 5; i++) {
			b1.add (i);
			b1.add (i);
			b1.add (i);
			b1.add (i);
			b1.add (i);
		}
		print ("longer list", b1);
		b1.remove(9);
		print ("removed all 9s", b1); // does nothing
		b1.remove(3);
		print ("removed all 3s", b1);
		b1.remove(1);
		print ("removed all 1s", b1);
		b1.remove(4);
		print ("removed all 4s", b1);
		b1.remove(2);
		print ("removed all 2s", b1); // should be empty
	}
}
