import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		sc.next();
		int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
		System.out.println(x < z && z < y ? "Yes" : x > z && z > y ? "Yes" : "No");
	}
}
