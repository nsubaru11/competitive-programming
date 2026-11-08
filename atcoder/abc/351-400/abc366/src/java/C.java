import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int q = sc.nextInt();
		int[] cnt = new int[1000000];
		int ans = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int idx = sc.nextInt();
			switch (idx) {
				case 1:
					int x = sc.nextInt() - 1;
					cnt[x]++;
					if (cnt[x] == 1) ans++;
					break;
				case 2:
					x = sc.nextInt() - 1;
					cnt[x]--;
					if (cnt[x] == 0) ans--;
					break;
				case 3:
					sb.append(ans).append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}
