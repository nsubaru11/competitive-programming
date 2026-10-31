import java.io.PrintWriter;
import java.util.*;

import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		int m = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[m];
		setAll(a, i -> sc.nextInt());
		setAll(b, i -> sc.nextInt());
		TreeSet<Integer> set = new TreeSet<>();
		dfs(0, m, b, set, 0);
		int cnt = 0;
		int target = -1;
		BitSet bs = new BitSet();
		for (int i = 0; i < n; i++) {
			if (!set.contains(a[i])) {
				BitSet temp = new BitSet();
				for (int s : set) {
					temp.set(abs(s - b[i]));
				}
				bs.or(temp);
			}
		}

	}

	private static void dfs(int i, int m, int[] b, TreeSet<Integer> set, int d) {
		if (d >= 0) set.add(d);
		if (i == m) return;
		dfs(i + 1, m, b, set, d - b[i]);
		dfs(i + 1, m, b, set, d + b[i]);
		dfs(i + 1, m, b, set, d);
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
