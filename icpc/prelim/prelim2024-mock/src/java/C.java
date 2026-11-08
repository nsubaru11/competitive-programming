import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}

			long[][] xyz = new long[n][3];
			for (int i = 0; i < n; i++) {
				xyz[i][0] = sc.nextLong();
				xyz[i][1] = sc.nextLong();
				xyz[i][2] = sc.nextLong();
			}
			// "対角線の長さ" から "中点の座標" への写像
			// key -> (xi - xj)² + (yi - yj)² + (zi - zj)²
			// value -> (xi + xj) -> (yi + yj) -> (zi + zj) -> count
			HashMap<Long, HashMap<Long, HashMap<Long, HashMap<Long, Integer>>>> len_xyz = new HashMap<>();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					long xx = (xyz[i][0] - xyz[j][0]) * (xyz[i][0] - xyz[j][0]);
					long yy = (xyz[i][1] - xyz[j][1]) * (xyz[i][1] - xyz[j][1]);
					long zz = (xyz[i][2] - xyz[j][2]) * (xyz[i][2] - xyz[j][2]);
					long len = xx + yy + zz;
					long x = xyz[i][0] + xyz[j][0];
					long y = xyz[i][1] + xyz[j][1];
					long z = xyz[i][2] + xyz[j][2];

					// 同じ長さ対角線が同じものがないとき
					if (len_xyz.get(len) == null) {
						HashMap<Long, HashMap<Long, HashMap<Long, Integer>>> coord = new HashMap<>(); // x座標からyz座標
						HashMap<Long, HashMap<Long, Integer>> yz = new HashMap<>(); // y座標からz座標
						HashMap<Long, Integer> z_count = new HashMap<>(); // z座標からcount
						z_count.put(z, 1);
						yz.put(y, z_count);
						coord.put(x, yz);
						len_xyz.put(len, coord);

						//同じものがあるとき
					} else {
						HashMap<Long, HashMap<Long, HashMap<Long, Integer>>> coord = len_xyz.get(len);

						// 中点のx座標が同じものがないとき
						if (coord.get(x) == null) {
							HashMap<Long, HashMap<Long, Integer>> yz = new HashMap<>();
							HashMap<Long, Integer> z_count = new HashMap<>();
							z_count.put(z, 1);
							yz.put(y, z_count);
							coord.put(x, yz);

							// 同じものがあるとき
						} else {
							HashMap<Long, HashMap<Long, Integer>> yz = coord.get(x);

							// 中点のy座標が同じものがないとき
							if (yz.get(y) == null) {
								HashMap<Long, Integer> z_count = new HashMap<>();
								z_count.put(z, 1);
								yz.put(y, z_count);

								// 同じものがあるとき
							} else {
								HashMap<Long, Integer> z_count = yz.get(y);

								// 中点のz座標が同じものがあるとき
								if (z_count.containsKey(z)) {
									z_count.put(z, z_count.get(z) + 1);

									// 同じものがないとき
								} else {
									z_count.put(z, 1);
								}
							}
						}
					}
				}
			}
			int count = 0;
			for (long len : len_xyz.keySet()) {
				for (long x : len_xyz.get(len).keySet()) {
					for (long y : len_xyz.get(len).get(x).keySet()) {
						for (long z : len_xyz.get(len).get(x).get(y).keySet()) {
							int cnt = len_xyz.get(len).get(x).get(y).get(z);
							count += cnt * (cnt - 1) / 2;
						}
					}
				}
			}

			System.out.println(count);
		}
	}

}
