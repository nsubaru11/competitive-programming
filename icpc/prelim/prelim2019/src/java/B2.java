import java.awt.Point;
import java.util.*;

public class B2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int h = Integer.parseInt(sc.next());
			int w = Integer.parseInt(sc.next());
			if (h == 0 && w == 0) {
				sc.close();
				return;
			}
			
			HashMap<Character, Point> coord = new HashMap<>();
			for (int i = 0; i < h; i++) {
				String r = sc.next();
				for (int j = 0; j < w; j++) {
					coord.put(r.charAt(j), new Point(j, i));
				}
			}

			String s = sc.next();
			int ans = coord.get(s.charAt(0)).x + coord.get(s.charAt(0)).y;
			for (int i = 1; i < s.length(); i++) {
				int dx = Math.abs(coord.get(s.charAt(i)).x - coord.get(s.charAt(i - 1)).x);
				int dy = Math.abs(coord.get(s.charAt(i)).y - coord.get(s.charAt(i - 1)).y);
				ans +=  dx + dy;
			}
			System.out.println(ans + s.length());
		}

	}
}
