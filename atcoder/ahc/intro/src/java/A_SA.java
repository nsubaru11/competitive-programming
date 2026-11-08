import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class A_SA {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	private static int D;
	private static int[] c;
	private static int[][] s;

	public static void main(String[] args) {
		// input
		D = sc.nextInt();// 総日数
		c = sc.nextInt(26);// タイプ'i'満足度の下がりやすさ
		long sum = Arrays.stream(c).asLongStream().sum() * D * (D + 1) / 2;
		s = sc.nextIntMat(D, 26);// 'd'日目のタイプ'i'の満足度上昇値
		HashMap<Integer, TreeSet<Integer>> last = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			last.put(i, new TreeSet<Integer>());
			last.get(i).add(0);
		}
		// 初期解
		int[] choosed = new int[D];
		long score = -sum;
		for (int d = 0; d < D; d++) {
			choosed[d] = (int) (random() * 26);
//			int t = choosed[d];
			int t = sc.nextInt();
			int last_t = last.get(t).lower(d + 1);
			score += s[d][t] + c[t] * last_t * (d + 1 - last_t) + c[t] * (d + 1);
			last.get(t).add(d + 1);
		}
		for (int i = 0; i < 26; i++) {
			int last_i = last.get(i).floor(D);
			score += c[i] * last_i * (D - last_i);
		}
		System.out.println(score);
		// 山登り法
		int m = 10000000;
		while (m-- > 0) {
			int d = (int) (random() * D);
			int p = choosed[d];
			int q = (int) (random() * 26);

			int last_p = last.get(p).lower(d + 1);
			Integer next_p = last.get(p).higher(d + 1);
			next_p = next_p == null ? D : next_p;

			int last_q = last.get(q).lower(d + 1);
			Integer next_q = last.get(q).higher(d + 1);
			next_q = next_q == null ? D : next_q;

			long temp = score;
			temp -= s[d][p] + c[p] * (d + 1) * (next_p - (d + 1) - 1) + c[q] * last_q * (next_q - (d + 1));
			temp += s[d][q] + c[q] * (d + 1) * (next_q - (d + 1) - 1) + c[p] * last_p * (next_p - (d + 1));
			if (score < temp) {
				score = temp;
				last.get(p).remove(d + 1);
				last.get(q).add(d + 1);
				choosed[d] = q;
			}
		}
		for (int v : choosed) {
			out.println(v + 1);
		}
		out.println(score);
		out.flush();
	}

	// 状態の初期化
	private static void init() {

	}

	// 状態遷移
	private static void modify() {
		/*
		 * D: 総日数
		 * c[]: コンテストごとの満足度の下がりやすさ
		 * s[][]: 日にちとコンテストごとの満足度
		 * choosed[]: 日にちごとの開催したコンテスト
		 * last[]: コンテストごとの最終コンテスト開催日
		 *
		 *
		 * t = choosed[i]
		 * score = {i: 0 <= i < D}Σ s[i][t] + c[t] * (i + 1 - last[t]) + {j: 0 <= j < 26}Σ -c[j] * (i + 1 - last[j])
		 * score = {i: 0 <= i < D}Σ s[i][t] + c[t] * (i + 1 - last[t]) + {j: 0 <= j < 26}Σ -c[j] * (i + 1) + c[j] * last[j]
		 *
		 * 全ての日でコンテストを開催しなかったときの不満度
		 * sum = {i: 0 <= i < 26}Σ c[i] * D * (D + 1) / 2
		 * score = -sum + {i: 0 <= i < D}Σ s[i][t] + c[t] * last[t] * (i - last[t]) + c[t] * (i + 1)
		 *
		 */
	}

	// 状態のスコア計算
	private static int calc_score(int s) {
		return max(1000000 + s, 0);
	}

	// Simulated Annealing - 焼きなまし法
	private static void sa() {

	}
}
