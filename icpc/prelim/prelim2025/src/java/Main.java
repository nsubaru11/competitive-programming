import java.io.PrintWriter;
import java.util.*;

import static java.lang.String.valueOf;
import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class Main {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sum += (long) i * j;
			}
		}
		out.println(sum);
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
