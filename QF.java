
public class QF
{
	private int[] id;
	private int count;
	Counter aa = new Counter("array accesses");
	
	public QF(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
		{
			id[i] = i;
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
		aa.increment();
		return id[p];
	}
	public void union(int p, int q)
	{
		int pID = find(p);
		int qID = find(q);
		
		if (pID == qID) return;
		
		for (int i = 0; i < id.length; i++)
		{
			if (id[i] == pID)
			{
				aa.increment();
				id[i] = qID;
				aa.increment();
				StdOut.println("id[" + i + "]  " + id[i]);
				StdOut.println("array accesses " + aa.tally());
			}
		}
		count--;
	}
	
	public static void main(String[] args)
	{
		StdOut.println("Please enter the number of inputs (input pairs * 2): ");
		int N = StdIn.readInt();
		QF qf = new QF(N);
		StdOut.println("Please enter each input (1 per line): ");
		while (!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (qf.connected(p, q)) continue;
			qf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qf.count() + " components");
	}
	
/*
Please enter the number of inputs (input pairs * 2): 
16
Please enter each input (1 per line): 
9
0
id[9]  0
array accesses 6
9 0
3
4
id[3]  4
array accesses 6
3 4
5
8
id[5]  8
array accesses 6
5 8
7
2
id[7]  2
array accesses 6
7 2
2
1
id[2]  1
array accesses 6
id[7]  1
array accesses 8
2 1
5
7
id[5]  1
array accesses 6
id[8]  1
array accesses 8
5 7
0
3
id[0]  4
array accesses 6
id[9]  4
array accesses 8
0 3
4
2
id[0]  1
array accesses 6
id[3]  1
array accesses 8
id[4]  1
array accesses 10
id[9]  1
array accesses 12
4 2
*/
}
