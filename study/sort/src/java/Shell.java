import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

public class Shell {

	public static void main(String[] args) {
		double t, t3;
		t = t3 = 0;
		double min, min3;
		min = min3 = Double.MAX_VALUE;
		double max, max3;
		max = max3 = Double.MIN_VALUE;
		int n = 10000; // データの数
		int k = 10000; // テスト回数
		System.out.println("テスト回数: " + k + "回");
		for (int i = 0; i < k; i++) {
			int[] arr = new int[n];
			setAll(arr, j -> (int) (Math.random() * 1000));
			int[] arr3 = arr.clone();

			double temp = System.nanoTime();
			shellSort(arr);
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
				System.out.println("shellSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
		}
		int d = 1000000;
		System.out.println(" シェルソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static void shellSort(int[] arr) {
		int n = arr.length;
		int h = 1;
		int k = 1;
		while (n > h) {
			h = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
			k++;
		}
		k--;
		while (k >= 0) {
			if (k == 0) {
				h = 1;
			} else {
				h = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
			}
			for (int i = 0; i < h; i++) {
				for (int j = i + h - 1; j < n; j += h) {
					int idx = j;
					while (idx >= h && arr[idx - h] > arr[idx]) {
						swap(arr, idx - h, idx);
						idx -= h;
					}
				}
			}
			k--;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
