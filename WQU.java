
public class WQU
{
	private int[] id;
	private int[] sz;
	private int count;
	Counter aa = new Counter("array accesses");
	
	public WQU(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
		{
			id[i] = i;
			aa.increment();
		}
		sz = new int[N];
		for (int i = 0; i < N; i++)
		{
			sz[i] = 1;
			aa.increment();
		}
	}
	
	public int count() { return count; }
	public boolean connected(int p, int q)
	{
		aa.reset();
		return find(p) == find(q);
	}
	public int find(int p)
	{
		while (p != id[p])
		{
			aa.increment();
			p = id[p];
			aa.increment();
		}
		return p;
	}
	public void union(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		if (i == j) return;
		
		if (sz[i] < sz[j])
		{
			aa.increment();
			aa.increment();
			id[i] = j;
			aa.increment();
			sz[j] += sz[i];
			aa.increment();
			aa.increment();
		}
		else
		{
			aa.increment();
			aa.increment();
			id[j] = i;
			aa.increment();
			sz[i] += sz[j];
			aa.increment();
			aa.increment();
		}
		
		StdOut.println("id[" + i + "]  " + id[i]);
		StdOut.println("array accesses " + aa.tally());
		count--;
	}
	
	public static void main(String[] args)
	{
		StdOut.println("Please enter the number of inputs (input pairs * 2): ");
		int N = StdIn.readInt();
		WQU wqu = new WQU(N);
		StdOut.println("Please enter each input (1 per line): ");
		while (!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (wqu.connected(p, q)) continue;
			wqu.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(wqu.count() + " components");
	}
/*	
Please enter the number of inputs (input pairs * 2): 
16
Please enter each input (1 per line): 
9
0
id[9]  9
array accesses 5
9 0
3
4
id[3]  3
array accesses 5
3 4
5
8
id[5]  5
array accesses 5
5 8
7
2
id[7]  7
array accesses 5
7 2
2
1
id[7]  7
array accesses 9
2 1
5
7
id[5]  7
array accesses 5
5 7
0
3
id[9]  9
array accesses 9
0 3
4
2
id[9]  7
array accesses 17
4 2
*/
}
