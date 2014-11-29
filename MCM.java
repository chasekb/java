/*
 * Kahlil Bernard Chase
 * CSC 421
 * Final Exam
 * 25 November 2014
 * MCM.java
 * MCM Program (25 points) Implement the matrix-chain multipication program 
 * described in chapter 15 and explained in class on our last night. The input 
 * will be a series of lines, each line containing two integers representing the 
 * dimensions of an array. The output should be the optimal number of multiplications 
 * achieved by an optimal parenthesization of the list of matrices.
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class MCM
{
	private static String tempLine;
	private static int tempInt;
	public static int[] rows;
	public static int[] cols;
	public static int [][] m;
	public static int [][] s;
	public static int numMatrices = 0;
	public static int line = 0;

	public static void main(String[] args)
	{
		String filename = args[0];
		processFile(filename);
		MCM(rows, cols);
		
		System.out.println("m");
		for (int i = 0; i < numMatrices; i++)
		{
			for(int j = 0; j < numMatrices; j++)
			{
				System.out.printf("%7d", m[i][j]);
			}
			
			System.out.println();
		}

		System.out.println("s");
		for (int i = 0; i < numMatrices; i++)
		{
			for(int j = 0; j < numMatrices; j++)
			{
				System.out.print("     " + s[i][j]);
			}
			
			System.out.println();
		}
		
		printOptimalParens(s, 0, numMatrices - 1);
	}
	
	public static void MCM(final int[] pRows, final int[] pCols)
	{
		// n = p.length - 1
		int n = numMatrices;
		
		// let m[1...n, 1...n] and s[1...n-1,2...n] be new tables
		m = new int[n][n];
		s = new int[n][n];

		// for i = 1 to n
		for (int i = 0; i < n; i++)
		{
			// m[i, i] = 0
			m[i][i] = 0;
		}
		
		// for l = 2 to n	// l is the chain length
		for (int l = 2; l <= n; l++)
		{
			// for i = 1 to n - l + 1
			for (int i = 0; i < (n - l + 1); i++)
			{
				// j = i + l - 1
				int j = i + l - 1;
				
				// m[i,j] = infinity
				m[i][j] = 9999999;
				
				// for k = i to j - 1
				for (int k = i; k < j; k++)
				{
					// q = m[i,k] + m[k + 1, j] + p(i-1)p(k)p(j)
					int q = m[i][k] + m[k + 1][j] + pRows[i] * pCols[k] * pCols[j];						

					// if q < m[i,j]
					if (q < m[i][j])
					{
						// m[i,j] = q
						m[i][j] = q;
						
						// s[i,j] = k
						s[i][j] = k;
					}
				}
			}
		}
		// return m and s
	}
	
	public static void printOptimalParens(int[][] s, int i, int j)
	{
		if (i == j)
		{
			System.out.print("A");
		}
		
		else
		{
			System.out.print("(");
			printOptimalParens(s, i, s[i][j]);
			printOptimalParens(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}
	
	public static void processFile(String filename)
	{
		// let's read a file		
		try
		{
			// read file into fileReader
			FileReader numMat = new FileReader(filename);
			Scanner numM = new Scanner(numMat);
			numM.useDelimiter("\n");
			
			// determine number of matrices by counting lines
			while (numM.hasNext())
			{
				String s = numM.nextLine();
				if (s == null) break;
				numMatrices++;
			}
			
			numM.close();
			numMat.close();
			
			// read matrix rows and columns
			rows = new int[numMatrices];
			cols = new int[numMatrices];
			
			FileReader fileReader = new FileReader(filename);
			Scanner in = new Scanner(fileReader);
			while (in.hasNext())
			{
				// check next line is not empty
				String s = in.nextLine();
				if (s == null) break;
				
				tempLine = s.toString().trim();
				String[] tempSplit = tempLine.trim().split("\\s+");
				for (int i = 0; i < tempSplit.length; i++)
				{
					tempInt = Integer.parseInt(tempSplit[i]);
					if (i == 0)	rows[line] = tempInt;
					else		cols[line] = tempInt;
				}
				
				if (line < numMatrices) line++;
			}
			in.close();
			fileReader.close();
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
