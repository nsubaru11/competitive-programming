import java.util.*;

public class C2 {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int v = sc.nextInt(), e = sc.nextInt();
		Warshallfroyd wf = new Warshallfroyd(v);
		for (int i = 0; i < e; i++) {
			wf.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		long[][] dist = wf.dist();
		if (dist == null) {
			System.out.println("NEGATIVE CYCLE");
		} else {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (long[] dd : dist) {
				if (i > 0) sb.append("\n");
				int j = 0;
				for (long d : dd) {
					if (j > 0) sb.append(" ");
					if (d == wf.INF) sb.append("INF");
					else sb.append(d);
					j = 1;
				}
				i = 1;
			}
			System.out.println(sb);
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
