import lib.io.*;

public class No1 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt();
		System.out.println((a * b % 2 == 0) ? "Even" : "Odd");
	}
}
