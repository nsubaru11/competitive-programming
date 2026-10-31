import java.util.*;

public class A1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//二分探索を使用(配列の要素が大きくないため、意味はない)
		while (true) {
			// input
			int n = Integer.parseInt(sc.next()); //要素数
			if (n == 0) {
				sc.close();
				return;
			}
			Integer[] a = new Integer[n]; //所得
			int average = 0;	//所得の平均
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
				average += a[i];
			}

			// solves
			average /= n;
			Arrays.sort(a); //昇順にsort

			/* 二分探索を行う
			 * low = 0; high = n - 1; で初期化する。
			 * 操作 以下を繰り返す。
			 * 1. middle = (low + high) / 2; とする。
			 * 2. a[middle] が平均以下なら low を (middle + 1) にする。
			 * 3. a[middle] が平均より大きいなら high を (middle - 1) にする。
			 *
			 * 操作を繰り返して (low + 1) = high になったとき操作を行うと、、
			 * middle = low となり　1,2 どちらの場合でも　low = high　となるので、
			 * ここで while文 を抜け出し low が求める値である。
			 * ただし a[low] が境目の右側か左側にいるかがわからないため場合分けを行う。
			 */
			int low = 0;
			int high = n - 1;
			while (low < high) {
				int middle = (low + high) / 2;
				if (a[middle] <= average) {
					low = middle + 1;
				} else if (a[middle] > average) {
					high = middle - 1;
				}
			}
			System.out.println((a[low] <= average) ? low + 1: low);
		}
	}

}
