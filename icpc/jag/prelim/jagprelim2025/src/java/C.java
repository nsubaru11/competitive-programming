import java.io.*;
import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		int k = sc.nextInt();
		if ((k * sum(n) % n) == 0) {
			out.println("Yes");
		} else {
			out.println("No");
		}
	}

	private static long sum(int n) {
		HashSet<Long> set = new HashSet<>();
		factorsSet(set, n);
		long ans = 0;
		for (long l : set) {
			ans += l;
		}
		return ans;
	}

	private static void factorsSet(HashSet<Long> factors, long n) {
		// 約数の集合を作成します。
		for (long i = 1L; i <= Math.sqrt(n) + 1; i++) {
			if (n % i == 0) {
				factors.add(i);
				factors.add(n / i);
			}
		}
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
