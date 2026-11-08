import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int h = Integer.parseInt(sc.next());
			int w = Integer.parseInt(sc.next());
			if (h == 0 && w == 0) {
				sc.close();
				return;
			}

			HashMap<Character, Integer> y = new HashMap<>();    //行
			HashMap<Character, Integer> x = new HashMap<>();    //列
			for (int i = 0; i < h; i++) {
				String r = sc.next();
				for (int j = 0; j < w; j++) {
					y.put(r.charAt(j), i);
					x.put(r.charAt(j), j);
				}
			}

			String s = sc.next();
			int ans = y.get(s.charAt(0)) + x.get(s.charAt(0));
			for (int i = 1; i < s.length(); i++) {
				int dx = Math.abs(x.get(s.charAt(i)) - x.get(s.charAt(i - 1)));
				int dy = Math.abs(y.get(s.charAt(i)) - y.get(s.charAt(i - 1)));
				ans += dx + dy;
			}
			System.out.println(ans + s.length());
		}

	}
}
