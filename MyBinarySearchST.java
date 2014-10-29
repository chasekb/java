
public class MyBinarySearchST<Key extends Comparable<Key>, Value>
{
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	public MyBinarySearchST(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	public int size()
	{ return N; }
	
	public boolean isEmpty()
	{ return N == 0; }
	
	public Value get(Key key)
	{
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)	return vals[i];
		else										return null;
	}
	
	public int rank(Key key)
	{
		int lo = 0, hi = N - 1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if		(cmp < 0) hi = mid - 1;
			else if	(cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}
	
	public void put(Key key, Value val)
	{
		int i = rank(key);
		if (i == N)
		{ vals[i + 1] = val; keys[i + 1] = key; }
		
		else if (i < N && keys[i].compareTo(key) == 0)
		{ vals[i] = val; return; }
		for (int j = N; j > i; j--)
		{ keys[j] = keys[j - 1]; vals[j] = vals[j - 1]; }
		keys[i] = key; vals[i] = val;
		N++;
	}
	
	public void delete(Key key)
	{
        if (isEmpty()) return;
        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) 
        { return; }

        for (int j = i; j < N-1; j++) 
        {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;
	}
	
    public Key floor(Key key)
    {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }
	
    public static void main(String[] args)
    { 
    	int capacity = 16;
        MyBinarySearchST st = new MyBinarySearchST(capacity);
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 2);
        st.put("R", 3);
        st.put("C", 4);
        st.put("H", 5);
        
        // delete test
        st.delete("E");
        st.delete("H");
        
        // floor test
        StdOut.println(st.floor("D"));
        StdOut.println(st.floor("Q"));
        
        // ordered insertion test
        st.put("Z", 6);
    }
}

