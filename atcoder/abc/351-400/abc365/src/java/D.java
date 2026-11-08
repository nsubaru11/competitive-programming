import lib.io.*;

public class D {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		char[] c = sc.nextChars();
		int[][] dp = new int[n]['S' + 1];
		int min = Integer.MIN_VALUE;
		if (c[0] == 'R') {
			dp[0]['P'] = 1;
			dp[0]['R'] = 0;
			dp[0]['S'] = min;
		} else if (c[0] == 'P') {
			dp[0]['S'] = 1;
			dp[0]['P'] = 0;
			dp[0]['R'] = min;
		} else {
			dp[0]['R'] = 1;
			dp[0]['S'] = 0;
			dp[0]['P'] = min;
		}
		for (int i = 1; i < n; i++) {
			if (c[i] == 'R') {
				dp[i]['P'] = Math.max(dp[i - 1]['R'], dp[i - 1]['S']) + 1;
				dp[i]['R'] = Math.max(dp[i - 1]['P'], dp[i - 1]['S']);
				dp[i]['S'] = min;
			} else if (c[i] == 'P') {
				dp[i]['S'] = Math.max(dp[i - 1]['R'], dp[i - 1]['P']) + 1;
				dp[i]['P'] = Math.max(dp[i - 1]['R'], dp[i - 1]['S']);
				dp[i]['R'] = min;
			} else {
				dp[i]['R'] = Math.max(dp[i - 1]['P'], dp[i - 1]['S']) + 1;
				dp[i]['S'] = Math.max(dp[i - 1]['R'], dp[i - 1]['P']);
				dp[i]['P'] = min;
			}
		}
		out.println(Math.max(dp[n - 1]['R'], Math.max(dp[n - 1]['P'], dp[n - 1]['S'])));
		out.flush();
	}
}
