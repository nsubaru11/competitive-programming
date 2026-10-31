import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

import java.util.Arrays;

public class Comb {

	public static void main(String[] args) {
		double t, t2, t3;
		t = t2 = t3 = 0;
		double min, min2, min3;
		min = min2 = min3 = Double.MAX_VALUE;
		double max, max2, max3;
		max = max2 = max3 = Double.MIN_VALUE;
		int n = 10000; // データの数
		int k = 1000000; // テスト回数
		System.out.println("テスト回数: " + k + "回");
		for (int i = 0; i < k; i++) {
			int[] arr = new int[n];
			setAll(arr, j -> (int) (Math.random() * 1000));
			int[] arr2 = arr.clone();
			int[] arr3 = arr.clone();

			double temp = System.nanoTime();
			combSort0(arr);
			double temp2 = System.nanoTime();
			t += temp2 - temp;
			min = min(min, temp2 - temp);
			max = max(max, temp2 - temp);

			temp = System.nanoTime();
			combSort(arr2);
			temp2 = System.nanoTime();
			t2 += temp2 - temp;
			min2 = min(min2, temp2 - temp);
			max2 = max(max2, temp2 - temp);

			temp = System.nanoTime();
			Arrays.sort(arr3);
			temp2 = System.nanoTime();
			t3 += temp2 - temp;
			min3 = min(min3, temp2 - temp);
			max3 = max(max3, temp2 - temp);
			
			if (Arrays.compare(arr, arr3) != 0) {
				System.out.println("combSort0" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
			if (Arrays.compare(arr2, arr3) != 0) {
				System.out.println("combSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
		}
		int d = 1000000;
		System.out.println(" コムソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" 改良コムソート:  平均計算時間: " + t2 / k / d + "ms " + "最小計算時間: " + min2 / d + "ms " + "最大計算時間: " + max2 / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static void combSort0(int[] arr) {
		int n = arr.length;
		int h = n;
		boolean flag = true;
		while (h != 1 || !flag) {
			h = h > 1 ? h * 10 / 13 : 1;
			flag = true;
			for (int i = 0; i + h < n; i++) {
				if (arr[i] > arr[i + h]) {
					swap(arr, i, i + h);
					flag = false;
				}
			}
		}
	}

	private static void combSort(int[] arr) {
		int n = arr.length;
		int h = n;
		boolean flag = true;
		while (h != 1 || !flag) {
			h = h > 1 ? h * 10 / 13 : 1;
			if (h == 9 || h == 10) h = 11; // なんか速くなる
			flag = true;
			for (int i = 0; i + h < n; i++) {
				if (arr[i] > arr[i + h]) {
					swap(arr, i, i + h);
					flag = false;
				}
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
