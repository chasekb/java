/*
 * Kahlil Bernard Chase
 * CSC 421
 * Final Exam
 * 25 November 2014
 * SP.java
 * 
 * SP Program (25 points) In Problem Set 5 you wrote a shortest paths program 
 * implementing either Bellman-Ford or Dijkstra's. For this exercise, implement 
 * the other one. 
 * 
 * Program SP (30 points) Implement one of the shortest path algorithms 
 * presented in class (Bellman-Ford or Dijstra's) in the language of your choice.
 */

import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.IOException;


public class SP 
{
	private static int numVertices = 0;
	private static String tempLine;
	private static int tempInt;
	public static int[][] adjMat;
	public static int[] d;
	public static int[] pi;

	public static void main(String[] args)
	{
		String filename = "data/SPadjacencyMatrix.txt";
		processFile(filename);
		boolean choice = true;
		SP(choice, adjMat, 0);
		
		for (int i = 0; i < d.length; i++)
		{
			System.out.println("d[" + i + "]: " + d[i] + " pi[" + i + "]: " + pi[i]);
		}
		
		processFile(filename);
		choice = false;
		SP(choice, adjMat, 0);
		for (int i = 0; i < d.length; i++)
		{
			System.out.println("d[" + i + "]: " + d[i] + " pi[" + i + "]: " + pi[i]);
		}
	}

	public static void SP(boolean choice, final int[][] graph, final int s)
	{
		// if choice = true then bellman-ford
		if (choice)
		{
			// shortest path using bellman-ford
			System.out.println("bellman-ford");
			bellFo(graph, s);
		}
		
		// if choice = false then djikstrka
		else
		{
			// shortest path using djikstra
			System.out.println("\ndjikstra");
			djikstra(graph, s);
		}
	}
	
	public static void djikstra(int[][] graph, int s)
	{
		// initialize-single-source(graph, s)
		iss(graph, s);
		
		// make a set s = null
		int[] S = new int[numVertices];
		for (int each: S)
		{
			S[each] = Integer.MAX_VALUE;
		}
		
		// enter vertices graph.V into a min pq keyed by d value
		PriorityQueue<Integer> djikQ = new PriorityQueue<Integer>(numVertices, 
				new Comparator<Integer>()
				{
					public int compare(Integer u, Integer v)
					{
						return Integer.compare(d[u],  d[v]);
					}
				}	);
		
		for (int i = 0; i < numVertices; i++)
		{
			djikQ.offer(i);
		}

		// while the pq is not empty
		while (!djikQ.isEmpty())
		{
			// u = extract-min(pq)
			int u = (int) djikQ.poll();
			
			// s = s union u
			S[u] = 0;
			
			// for every vertex v in graph.adj[u]
			for (int v = 0; v < numVertices; v++)
			{
				if (graph[u][v] != 0)
				{					
					// relax(u, v, w)
					relax(u, v, graph);
				}
			}			
		}
	}
	
	public static boolean bellFo(int[][] graph, int s)
	{
		// initialize-single-source(graph, s)
		iss(graph, s);
		
		// make a pass over every edge
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
			{
				// for each edge (u, v) in graph
				if (graph[i][j] != 0)
				{
					// relax edge (u, v, w)
					relax(i, j, graph);
				}
			}
		}
		
		// for each edge (u, v) in graph
		for (int i = 0; i < numVertices; i++)
		{
			for (int j = 0; j < numVertices; j++)
			{
				// check for negative weight cycle
				// if v.d > u.d + w(u, v) return false
				if (d[j] > d[i] + graph[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	private static void iss(int[][] graph, int s)
	{
		// initialize single source
		// create attribute v.d
		d = new int[numVertices];
		
		// create attribute v.pi
		pi = new int[numVertices];
		
		// for every vertex v in graph
		for (int v = 0; v < numVertices; v++)
		{
			// set v.d to infinity
			d[v] = 999;
			
			// set v.pi to nil
			pi[v] = -1;
		}
		
		// set root.d to 0
		d[s] = 0;
	}
	
	private static void relax(int u, int v, int[][] w)
	{
		// relax edge (u, v)
		// if v.d > u.d + w(u, v)
		if (d[v] > d[u] + w[u][v])
		{
			// set v.d = u.d + w(u, v)
			d[v] = d[u] + w[u][v];
			
			// set v.pi = u
			pi[v] = u;
		}
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
