
public class MyDegrees
{
	private int[] id;			// array containing vertex indegree
	private int[] od;			// array containing vertex outdegree
	private Bag<Integer> so;	// bag containing sources
	private Bag<Integer> si;	// bag containing sinks
	
	private int numV;	// G.V()
	
	public MyDegrees(Digraph G)
	{
		numV = G.V();
		id = new int[numV];
		od = new int[numV];
		so = new Bag<Integer>();
		si = new Bag<Integer>();
		int count;
		
		// calculate indegrees
		for (int i = 0; i < numV; i++)
		{
			count = 0;
			for (int cV : G.adj(i))
				count++;
			id[i] = count;
			
			// determine sources
			if (id[i] == 0)
				so.add(i);
		}

		// calculate outdegrees
		Digraph R = G.reverse();
		for (int i = 0; i < numV; i++)
		{
			count = 0;			
			for (int cV : R.adj(i))
				count++;
			od[i] = count;
			
			// determine sinks
			if (od[i] == 0)
				si.add(i);
		}
	}
	
	public int indegree(int v)
	{ return id[v]; }
	
	public int outdegree(int v)
	{ return od[v]; }
	
	Iterable<Integer> sources()
	{
        if (!so.iterator().hasNext()) return null;
		return so;
	}
	
	Iterable<Integer> sinks()
	{
        if (!si.iterator().hasNext()) return null;
		return si;
	}
	
	boolean isMap()
	{
		for (int i = 0; i < od.length; i++)
			if (od[i] != 1) return false;
		return true;
	}
	
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        MyDegrees M = new MyDegrees(G);
        StdOut.println(G);
        for (int i = 0; i < G.V(); i++)
        {
        	StdOut.println("indegree of vertex  " + i + " = " + M.indegree(i));
        	StdOut.println("outdegree of vertex " + i + " = " + M.outdegree(i));
        }
        
        StdOut.println("\n");
        
        for (int each : M.sources())
        	StdOut.println("source: " + each);
        for (int each : M.sinks())
        	StdOut.println("sink:   " + each);
        
        StdOut.println("\n");
        StdOut.println("Graph is a map: " + M.isMap());
    }

}
