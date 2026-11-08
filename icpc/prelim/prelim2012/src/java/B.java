import java.io.*;
import java.util.*;
//import static java.lang.System.out;

public class B {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static boolean solve() {
		int a = sc.nextInt();
		int l = sc.nextInt();
		if (a == 0 && l == 0) return false;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(a, 0);
		for (int i = 1; i <= 20; i++) {
			int min, max;
			char[] c = Master.toCharArray(a, l);
			Master.sort(c);
			min = Master.toInt(c);
			Master.reverse(c);
			max = Master.toInt(c);
			a = max - min;
			if (map.containsKey(a)) {
				int j = map.get(a);
				out.println(j + " " + a + " " + (i - j));
				return true;
			}
			map.put(a, i);
		}

		return true;
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int n;
			while (solve()) {
				; // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

	private static final class Master {

		/* -------------- pow -------------- */

		/**
		 * long値で返します。
		 */
		public static long pow(long n, int k) {
			long res = 1;
			while (k > 0) {
				if ((k & 1) == 1) res *= n;
				n *= n;
				k >>= 1;
			}
			return res;
		}

		/* -------------- modPow -------------- */

		/**
		 * n ^ k % mod
		 */
		public static long modPow(long n, long k, long mod) {
			long res = 1;
			n %= mod;
			while (k > 0) {
				if ((k & 1) == 1) res = res * n % mod;
				n = n * n % mod;
				k >>= 1;
			}
			return res;
		}

		/* -------------- gcd -------------- */

		/**
		 * 最大公約数
		 */
		public static int gcd(int a, int b) {
			return b == 0 ? a : gcd(b, a % b);
		}

		/**
		 * 最大公約数
		 */
		public static long gcd(long a, long b) {
			return b == 0 ? a : gcd(b, a % b);
		}

		/**
		 * 最大公約数
		 */
		public static int gcd(int[] arr) {
			int ans = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) {
				ans = gcd(ans, arr[i]);
			}
			return ans;
		}

		/**
		 * 最大公約数
		 */
		public static long gcd(long[] arr) {
			long ans = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) {
				ans = gcd(ans, arr[i]);
			}
			return ans;
		}

		/* -------------- lcm -------------- */

		/**
		 * 最小公倍数
		 */
		public static long lcm(int a, int b) {
			return (long) a / gcd(a, b) * b;
		}

		/**
		 * 最小公倍数
		 */
		public static long lcm(long a, long b) {
			return a / gcd(a, b) * b;
		}

		/**
		 * 最小公倍数
		 */
		public static long lcm(int[] arr) {
			long ans = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) {
				ans = lcm(ans, arr[i]);
			}
			return ans;
		}

		/**
		 * 最小公倍数
		 */
		public static long lcm(long[] arr) {
			long ans = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) {
				ans = lcm(ans, arr[i]);
			}
			return ans;
		}

		/* -------------- sum, min, max -------------- */

		/**
		 * int配列の合計
		 */
		public static long sum(int[] arr) {
			long sum = 0;
			for (int x : arr) sum += x;
			return sum;
		}

		/**
		 * long配列の合計
		 */
		public static long sum(long[] arr) {
			long sum = 0;
			for (long x : arr) sum += x;
			return sum;
		}

		/**
		 * int配列の最小値
		 */
		public static int min(int[] arr) {
			int min = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) min = Math.min(min, arr[i]);
			return min;
		}

		/**
		 * long配列の最小値
		 */
		public static long min(long[] arr) {
			long min = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) min = Math.min(min, arr[i]);
			return min;
		}

		/**
		 * int配列の最大値
		 */
		public static int max(int[] arr) {
			int max = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) max = Math.max(max, arr[i]);
			return max;
		}

		/**
		 * long配列の最大値
		 */
		public static long max(long[] arr) {
			long max = arr[0];
			int len = arr.length;
			for (int i = 1; i < len; i++) max = Math.max(max, arr[i]);
			return max;
		}

		/* -------------- toInt -------------- */

		/**
		 * char[] -> int 変換
		 */
		public static int toInt(char[] arr) {
			int d = 0;
			for (char c : arr)
				d = d * 10 + c - '0';
			return d;
		}

		/**
		 * String -> int 変換
		 */
		public static int toInt(String s) {
			int d = 0;
			for (int i = 0; i < s.length(); i++)
				d = d * 10 + s.charAt(i) - '0';
			return d;
		}

		/* -------------- toLong -------------- */

		/**
		 * char[] -> long 変換
		 */
		public static long toLong(char[] arr) {
			long d = 0;
			for (char c : arr)
				d = d * 10 + c - '0';
			return d;
		}

		/**
		 * String -> long 変換
		 */
		public static long toLong(String s) {
			long d = 0;
			for (int i = 0; i < s.length(); i++)
				d = d * 10 + s.charAt(i) - '0';
			return d;
		}

		/* -------------- toCharArray -------------- */

		/**
		 * int -> char[] 変換
		 */
		public static char[] toCharArray(int n) {
			return String.valueOf(n).toCharArray();
		}

		/**
		 * int -> char[] 変換（桁数指定）
		 * 指定した桁数に満たない場合0で埋める
		 */
		public static char[] toCharArray(int n, int l) {
			char[] c = new char[l];
			for (int i = l - 1; i >= 0; i--) {
				c[i] = (char) (n % 10 + '0');
				n /= 10;
			}
			return c;
		}

		/**
		 * long -> char[] 変換
		 */
		public static char[] toCharArray(long n) {
			return String.valueOf(n).toCharArray();
		}

		/**
		 * long -> char[] 変換（桁数指定）
		 * 指定した桁数に満たない場合0で埋める
		 */
		public static char[] toCharArray(long n, int l) {
			char[] c = new char[l];
			for (int i = l - 1; i >= 0; i--) {
				c[i] = (char) (n % 10 + '0');
				n /= 10;
			}
			return c;
		}

		/**
		 * int[] -> char[] 変換
		 */
		public static char[] toCharArray(int[] arr) {
			int len = arr.length;
			char[] res = new char[len];
			for (int i = 0; i < len; i++) {
				res[i] = (char) (arr[i] + '0');
			}
			return res;
		}

		/* -------------- toString -------------- */

		/**
		 * int -> String 変換
		 */
		public static String toString(int n) {
			return Integer.toString(n);
		}

		/**
		 * long -> String 変換
		 */
		public static String toString(long n) {
			return Long.toString(n);
		}

		/**
		 * char[] -> String 変換
		 */
		public static String toString(char[] c) {
			return String.valueOf(c);
		}

		/**
		 * int[] -> String 変換
		 */
		public static String toString(int[] arr) {
			StringBuilder sb = new StringBuilder(arr.length * 10);
			for (int v : arr) sb.append(v);
			return sb.toString();
		}

		/* -------------- toIntArray -------------- */

		/**
		 * char[] -> int[] 変換
		 */
		public static int[] toIntArray(char[] arr) {
			int len = arr.length;
			int[] res = new int[len];
			for (int i = 0; i < len; i++) {
				res[i] = arr[i] - '0';
			}
			return res;
		}

		/**
		 * String -> int[] 変換
		 */
		public static int[] toIntArray(String s) {
			int len = s.length();
			int[] res = new int[len];
			for (int i = 0; i < len; i++) {
				res[i] = s.charAt(i) - '0';
			}
			return res;
		}

		/* -------------- reverse -------------- */

		/**
		 * int値の反転
		 */
		public static int reverse(int n) {
			int d = 0;
			while (n > 0) {
				d = d * 10 + n % 10;
				n /= 10;
			}
			return d;
		}

		/**
		 * long値の反転
		 */
		public static long reverse(long n) {
			long d = 0;
			while (n > 0) {
				d = d * 10 + n % 10;
				n /= 10;
			}
			return d;
		}

		/**
		 * 配列の反転（int[]）
		 */
		public static void reverse(int[] arr) {
			int len = arr.length;
			for (int i = 0; i < len / 2; i++) {
				int tmp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = tmp;
			}
		}

		/**
		 * 配列の反転（char[]）
		 */
		public static void reverse(char[] arr) {
			int len = arr.length;
			for (int i = 0; i < len / 2; i++) {
				char tmp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = tmp;
			}
		}

		/**
		 * 配列の反転（long[]）
		 */
		public static void reverse(long[] arr) {
			int len = arr.length;
			for (int i = 0; i < len / 2; i++) {
				long tmp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = tmp;
			}
		}

		/* -------------- sort -------------- */

		/**
		 * int値 各桁昇順でソート
		 */
		public static int sort(int n) {
			char[] c = toCharArray(n);
			Arrays.sort(c);
			return toInt(c);
		}

		/**
		 * long値 各桁昇順でソート
		 */
		public static long sort(long n) {
			char[] c = toCharArray(n);
			Arrays.sort(c);
			return toLong(c);
		}

		/**
		 * char[] ソート
		 */
		public static void sort(char[] arr) {
			Arrays.sort(arr);
		}

		/**
		 * int[] ソート
		 */
		public static void sort(int[] arr) {
			Arrays.sort(arr);
		}

		/**
		 * long[] ソート
		 */
		public static void sort(long[] arr) {
			Arrays.sort(arr);
		}

		/**
		 * String ソート（戻り値あり）
		 */
		public static String sort(String str) {
			char[] arr = str.toCharArray();
			sort(arr);
			return toString(arr);
		}

		/* -------------- descendingSort -------------- */

		/**
		 * int値 各桁降順でソート
		 */
		public static int descendingSort(int n) {
			char[] c = toCharArray(n);
			Arrays.sort(c);
			reverse(c);
			return toInt(c);
		}

		/**
		 * long値 各桁降順でソート
		 */
		public static long descendingSort(long n) {
			char[] c = toCharArray(n);
			Arrays.sort(c);
			reverse(c);
			return toLong(c);
		}

		/**
		 * char[] 降順でソート
		 */
		public static void descendingSort(char[] arr) {
			Arrays.sort(arr);
			reverse(arr);
		}

		/**
		 * int[] 降順ソート
		 */
		public static void descendingSort(int[] arr) {
			Arrays.sort(arr);
			reverse(arr);
		}

		/**
		 * long[] 降順ソート
		 */
		public static void descendingSort(long[] arr) {
			Arrays.sort(arr);
			reverse(arr);
		}

		/**
		 * String 降順ソート（戻り値あり）
		 */
		public static String descendingSort(String str) {
			char[] arr = str.toCharArray();
			descendingSort(arr);
			return toString(arr);
		}

	}

}
