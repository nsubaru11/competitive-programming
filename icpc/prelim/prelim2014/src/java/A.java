import java.io.PrintWriter;
import java.util.*;

import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int x) {
		int y = sc.nextInt();
		int s = sc.nextInt();
		int ans = 0;
		for (int i = 1; i < s; i++) {
			for (int j = 1; j < s; j++) {
				int price = i * (x + 100) / 100 + j * (x + 100) / 100;
				if (price == s){
					price = i * (y + 100) / 100 + j * (y + 100) / 100;
					ans = max(ans, price);
				}
			}
		}
		out.println(ans);
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int x;
			while ((x = sc.nextInt()) != 0) {
				solve(x); // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
