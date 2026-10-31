import java.io.*;
import java.util.*;

public class C2 {

	public static void main(String[] args){
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
			for (long[] dd: dist) {
				if (i > 0) sb.append("\n");
				int j = 0;
				for (long d: dd) {
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
		private int v;
		private final long INF = Long.MAX_VALUE;
		private static long[][] dist;

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

	private static final FastScanner sc = new FastScanner();
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;

		public int read(){
			if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		int nextInt(){return(int)nextLong();}

		long nextLong(){
			int b=read(),c=0;while(b<'!'||'~'<b){b=read();}
			boolean neg=false;if(b=='-'){neg=true;b=read();}
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

	}
}