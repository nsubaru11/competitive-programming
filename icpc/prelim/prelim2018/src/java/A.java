import java.io.PrintWriter;
import java.util.*;

import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		int[] a = new int[n];
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += (a[i] = sc.nextInt());
		}
		double sum2 = sum / n;
		out.println(stream(a).filter(k -> k <= sum2).count());
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(n); // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
