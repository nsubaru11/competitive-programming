import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

import java.util.Arrays;

public class Shaker {

	public static void main(String[] args) {
		double t, t3;
		t = t3 = 0;
		double min, min3;
		min = min3 = Double.MAX_VALUE;
		double max, max3;
		max = max3 = Double.MIN_VALUE;
		int n = 10000; // データの数
		int k = 1000; // テスト回数
		System.out.println("テスト回数: " + k + "回");
		for (int i = 0; i < k; i++) {
			int[] arr = new int[n];
			setAll(arr, j -> (int) (Math.random() * 1000));
			int[] arr3 = arr.clone();

			double temp = System.nanoTime();
			shakerSort(arr);
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
				System.out.println("quickSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
		}
		int d = 1000000;
		System.out.println(" シェーカーソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static void shakerSort(int[] arr) {
		int left = 0, right = arr.length;// sort済みのidxを覚える
		for (int i = 0; i < arr.length; i++) {

			// 正順
			boolean flag = true; // swapの有無
			int r = 0;
			for (int j = left + 1; j < right; j++) {
				if (arr[j - 1] > arr[j]) {
					r = j;
					swap(arr, j - 1, j);
					flag = false;
				}
			}
			if (flag) break;

			// 逆順
			flag = true;
			right = r;
			int l = 0;
			for (int j = right - 1; j > left; j--) {
				if (arr[j - 1] > arr[j]) {
					l = j;
					swap(arr, j - 1, j);
					flag = false;
				}
			}
			if (flag) break;
			left = l;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
