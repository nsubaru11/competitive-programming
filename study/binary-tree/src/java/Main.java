import java.util.ArrayList;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		int c = 100;
		int size = 1000000;
		long ans = 0;
		long ans2 = 0;
		long ans3 = 0;
		for (int k = 0; k < c; k++) {
			TreeSet<Integer> ts = new TreeSet<>();
			ArrayList<Integer> al = new ArrayList<>(size);
			for (int i = 0; i < size; i++) {
				al.add((int) (Math.random() * size * 0.8));
			}
			long t = System.nanoTime();
			ts.addAll(al);
			ans += System.nanoTime() - t;

			t = System.nanoTime();
			ts.removeAll(al);
			ans2 += System.nanoTime() - t;

			ts.addAll(al);

			t = System.nanoTime();
			ts.removeIf(a -> a % 2 == 0);
			ans3 += System.nanoTime() - t;
			System.out.println(k);
		}
		System.out.println("テスト回数: " + c + ", データ数: " + size);
		System.out.println("addAll: " + ans / c / 1000000 + "ms");
		System.out.println("removeAll: " + ans2 / c / 1000000 + "ms");
		System.out.println("removeIf: " + ans3 / c / 1000000 + "ms");
//		System.out.println(Arrays.toString(arr));
//		System.out.println();
//		int i = 0;
//		for (int a: arr) {
//			ts.add(a);
//			System.out.print("ts.get(" + i + "): " + ts.get(i));
//			System.out.println(", ts.indexOf(" + a + "): " + ts.indexOf(a));
//		}
//		System.out.println();
//		System.out.println(ts.toList());
//		System.out.println();
//		for (i = 0; i < ts.size(); i++) {
//			System.out.print("ts.get(" + i + "): " + ts.get(i));
//			System.out.print(",  ts.higher(" + i + "): " + ts.higher(i));
//			System.out.print(",  ts.ceil(" + i + "): " + ts.ceiling(i));
//			System.out.print(",  ts.lower(" + i + "): " + ts.lower(i));
//			System.out.println(",  ts.floor(" + i + "): " + ts.floor(i));
//		}
//		i = ts.get(i - 1);
//		System.out.print("ts.higher(" + i + "): " + ts.higher(i));
//		System.out.print(",  ts.ceil(" + i + "): " + ts.ceiling(i));
//		System.out.print(",  ts.lower(" + i + "): " + ts.lower(i));
//		System.out.println(",  ts.floor(" + i + "): " + ts.floor(i));
//		System.out.println("\n" + ts.toList());
//
//		for (int a: arr) {
//			System.out.println("ts.remove(" + a + "): " + ts.remove(a));
//			System.out.println(ts.toList());
//		}
	}
}
