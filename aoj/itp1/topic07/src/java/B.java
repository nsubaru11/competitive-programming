import java.util.*;

import static java.lang.Math.*;

public class B {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		while (true) {
			int n = sc.nextInt(), x = sc.nextInt();
			if (n == 0 && x == 0) break;
			int cnt = 0;
			//a + b + c = x; a < b < c;
			for (int a = 1; a <= min(n, (x - 3) / 3); a++) {
				int b = a + 1;//bの最小値
				int c = min(x - a - b, n);//cの最小値
				int add = min((x - a + 1) / 2 - b, c - (x - a) / 2);
				cnt += (add < 0) ? 0 : add;
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb);
	}
}
