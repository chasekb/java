
/*The largest value of N for which this program takes less than
1 hour to compute the value of F( N)*/

public class MyFibonacci {
	public static long F( int N)
	{
		long curr = 1;
		long prev = 1;
		long temp = 0;
		for (int i=1; i<N; i++)  {
			temp = curr;
			curr += prev;
			prev = temp;
		}
		return curr;
	}
	
	public static void main( String[] args)
	{
		int N = 100;
		long[] a = new long[N];
		for (int i = 0; i < N; i++){
			a[i] = F( i);
//			System.out.println(i + " " + a[i]);
		}
	}

}
