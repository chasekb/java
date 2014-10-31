import java.util.Arrays;
import java.util.Scanner;

public class LCS 
{
	/*Implement a solution to the longest common subsequence problem.
	 * The input contains two lines, each containing a string of characters.
	 * The output should be the length of the LCS of the two strings and a common 
	 * subsequence of that length.*/
	
	/*Kahlil Bernard Chase
	 * CSC421
	 * 30 October 2014
	 */
	
	public static void LCS(String X, String Y)
	{
		int m = X.length();
		int n = Y.length();
		
		int[][] c = new int[m + 1][n + 1];
		
		for (int i = 0; i <= m; i++)
			c[i][0] = 0;
		
		for (int j = 0; j <= n; j++)
			c[0][j] = 0;
		
		for (int i = 1; i <= m; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				if (X.charAt(i - 1) == Y.charAt(j - 1))
					c[i][j] = c[i - 1][j - 1] + 1;
				
				else if (c[i - 1][j] >= c[i][j - 1])
					c[i][j] = c[i - 1][j];
				
				else
					c[i][j] = c[i][j - 1];
			}
		}
		
		System.out.println("LCS length: " + c[m][n]);
		LCSPrint(c, X, X.length(), Y.length());
	}
	
	private static void LCSPrint(int[][] b, String x, int i, int j)
	{
		if (i == 0 || j == 0)
			return;
		
		if (b[i][j] == b[i - 1][j - 1] + 1)
		{
			LCSPrint(b, x, i - 1, j - 1);
			System.out.print(x.charAt(i - 1));
		}
		
		else if (b[i][j] == b[i - 1][j])
			LCSPrint(b, x, i - 1, j);
		
		else
			LCSPrint(b, x, i, j - 1);			
	}
	
	public static void main(String[] args)
	{
	    Scanner firstSeq = new Scanner(System.in);
	    System.out.print("Enter first sequence: ");
	    String sequence1 = firstSeq.nextLine();

	    Scanner secondSeq = new Scanner(System.in);
	    System.out.print("Enter second sequence: ");
	    String sequence2 = secondSeq.nextLine();
	    
	    firstSeq.close();
	    secondSeq.close();

		LCS(sequence1, sequence2);		
	}
}
