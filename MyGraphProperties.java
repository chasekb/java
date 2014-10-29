
public class MyGraphProperties 
{
	private int[] furthestV;
	private int[] eccentricity;
	private int N;
	private int d;
	private int r;
	private int c;
	
	public MyGraphProperties(Graph G)
	{
		// throw an exception if G is not connected
		CC cc = new CC(G);
		if (cc.size(0) != G.V()) throw new UnsupportedOperationException(); 
		
		// find the shortest path to each vertex from each vertex
		N = G.V();
		furthestV = new int[N];
		eccentricity = new int[N];

		// find furthest connected vertex from each vertex
		for (int i = 0; i < N; i++)
		{
			BreadthFirstPaths bfp = new BreadthFirstPaths(G, i);	

			for (int j = 0; j < N; j++)
				if (bfp.hasPathTo(j))
					if (bfp.distTo(j) > furthestV[i])
					{
						furthestV[i] = j;
						// calculate the shortest path to
						// the furthest vertex
						eccentricity[i] = bfp.distTo(j);
					}
		}
	}
	
	public int diameter()
	{
		// return the maximum eccentricity of any vertex
		for (int i = 0; i < N; i++)
			if (eccentricity[i] > d)
				d = eccentricity[i];
		
		return d;
	}
	
	public int radius()
	{
		// return the minimum eccentricity of any vertex
		r = d;
		for (int i = 0; i < N; i++)
			if (eccentricity[i] < r)
				r = eccentricity[i];
		
		return r;		
	}
	
	public int center()
	{
		// return the vertex whose eccentricity is the radius
		for (int i = 0; i < N; i++)
			if (eccentricity[i] == r)
				c = i;
		
		return c;
	}
	
	public static void main(String[] args)
	{
		Graph G = new Graph(new In(args[0]));
		MyGraphProperties mg = new MyGraphProperties(G);
		
		StdOut.println("diameter = " + mg.diameter());
		StdOut.println("radius =   " + mg.radius());
		StdOut.println("center =   " + mg.center());
	}
}
