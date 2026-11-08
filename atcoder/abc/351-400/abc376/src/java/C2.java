import lib.io.*;

public class C2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextSortedInt(n);
		int[] box = sc.nextSortedInt(n - 1);
		int size = 0;
		for (int i = n - 1, j = 1; i >= 0; i--) {
			if (i == 0 && j == 1) {
				size = a[i];
				break;
			}
			if (a[i] > box[i - j]) {
				if (j == 0) {
					System.out.println(-1);
					return;
				} else {
					size = a[i];
					j--;
				}
			}
		}
		System.out.println(size);
	}
}
