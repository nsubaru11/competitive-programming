import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class C {

	private static void solve(Scanner sc, PrintWriter out, int m, int n) {
		int max = 7368792;
		boolean[] bamboo = new boolean[max];
		for (int k = m; k < max; k++) {
			if (bamboo[k]) {
				continue;
			} else if (n == 0) {
				out.println(k);
				return;
			}
			n--;
			for (int l = k; l < max; l += k) {
				bamboo[l] = true;
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			PrintWriter out = new PrintWriter(System.out)) {
			while (true) {
				int m = sc.nextInt();
				int n = sc.nextInt();
				if (m == 0 && n == 0) break;
				solve(sc, out, m, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}