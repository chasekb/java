import java.util.Arrays;

public class ClosestPair
{
	// find the closest pair of double values from array a[N]
	public double best;		// saves the value of the closest distance between two points
	public int p1i;			// saves the index of point 1 of the closest pair
	public int p2i;			// saves the index of point 2 of the closest pair
	private double diff;	// saves the distance between two points
	
	public ClosestPair(double[] a)
	{
		// sort array a
		Arrays.sort(a);
		
		// iterate through array and determine 
		best = a[1] - a[0];
		for (int i = 0; i < (a.length - 1); i++)
		{
			diff = Math.abs(a[i + 1] - a[i]);		// calculate distance between points
			if (diff < best)						// compare diff and best
			{
				best = diff;						// update best
				p1i = i;							// capture point 1
				p2i = i + 1;						// capture point 2
			}
			//StdOut.println("a[" + i + "]  " + a[i] + " a[" + (i + 1) + "]  " + a[i + 1] + " diff "+ diff + " best " + best);
		}
	}
	
	public double distance() { return best; }
	public int point1() { return p1i; }
	public int point2() { return p2i; }
	
	public static void main(String[] args)
	{
		int N = 100;
		double[] b = new double[N];
		for (int i = 0; i < N; i++)
		{
			b[i] = StdRandom.uniform(0.0, 100000.0);
		}
		
		double c = new ClosestPair(b).distance();
		int p1i = new ClosestPair(b).point1();
		int p2i = new ClosestPair(b).point2();

		StdOut.println("Closest pair");
		StdOut.println("Point 1 - " + p1i + " / Point 2 - " + p2i + " Distance between: " + c);
	}
}
