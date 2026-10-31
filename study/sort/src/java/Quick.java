import java.util.Arrays;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

public class Quick {

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
			quickSort(arr, 0, n - 1);
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
		System.out.println(" クイックソート:  平均計算時間: " + t / k / d + "ms " + "最小計算時間: " + min / d + "ms " + "最大計算時間: " + max / d + "ms");
		System.out.println(" Javaソート:  平均計算時間: " + t3 / k / d + "ms " + "最小計算時間: " + min3 / d + "ms " + "最大計算時間: " + max3 / d + "ms");
	}

	private static void quickSort(int[] data, int left, int right) {
		if (right <= left) return;
		int l = left;
		int r = right;
		int mid = (left + right) / 2;
		int pivot = data[mid];
		while (l < r) {
			while (l < r && pivot > data[l]) l++;
			while (l < r && pivot <= data[r]) r--;
			if (l >= r) break;
			swap(data, l, r);
			l++;
			r--;
		}
		while (r < right && data[r] <= pivot) r++;
		if (mid > r) {
			swap(data, mid, r);
			r += 1;
		}
		quickSort(data, left, l);
		quickSort(data, r, right);
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
