import java.util.*;
import java.io.PrintWriter;


public class C {

	private static HashSet<Long> weight;
	private static int m;
	private static int[] w;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			int n = Integer.parseInt(sc.next());	//薬品
			m = Integer.parseInt(sc.next());	//重り
			if (n == 0 && m == 0) {
				out.flush();
				sc.close();
				return;
			}

			int[] a = new int[n];	//薬品
			w = new int[m];	//重り
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			for (int i = 0; i < m; i++) {
				w[i] = sc.nextInt();
			}

			weight = new HashSet<>();
			weight = calculation(0, 0);
			weight.remove(0L);
			TreeSet<Integer> medicine = new TreeSet<>();
			int count = 0;

			for (int i = 0; i < n; i++) {
				TreeSet<Integer> seki = new TreeSet<>();
				for (long x: weight) {
					seki.add((int) Math.abs(a[i] - x));
				}
				if (!seki.contains(0) && count == 0) {
					medicine = seki;
					count++;
				} else if (!seki.contains(0) && count > 0){
					medicine.retainAll(seki);
				}
			}

			if (count == 0) {
				out.println(0);
			} else if (medicine.size() > 0) {
				out.println(medicine.first());
			} else {
				out.println(-1);
			}
		}
	}

	private static HashSet<Long> calculation(int idx, long sum) {
		if (idx == m) {
			weight.add(sum);
			return weight;
		}
		calculation(idx + 1, sum + w[idx] * 1);
		calculation(idx + 1, sum + w[idx] * -1);
		calculation(idx + 1, sum + w[idx] * 0);
		return weight;
	}
}