import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt();
		int q = sc.nextInt();
		Queue<Process> qu = new ArrayDeque<>(n);
		for (int i = 0; i < n; i++) {
			Process prcss = new Process(sc.next(), sc.nextInt());
			qu.add(prcss);
		}
		int cnt = 0;
		while (!qu.isEmpty()) {
			Process cur = qu.poll();
			int t = cur.getTime();
			if (t <= q) {
				cnt += t;
				out.print(cur.getName());
				out.print(' ');
				out.print(cnt);
			} else {
				cnt += q;
				cur.setTime(t - q);
				qu.add(cur);
			}
		}
		out.flush();
	}

	private static class Process {
		private String s;
		private int n;

		Process(String s, int n) {
			this.s = s;
			this.n = n;
		}

		public String getName() {
			return s;
		}

		public int getTime() {
			return n;
		}

		public void setTime(int n) {
			this.n = n;
		}

	}
}
