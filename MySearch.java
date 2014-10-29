
public class MySearch
{
	private boolean[] marked;
	private int count;
	
	public MySearch(Graph G, int s)
	{
		int source = s;
		int graphVerts = G.V();
		
        // build UF object
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(graphVerts);
        this.marked = new boolean[graphVerts];
		
        // union each edge of G
        for (int i = 0; i < graphVerts; i++)
        	for (int w : G.adj(i))
        		uf.union(i, w);
        
        // implement marked(v)
        count = -1;
        for (int i = 0; i < graphVerts; i++)
        	if (uf.connected(i, source))
        	{
        		marked[i] = true;
        		count++;
        	}
	}
	
	public boolean marked(int v) { return marked[v]; }
	
	public int count() { return count; }
	
	public static void main(String[] args)
	{
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt("9");	// args[1]
		MySearch ms = new MySearch(G, s);
		
		for (int v = 0; v < G.V(); v++)
			if (ms.marked(v)) StdOut.print(v + " ");
				StdOut.println();
		
		if (ms.count() != G.V())
			StdOut.print(" NOT ");
		StdOut.println(" connected");
		StdOut.println("ms.count = " + ms.count() + " G.V() = " + G.V());
	}
}
