import java.util.Arrays;

public class MyPoint2DClient {

	public static void main(String[] args) {
		// takes int N from command line
		int N = StdIn.readInt();
		
		// generate N random points in the unit square
		Point2D[] points = new Point2D[N];
		
		for(int i = 0; i < N; i++) {
			points[i] = new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
		}
		
		// compute distance separating closest pair
		double closest = 0.0;
		int cindex = 0;
		
		for(int j = 0; j < (N - 1); j++) {
			if(Math.abs(points[j].compareTo(points[j+1])) < closest) {
				cindex = j;
			}
		}
		
		StdOut.println(points[cindex].distanceTo(points[cindex + 1]));
	}
	
}
