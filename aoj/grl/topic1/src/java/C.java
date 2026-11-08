import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int v = sc.nextInt();
		int e = sc.nextInt();

		Warshallfroyd wf = new Warshallfroyd(v);
		for (int i = 0; i < e; i++) {
			int s = sc.nextInt();
			int t = sc.nextInt();
			int d = sc.nextInt();
			wf.addEdge(s, t, d);
		}

		long[][] dist = wf.dist();
		if (dist == null) {
			System.out.println("NEGATIVE CYCLE");
		} else {
			StringJoiner sj = new StringJoiner("\n");
			for (long[] dd : dist) {
				StringJoiner sj2 = new StringJoiner(" ");
				for (long d : dd) {
					if (d == wf.INF) sj2.add("INF");
					else sj2.add(Long.toString(d));
				}
				sj.add(sj2.toString());
			}
			System.out.println(sj);
		}
	}

	private static class Warshallfroyd {
		private static long[][] dist;
		private final long INF = Long.MAX_VALUE;
		private int v;

		Warshallfroyd(int v) {
			this.v = v;
			dist = new long[v][v];
			for (int i = 0; i < v; i++) {
				Arrays.fill(dist[i], INF);
				dist[i][i] = 0;
			}
		}

		private void addEdge(int from, int to, int cost) {
			dist[from][to] = cost;
		}

		private long[][] dist() {
			for (int via = 0; via < v; via++) {
				for (int from = 0; from < v; from++) {
					if (dist[from][via] == INF) continue;
					for (int to = 0; to < v; to++) {
						if (dist[via][to] == INF) continue;
						dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
						if (from == to && dist[from][to] != 0) return null;
					}
				}
			}
			return dist;
		}

	}
}
