public class MyBST<Key extends Comparable<Key>, Value>
{
	private Node root;
	
	private class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
	
		public Node(Key key, Value val, int N)
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public int size()
	{ return size(root); }
	
	private int size(Node x)
	{
		if (x == null) 	return 0;
		else			return x.N;
	}
	
	public Value get(Key key)
	{ return get(root, key); }
	
	private Value get(Node x, Key key)
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if 		(cmp < 0) 	return get(x.left, key);
		else if	(cmp > 0) 	return get(x.right, key);
		else 				return x.val;
	}
	
	public void put(Key key, Value val)
	{ root = put(root, key, val); }
	
	private Node put(Node x, Key key, Value val)
	{
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) x.left = put(x.left, key, val);
		else if	(cmp > 0) x.right = put(x.right, key, val);
		else				x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Key min()
	{
		Node x = root;
		while (x.left != null) x = x.left;
		return x.key;
	}
	
	public Key max()
	{
		Node x = root;
		while (x.right != null) x = x.right;
		return x.key;
	}
	
	public Key floor(Key key)
	{
		Node x = root;
		Node floor = root;
		
		while (x != null)
		{
			int cmp = key.compareTo(x.key);
			if		(cmp == 0)	return x.key;
			else if	(cmp < 0)
			{
				if (x.left != null)
				{
					x = x.left;
					if (x.key.compareTo(floor.key) > 0 && x.key.compareTo(key) < 0) floor = x;
				}
				else			return floor.key;
			}
			else if	(cmp > 0)
			{
				x = x.right;
				if (x.key.compareTo(floor.key) > 0 && x.key.compareTo(key) < 0) floor = x;
			}
		}
		return floor.key;		
	}
	
	public Key ceiling(Key key)
	{
		Node x = root;
		Node ceiling = root;
		
		while (x != null)
		{
			int cmp = key.compareTo(x.key);
			if		(cmp == 0)	return x.key;
			else if (cmp > 0)
			{
				if (x.right != null)
				{
					x = x.right;
					if (x.key.compareTo(key) > 0) ceiling = x;
				}
				else			return ceiling.key;
			}	
			else if (cmp < 0)
			{
				x = x.left;
				if (x.key.compareTo(key) > 0) ceiling = x;
			}
		}
		return ceiling.key;		
	}
	
	public int rank(Key key)
	{
		Node x = root;
		int sum = root.left.N + 1;
		
		while (x != null)
		{
			int cmp = key.compareTo(x.key);

			if 		(cmp == 0)	return sum;
			else if	(cmp < 0)
			{
				if (x.left != null)
				{
					sum--;
					x = x.left;
					if (x.right != null) sum -= x.right.N;
				}
			}
			else if	(cmp > 0)
			{
				if (x.right != null)
				{
					x = x.right;
					sum += x.left.N + 1;
				}
			}
		}
		return sum;
	}
	
	public Key select(int k)
	{
		Node x = root;
		Node cur = x;

		while (x != null)
		{
			if 		(rank(x.key) == k) return x.key;
			else if (rank(x.key) > k)
			{
				if (x.left != null)
				{ cur = x; x = x.left; }
			}
			else if (rank(x.key) < k)
			{
				if (x.right != null)
				{ cur = x; x = x.right; }
			}
		}
		return cur.key;
	}

	public static void main (String args[])
	{
		MyBST m = new MyBST();
		m.put("E", 1);
		m.put("A", 2);
		m.put("S", 3);
		m.put("Y", 4);
		m.put("Q", 5);
		m.put("U", 6);
		m.put("E", 7);
		m.put("S", 8);
		m.put("T", 9);
		m.put("I", 10);
		m.put("O", 11);
		m.put("N", 12);
		
		StdOut.println("min     : " + m.min());
		StdOut.println("max     : " + m.max());
		StdOut.println("floor   K: " + m.floor("K"));
		StdOut.println("ceiling P: " + m.ceiling("P"));
		StdOut.println("rank    T: " + m.rank("T"));
		StdOut.println("select  9: " + m.select(9));
	}
}