public class MyMinMaxPQ<Key extends Comparable<Key>>
{
	private Key[] pq;
	private int N = 0;
	
	public MyMinMaxPQ(int maxN)
	{ pq = (Key[]) new Comparable[maxN + 1]; }
	
	public boolean isEmpty()
	{ return N == 0; }
	
	public int size()
	{ return N; }
	
	public void insert(Key v)
	{
		pq[++N] = v;
		swim(N);
	}
	
	public Key delMax()
	{
		Key max = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		return max;
	}
	
	public Key delMin()
	{
		if (less(N - 1, N))
		{
			exch(N - 1, N);
			Key min = pq[N--];
			pq[N + 1] = null;
			return min;
		}
		
		else
		{
			Key min = pq[N--];
			pq[N + 1] = null;
			return min;
		}
	}
	
	public Key getMax()
	{ return pq[1]; }
	
	public Key getMin()
	{ return pq[N]; }
	
	public boolean less(int i, int j)
	{ return pq[i].compareTo(pq[j]) < 0; }
	
	public void exch(int i, int j)
	{ Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }
	
	public void swim(int k)
	{
		while (k > 1 && less(k / 2, k))
		{
			exch(k / 2, k);
			k = k / 2;
		}
	}
	
	public void sink(int k)
	{
		while (2 * k <= N)
		{
			int j = 2 * k;
			if (j < N && less(j, j + 1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	
	public static void main(String[] args)
	{
		int capacity = 16;
		MyMinMaxPQ a = new MyMinMaxPQ(capacity);
		
		// insert test
		a.insert(7);
		a.insert(9);
		a.insert(2);
		
		// delMax test
		StdOut.println("delMax");
		StdOut.println(a.delMax());
		StdOut.println(a.delMax());
		
		// insert
		a.insert(13);
		a.insert(4);
		
		// delMin test
		StdOut.println("delMin");
		StdOut.println(a.delMin());
		StdOut.println(a.delMin());
		
		// insert
		a.insert(12);
		a.insert(25);
		
		// getMax test
		StdOut.println("getMax");
		StdOut.println(a.getMax());
		StdOut.println(a.getMax());
		
		// insert
		a.insert(5);
		a.insert(2);;
		
		// getMin test
		StdOut.println("getMin");
		StdOut.println(a.getMin());
		StdOut.println(a.getMin());
	}
}