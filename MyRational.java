public class MyRational
{

	private long num;
	private long den; 
	
	// create and initialize a new Rational object
	public MyRational(long numerator, long denominator)
	{
		if (denominator == 0)
		{
			throw new RuntimeException("Denominator is zero");
		}
		long g = gcd(numerator,denominator);
		num = numerator/g;
		den = denominator/g;
	}
	
	//return string representation of 'this'
	public String toString()
	{
		if (den == 1) { return num + ""; }
		else		{ return num + "/" + den; }
	}
	
	//return {-1, 0, 1} if a < b, a = b, or a > b
	public long compareTo(MyRational b)
	{
		MyRational a = this;
		long lhs = a.num * b.den;
		long rhs = a.den * b.num;
		if (lhs < rhs) return -1;
		if (lhs > rhs) return +1;
		return 0;
	}
	
	//is this = that?
	public boolean equals(MyRational that)
	{
		if (that == null) return false;
		if (that.getClass() != this.getClass()) return false;
		MyRational b = (MyRational) that;
		return compareTo(b) == 0;
	}
		
	//return sum of 'this' number and b
	public MyRational plus(MyRational b)
	{
		long numerator = (this.num * b.den) + (this.den * b.num);
		long denominator = this.den * b.den;
		return new MyRational(numerator, denominator);
	}
	
	//return difference of 'this' number minus b
	public MyRational minus(MyRational b)
	{
		long numerator = (this.num * b.den) - (this.den * b.num);
		long denominator = this.den * b.den;
		return new MyRational(numerator, denominator);
	}
	
	//return the product of 'this' number * b
	public MyRational times(MyRational b)
	{
		return new MyRational(this.num * b.num, this.den * b.den);
	}
	
	//return the quotient of 'this' number divide by b
	public MyRational divides(MyRational b)
	{
		return new MyRational(den, num);
	}
	
	/*** Helper Function ***/
	private static long gcd(long m, long n)
	{
		if (0 == n) return m;
		else return gcd(n, m % n);
	}
	
	/**** Test client ****/
	public static void main(String[] args)
	{
		MyRational x,y,z;
		
		// 1/2 + 1/3 = 5/6
		x = new MyRational(1, 2);
		y = new MyRational(1, 3);
		z = x.plus(y);
		System.out.println(z);
		
		// 2/3 - 1/3 = 1/3
		x = new MyRational(2, 3);
		y = new MyRational(1, 3);
		z = x.minus(y);
		System.out.println(z);
		
		// 4/17 * 7/3 = 28/51
		x = new MyRational(4, 17);
		y = new MyRational(7, 3);
		z = x.times(y);
		System.out.println(z);
		
		// 6/12 = 1/2
		x = new MyRational(6, 12);
		System.out.println(x);
	}
}
