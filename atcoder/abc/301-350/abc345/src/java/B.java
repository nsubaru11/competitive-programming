import java.util.concurrent.atomic.*;

import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		long x = sc.nextLong();
		System.out.println(ceilDiv(x, 10));
	}
}
