import java.util.*;

public class B3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int h = Integer.parseInt(sc.next());
			int w = Integer.parseInt(sc.next());
			if (h == 0 && w == 0) {
				sc.close();
				return;
			}

			int[] x = new int[128];
			int[] y = new int[128];
			for (int i = 0; i < h; i++) {
				String r = sc.next();
				for (int j = 0; j < w; j++) {
					x[r.charAt(j)] = j;
					y[r.charAt(j)] = i;
				}
			}

			String s = sc.next();
			int ans = x[s.charAt(0)] + y[s.charAt(0)];
			for (int i = 1; i < s.length(); i++) {
				int dx = Math.abs(x[s.charAt(i)] - x[s.charAt(i - 1)]);
				int dy = Math.abs(y[s.charAt(i)] - y[s.charAt(i - 1)]);
				ans += dx + dy;
			}
			System.out.println(ans + s.length());
		}
	}

}
