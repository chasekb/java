/*
 * Kahlil Bernard Chase
 * CSC 421
 * Problem set 5
 * 17 November 2014
 * MST.java
 * 
 * Program MST (30 points) Implement one of the minimal spanning tree algorithms 
 * presented in class (Prim's or Kruskal's) in the language of your choice.
 */

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;

public class MST {

	private static int numVertices = 0;
	private static String tempLine;
	private static int tempInt;
	private static int weight;
	private static int lo;
	public static int[][] adjMat;
	public static int[] primKey;
	public static int[] primPi;

	public static void main(String[] args)
	{
		String filename = "data/MSTadjacencyMatrix.txt";
		processFile(filename);
		MST mst = new MST(adjMat, 0, 0);
		System.out.println(mst.weight());
	}

	public MST(final int[][] G, final int w, final int r)
	{
		// minimal spanning tree using prim's
		prims(G, w, r);
	}
	
	public static void prims(int[][] graph, int w, int r)
	{
		// prim's algorithm for minimal spanning tree
		// set key and parent for each vertex in graph
		for (int i = 0; i < numVertices; i++)
		{
			primKey[i] = 999;
			primPi[i] = -1;
		}
		
		// set root key to 0
		primKey[r] = 0;

		// enqueue G.V
		PriorityQueue<Integer> primQ = new PriorityQueue<Integer>(numVertices, 
				new Comparator<Integer>()
				{
					public int compare(Integer u, Integer v)
					{
						return Integer.compare(primKey[u],  primKey[v]);
					}
				}	);
		
		for (int i = 0; i < numVertices; i++)
		{
			primQ.offer(i);
		}
		
		// while Q is not empty
		while (!primQ.isEmpty())
		{
			// extract-min(q)
			int min = (int) primQ.remove();
			lo = 999;
			
			// for every v...
			for (int v = 0; v < numVertices; v++)
			{
				// ...that is adjacent to u
				if (graph[min][v] > 0)
				{
					// if v is in Q and the weight of edge (u, v) is less than v.key
					if (primQ.contains(v) && (graph[min][v] < primKey[v]))
					{
						// set lowest weight edge from min
						if (lo < 999)
						{
							if (graph[min][v] < graph[min][lo]) lo = v;
						}
						
						else
						{
							lo = v;
						}
						
						// set v's parent to u
						primPi[v] = min;
						
						// set v's key to the weight of edge (u, v)
						primKey[v] = primKey[min] + graph[min][v];
						primQ.remove(v);
						primQ.offer(v);

					}
				}
			}
			
			// add lowest weighted edge's weight to mst.weight 
			if (lo < 999) weight += graph[min][lo];
		}
	}
	
	public int weight()
	{
		return weight;
	}
	
	public static void processFile(String filename)
	{
		// let's read a file
		int i = 0;
		
		try
		{
			// read file into fileReader
			FileReader fileReader = new FileReader(filename);
			Scanner in = new Scanner(fileReader);
			in.useDelimiter("\n");
			
			while (in.hasNext())
			{
				int j = 0;
				if (i == 0)
				{
					String s = in.nextLine();
					if (s == null) break;
					
					tempLine = s.toString().trim();
					numVertices = Integer.parseInt(tempLine);
					adjMat = new int[numVertices][numVertices];
					primKey = new int[numVertices];
					primPi = new int[numVertices];
					i++;
				}
				
				else
				{
					String s = in.nextLine();
					if (s == null) break;
					
					tempLine = s.toString();
					String[] tempSplit = tempLine.trim().split("\\s+");
					for (String p : tempSplit)
					{
						tempInt = Integer.parseInt(p);
						if (tempInt != 0)
							adjMat[i - 1][j] = tempInt;
						j++;
					}
					i++;
				}
			}
			in.close();
			fileReader.close();
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
		
		//printMat(adjMat);
	}

	public static void printMat(int[][] graph)
	{
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
			{
				System.out.print(adjMat[i][j]);				
			}
			System.out.println();
		}
	}
	
}
