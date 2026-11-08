import java.io.*;
import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		final int[] a = nextSortedInts(sc, 3);
		out.print(a[0] + " " + a[1] + " " + a[2]);
		out.flush();
	}

	private static int[] nextInts(final Scanner sc, final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}

	private static int[] nextSortedInts(final Scanner sc, final int n) {
		final int[] a = nextInts(sc, n);
		Arrays.sort(a);
		return a;
	}
}
