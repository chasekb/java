public class MyCircular {
	
	public static boolean isShift( String s, String t) {
		return (s.length() == t.length()) && ((s+s).indexOf(t) != -1);
	}
	
	public static void main( String[] args) {
		String s = new String("ACTGACG");
		String t = new String("TGACGAC");
		boolean a = isShift(s, t);
		System.out.println(a);
	}

}