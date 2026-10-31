import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

public class Merge {

	public static void main(String[] args) {
		double t, t3;
		t = t3 = 0;
		double min, min3;
		min = min3 = Double.MAX_VALUE;
		double max, max3;
		max = max3 = Double.MIN_VALUE;
		int n = 10000; // データの数
		int k = 1000000; // テスト回数
		System.out.println("テスト回数: " + k + "回");
		for (int i = 0; i < k; i++) {
			int[] arr = new int[n];
			setAll(arr, j -> (int) (Math.random() * 1000));
			int[] arr3 = arr.clone();

			double temp = System.nanoTime();
			arr = mergeSort(arr, n);
			double temp2 = System.nanoTime();
			t += temp2 - temp;
			min = min(min, temp2 - temp);
			max = max(max, temp2 - temp);

			temp = System.nanoTime();
			Arrays.sort(arr3);
			temp2 = System.nanoTime();
			t3 += temp2 - temp;
			min3 = min(min3, temp2 - temp);
			max3 = max(max3, temp2 - temp);
			
			if (Arrays.compare(arr, arr3) != 0) {
				System.out.println("mergeSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
		}
		int d = 1000000;
		System.out.println(" マージソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static int[] mergeSort(int[] data, int n) {
		if (n > 1) {
			int l_len = n / 2;
			int r_len = n - n / 2;
			int[] left = mergeSort(Arrays.copyOfRange(data, 0, l_len), l_len);
			int[] right = mergeSort(Arrays.copyOfRange(data, l_len, n), r_len);
			for (int l = 0, r = 0; l + r < n;) {
				while (l + r < n && r >= r_len || (l < l_len && left[l] <= right[r])) {
					data[l + r] = left[l];
					l++;
				}
				while (l + r < n && l >= l_len || (r < r_len && left[l] > right[r])) {
					data[l + r] = right[r];
					r++;
				}
			}
		}
		return data;
	}
}
