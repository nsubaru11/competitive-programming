import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int n = sc.nextInt();
		Integer[] a = nextIntegers(sc, n);
		Collections.reverse(asList(a));
		final StringJoiner sj = new StringJoiner(" ");
		for (final Integer v : a) sj.add(String.valueOf(v));
		out.print(sj);
		out.flush();
	}

	private static Integer[] nextIntegers(final Scanner sc, final int n) {
		final Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}
}
