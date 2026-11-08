import lib.io.*;

public class No6 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] arr = sc.nextSortedInt(n);
		int Alice = 0, Bob = 0;
		if (n % 2 == 0) {
			n /= 2;
			for (int i = 0; i < n; i++) {
				Alice += arr[2 * i + 1];
				Bob += arr[2 * i];
			}
		} else {
			Alice += arr[n - 1];
			n = (n - 1) / 2;
			for (int i = 0; i < n; i++) {
				Alice += arr[2 * i];
				Bob += arr[2 * i + 1];
			}
		}
		System.out.println(Alice - Bob);
	}
}
