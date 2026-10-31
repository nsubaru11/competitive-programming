import java.io.*;
import java.util.*;

public class B2 {

	public static void main(String[] args){
		int v = sc.nextInt(), e = sc.nextInt(), r = sc.nextInt();

		BellmanFord bf = new BellmanFord(v, e);
		for (int i = 0; i < e; i++) {
			bf.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < v; i++) {
			if (i > 0) sb.append("\n");
			long temp = bf.getShortestPathWeight(r, i);
			if (temp == -bf.INF) {
				System.out.println("NEGATIVE CYCLE");
				return;
			} else if (temp == bf.INF) {
				sb.append("INF");
			} else {
				sb.append(temp);
			}
		}
		System.out.println(sb);
	}

	private static class BellmanFord {
		private int used;
		private final int v;
		private final long[] ans;
		private final ArrayList<Edge> edges;
		private final long INF = Long.MAX_VALUE;

		BellmanFord(int v, int e) {
			this.v = v;
			used = -1;
			ans = new long[v];
			edges = new ArrayList<>(e);
		}

		public void addEdge(int i, int j, int cost) {
			edges.add(new Edge(i, j, cost));
			edges.add(new Edge(j, i, cost));
		}

		/*
		 * 負の重みをもつ頂点の始点iから終点jまでの最小コストを求める
		 * まずansをINFで初期化し、始点を0とする
		 * 
		 * 全ての頂点から全ての辺に対して最短距離を更新する。		 
		 * 更新可能でなくなった時点で終了
		 * 最後の頂点でのループで更新可能な点が残っているならば
		 * 負の閉路があるため-INFを返す。
		 * 
		 * O(V * E)
		 */
		public long getShortestPathWeight(int i, int j) {
			if (used != i) {
				used = i;
				Arrays.fill(ans, INF);
				ans[i] = 0;
				boolean update = false;
				for (int vtx = 0; vtx < v; vtx++) {
					update = false;
					for (Edge e: edges) {
						int from = e.from;
						int to = e.to;
						int cost = e.cost;
						if (ans[from] != INF && ans[to] > ans[from] + cost) {
							ans[to] = ans[from] + cost;
							update = true;
						}
					}
					if (!update) break;
				}
				if (update) return -INF;
			}
			return ans[j];
		}

		private class Edge {
			public int from, to, cost;
			public Edge(int i, int j, int c) {
				this.from = i;
				this.to = j;
				this.cost = c;
			}
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