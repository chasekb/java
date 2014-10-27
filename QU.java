
public class QU
{
	private int[] id;
	private int count;
	Counter aa = new Counter("array accesses");
	
	public QU(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
		{
			aa.increment();
			id[i] = i;
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
		id[i] = j;
		aa.increment();
		
		StdOut.println("id[" + i + "]  " + id[i]);
		StdOut.println("array accesses " + aa.tally());
		count--;
	}
	
	public static void main(String[] args)
	{
		StdOut.println("Please enter the number of inputs (input pairs * 2): ");
		int N = StdIn.readInt();
		QU qu = new QU(N);
		StdOut.println("Please enter each input (1 per line): ");
		while (!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (qu.connected(p, q)) continue;
			qu.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qu.count() + " components");
	}
/*	
Please enter the number of inputs (input pairs * 2): 
16
Please enter each input (1 per line): 
9
0
id[9]  0
array accesses 1
9 0
3
4
id[3]  4
array accesses 1
3 4
5
8
id[5]  8
array accesses 1
5 8
7
2
id[7]  2
array accesses 1
7 2
2
1
id[2]  1
array accesses 1
2 1
5
7
id[8]  1
array accesses 13
5 7
0
3
id[0]  4
array accesses 5
0 3
4
2
id[4]  1
array accesses 5
4 2
*/
}
