import java.util.Arrays;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

public class Selection {

	public static void main(String[] args) {
		double t, t2, t3;
		t = t2 = t3 = 0;
		double min, min2, min3;
		min = min2 = min3 = Double.MAX_VALUE;
		double max, max2, max3;
		max = max2 = max3 = Double.MIN_VALUE;
		int n = 10000; // データの数
		int k = 10000; // テスト回数
		System.out.println("テスト回数: " + k + "回");
		for (int i = 0; i < k; i++) {
			int[] arr = new int[n];
			setAll(arr, j -> (int) (Math.random() * 1000));
			int[] arr2 = arr.clone();
			int[] arr3 = arr.clone();

			double temp = System.nanoTime();
			selectionSort(arr);
			double temp2 = System.nanoTime();
			t += temp2 - temp;
			min = min(min, temp2 - temp);
			max = max(max, temp2 - temp);

			temp = System.nanoTime();
			doubleSelectionSort(arr2);
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
				System.out.println("selectionSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
			if (Arrays.compare(arr2, arr3) != 0) {
				System.out.println("doubleSelectionSort" + Arrays.toString(arr) + "\n" + Arrays.toString(arr3));
			}
		}
		int d = 1000000;
		System.out.println(" 選択ソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" 双方向選択ソート:  平均計算時間: " + t2 / k / d + "ms " + "最小計算時間: " + min2 / d + "ms " + "最大計算時間: " + max2 / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIdx = j;
				}
			}
			swap(arr, i, minIdx);
		}
	}

	private static void doubleSelectionSort(int[] arr) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			int min = arr[left];
			int minIdx = left;
			int max = arr[left];
			int maxIdx = left;
			for (int i = left + 1; i < right; i++) {
				if (arr[i] < min) {
					min = arr[i];
					minIdx = i;
				}
				if (arr[i] > max) {
					max = arr[i];
					maxIdx = i;
				}
			}
			if (left == maxIdx && right - 1 == minIdx) {
				swap(arr, left, right - 1);
			} else if (left == maxIdx) {
				swap(arr, right - 1, maxIdx);
				swap(arr, left, minIdx);
			} else {
				swap(arr, left, minIdx);
				swap(arr, right - 1, maxIdx);
			}
			left++;
			right--;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
