import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int[] d = nextInts(sc, 6);
		Dice dice = new Dice(d[0], d[5], d[1], d[4], d[3], d[2]);
		int n = sc.nextInt();
		int[] x = new int[n * 2];
		for (int i = 0; i < n; i++) {
			x[i * 2] = sc.nextInt();
			x[i * 2 + 1] = sc.nextInt();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int j = 0;
			while (dice.u() != x[i * 2]) {
				rotateNorth(dice);
				j++;
				if (j == 4) {
					dice.rotateFR();
				}
			}
			while (dice.f() != x[i * 2 + 1]) {
				dice.rotateFR();
			}
			sb.append(dice.r()).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotateNorth(final Dice dice) {
		dice.rotateUF();
		dice.rotateUF();
		dice.rotateUF();
	}

	private static int[] nextInts(final Scanner sc, final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}

	/**
	 * 上、下、前、後、左、右の順で面を保持する可変な六面体ダイスです。
	 */
	private static final class Dice {
		private static final int U = 0;
		private static final int D = 1;
		private static final int F = 2;
		private static final int B = 3;
		private static final int L = 4;
		private static final int R = 5;
		private final int[] dice;

		public Dice(final int[] dice) {
			this.dice = dice;
		}

		/**
		 * 面を {@code 1, 6, 3, 4, 2, 5} で初期化します。
		 */
		public Dice() {
			this(1, 6, 3, 4, 2, 5);
		}

		/**
		 * 各面の値を指定して作成します。
		 */
		public Dice(final int u, final int d, final int f, final int b, final int l, int r) {
			this(new int[6]);
			dice[U] = u;
			dice[D] = d;
			dice[F] = f;
			dice[B] = b;
			dice[L] = l;
			dice[R] = r;
		}

		/**
		 * 6面の値を保持する配列を返します。
		 */
		public int[] dice() {
			return dice;
		}

		/**
		 * 上面の値を返します。
		 */
		public int u() {
			return dice[U];
		}

		/**
		 * 下面の値を返します。
		 */
		public int d() {
			return dice[D];
		}

		/**
		 * 前面の値を返します。
		 */
		public int f() {
			return dice[F];
		}

		/**
		 * 後面の値を返します。
		 */
		public int b() {
			return dice[B];
		}

		/**
		 * 左面の値を返します。
		 */
		public int l() {
			return dice[L];
		}

		/**
		 * 右面の値を返します。
		 */
		public int r() {
			return dice[R];
		}

		/**
		 * 上面を右面へ移す向きに回転します。
		 */
		public void rotateUR() {
			int right = dice[R];
			dice[R] = dice[U];
			dice[U] = dice[L];
			dice[L] = dice[D];
			dice[D] = right;
		}

		/**
		 * 前面を右面へ移す向きに回転します。
		 */
		public void rotateFR() {
			int right = dice[R];
			dice[R] = dice[F];
			dice[F] = dice[L];
			dice[L] = dice[B];
			dice[B] = right;
		}

		/**
		 * 上面を前面へ移す向きに回転します。
		 */
		public void rotateUF() {
			int front = dice[F];
			dice[F] = dice[U];
			dice[U] = dice[B];
			dice[B] = dice[D];
			dice[D] = front;
		}
	}
}
