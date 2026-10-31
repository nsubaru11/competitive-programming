//import java.util.Arrays;

public class Time {

	public static void main(String[] args) {
		int k = 100;
		int n = 20000000;
		System.out.println("テスト回数: " + k + "\nデータ数: " + n);
		double t, t1, t2;
		t = t1 = t2 = 0;
		for (int l = 0; l < k; l++) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = (int) (Math.random() * n * n);
			}
//			System.out.println(Arrays.toString(arr));

			t = System.nanoTime();
			SegmentTree st = new SegmentTree(n);
			for (int i = 0; i < n; i++) {
				st.update(i, arr[i]);
			}
			t1 += System.nanoTime() - t;
//			System.out.println(st);

			t = System.nanoTime();
//			SegmentTreeArray sta = new SegmentTreeArray(n);
			for (int i = 0; i < n; i++) {
//				sta.update(i, arr[i]);
			}
			t2 += System.nanoTime() - t;
//			System.out.println(sta);
		}
		System.out.println("ダミー値無しセグ木: " + t1 / k / 1000000 + "　ms");
		System.out.println("セグ木: " + t2 / k / 1000000 + "　ms");
	}
}
