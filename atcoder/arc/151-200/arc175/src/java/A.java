import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = sc.nextInt();
		}

		long mod = 998244353L;
		String S = sc.next();
		HashSet<Integer> memory_p = new HashSet<>();
		long left = 1;
		long right = 1;

		for (int i = 0; i < N; i++) {
			memory_p.add(p[i]);
			if (left != 0) {
				if (p[i] == N) {
					if (memory_p.contains(1) && S.charAt(N - 1) == '?') {
						left = left * 2L % mod;
					} else if (!memory_p.contains(1) && S.charAt(N - 1) == 'R') {
						left = 0;
					}
				} else if (memory_p.contains(p[i] + 1) && S.charAt(p[i] - 1) == '?') {
					left = left * 2L % mod;
				} else if (!memory_p.contains(p[i] + 1) && S.charAt(p[i] - 1) == 'R') {
					left = 0;
				}
			}

			if (right != 0) {
				if (p[i] == 1) {
					if (memory_p.contains(N) && S.charAt(0) == '?') {
						right = right * 2L % mod;
					} else if (!memory_p.contains(N) && S.charAt(0) == 'L') {
						right = 0;
					}
				} else if (memory_p.contains(p[i] - 1) && S.charAt(p[i] - 1) == '?') {
					right = right * 2L % mod;
				} else if (!memory_p.contains(p[i] - 1) && S.charAt(p[i] - 1) == 'L') {
					right = 0;
				}
			}

			if (left == right && right == 0) break;
		}

		System.out.println((left + right) % mod);
	}
}
