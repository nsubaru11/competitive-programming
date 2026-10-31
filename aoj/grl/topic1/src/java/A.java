import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.util.function.Supplier;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class A {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int v = sc.nextInt();
		int e = sc.nextInt();
		int r = sc.nextInt();

		Dijkstra di = new Dijkstra(v);
		for (int i = 0; i < e; i++) {
			int s = sc.nextInt();
			int t = sc.nextInt();
			int d = sc.nextInt();
			di.addEdge(s, t, d);
		}

		for (int i = 0; i < v; i++) {
			long temp = di.getShortestPathWeight(r, i);
			out.println(temp == di.INF ? "INF" : temp);
		}
		out.flush();
	}

	@SuppressWarnings("unused")
	private static class Dijkstra {
		private int used; // 前回の始点を記憶
		private final int v;
		private final int[] path; // 始点iから各頂点への経路
		private final long[] ans; // 始点 i から各頂点 j への重みの配列
		private final List<List<Edge>> edges; // 辺i、jの情報をListの i番目に保存
		private final long INF = Long.MAX_VALUE;

		// 頂点の数を引数にし、各点を始点とする辺情報を受け取るリストを作成
		public Dijkstra(int v) {
			this.v = v;
			used = -1;
			path = new int[v];
			ans = new long[v];
			edges = new ArrayList<>(v);
			while (v-- > 0) {
				edges.add(new ArrayList<>());
			}
		}

		// リストの i番目に始点が iの辺情報を加える
		public void addEdge(int i, int j, int cost) {
			edges.get(i).add(new Edge(i, j, cost));
		}

		/*
		 * 始点iから終点jまでの最小コストを求める まずansをINFで初期化し、始点を0とする これを未確定頂点pqに加えて、開始
		 * 
		 * while文の中では未確定頂点pqの中から最小コストのものを確定させ、 その頂点から隣接する頂点をpqに加える このときにコストの更新が可能なら更新する
		 * 
		 * pathは-1で初期化し、 確定した頂点番号iを隣接する頂点番目jに覚えさせる。 -> path[j] = i;
		 */
		public long getShortestPathWeight(int i, int j) {
			if (used != i) {
				used = i;

				// 各頂点への最短経路の重みを保持する配列の初期化。
				Arrays.fill(ans, INF);
				ans[i] = 0;

				// 各頂点への最短経路を保持する配列の初期化。
				Arrays.fill(path, -1);

				// 未確定頂点を保持する優先度付きキューの初期化。
				PriorityQueue<Vertex> pq = new PriorityQueue<>();
				pq.add(new Vertex(i, ans[i]));

				while (!pq.isEmpty()) {
					Vertex node = pq.poll();
					int from = node.vtx;
					if (ans[from] < node.cost) continue;
					for (Edge vtx : edges.get(from)) {
						long cost = vtx.cost;
						int to = vtx.to;
						if (ans[from] != INF && ans[to] > ans[from] + cost) {
							ans[to] = ans[from] + cost;
							pq.add(new Vertex(to, ans[to]));
							path[to] = from;
						}
					}
				}
			}
			return ans[j];
		}

		/*
		 * 始点1からjまでの経路を返します。 経路が存在ない場合はnullを返します。
		 */
		public List<Integer> getShortestPath(int i, int j) {
			if (used != i) getShortestPathWeight(i, j);
			if (ans[j] == INF) return null;
			List<Integer> shortestPath = new ArrayList<>();
			shortestPath.add(j);
			while (path[j] >= 0) {
				j = path[j];
				shortestPath.add(j);
			}
			Collections.reverse(shortestPath);
			return shortestPath;
		}

		// 辺情報
		private class Edge {
			public int from, to, cost;

			public Edge(int i, int j, int c) {
				from = i;
				to = j;
				cost = c;
			}
		}

		// 確定した頂点と一度でも隣接した頂点情報
		private class Vertex implements Comparable<Vertex> {
			public int vtx;
			public long cost;

			public Vertex(int v, long w) {
				vtx = v;
				this.cost = w;
			}

			// コストの大きさによって並び替え
			@Override
			public int compareTo(Vertex o) {
				return Long.compare(cost, o.cost);
			}
		}

	}

	@SuppressWarnings("unused")
	private static final class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buf=new byte[1<<17];
		private static int pos=0,cnt=0;private long sum,low,high;

		private byte read(){if(pos==cnt){try{cnt=reader.read(buf,pos=0,1<<17);}
			catch(IOException ignored){}}if(cnt<0)return 0;return buf[pos++];}

		public char nextChar(){byte b=read();while(b<'!'||'~'<b)b=read();return(char)b;}

		public String next(){return nextSb().toString();}

		public StringBuilder nextSb(){StringBuilder sb=new StringBuilder();int b=read();
			while(b<'!'||'~'<b)b=read();while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb;}

		public String nextLine(){StringBuilder sb=new StringBuilder();int b=read();
			while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}if(b=='\r')read();return sb.toString();}

		public int nextInt(){int b=nextChar();boolean neg=b=='-';if(neg)b=read();
			int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();}return neg?-n:n;}

		public long nextLong(){int b=nextChar();boolean neg=b=='-';if(neg)b=read();
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();}return neg?-n:n;}

		public int nextIntRadix(int radix){return Integer.parseInt(next(),radix);}

		public long nextLongRadix(int radix){return Long.parseLong(next(),radix);}

		public BigInteger nextBigInteger(){return new BigInteger(next());}

		public BigInteger nextBigInteger(int radix){return new BigInteger(next(),radix);}

		public double nextDouble(){return Double.parseDouble(next());}

		public String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		public String[]nextSortedStrings(int n){String[]s=nextStrings(n);sort(s);return s;}

		public String[][]nextStringMat(int n,int m){String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		public char nextCharAt(int i){return next().charAt(i);}

		public char[]nextChars(){return next().toCharArray();}

		public char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=nextChar();return c;}

		public char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		public char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		public char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		public char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		public int[]nextInt(int n){int[]a=new int[n];resetStats();for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public Integer[]nextInts(int n){Integer[]a=new Integer[n];resetStats();for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public int[]nextSortedInt(int n){int[]a=nextInt(n);sort(a);return a;}

		public Integer[]nextSortedInts(int n){Integer[]a=nextInts(n);sort(a);return a;}

		public int[]nextIntSum(int n){int[]a=new int[n];resetStats();updateStats(a[0]=nextInt());
			for(int i=1;i<n;i++){updateStats(a[i]=nextInt());a[i]+=a[i-1];}return a;}

		public int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInt(w));return a;}

		public int[][]nextIntMatSum(int h,int w){int[][]a=new int[h+1][w+1];
			for(int i=1;i<=h;i++)for(int j=1;j<=w;j++)a[i][j]=nextInt()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public int[][][]nextInt3D(int x,int y,int z){int[][][]a=new int[x][y][z];setAll(a,b->nextIntMat(y,z));return a;}

		public int[][][]nextIntSum3D(int x,int y,int z){int[][][]e=new int[x+1][y+1][z+1];
			for(int a=1;a<=x;a++)for(int b=1;b<=y;b++)for(int c=1;c<=z;c++)e[a][b][c]=nextInt()+e[a-1][b][c]
				+e[a][b-1][c]+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1] - e[a][b - 1][c - 1] + e[a - 1][b - 1][c - 1];return e;}

		public long[]nextLong(int n){long[]a=new long[n];resetStats();for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public Long[]nextLongs(int n){Long[] a=new Long[n];resetStats();for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public long[]nextSortedLong(int n){long[]a=nextLong(n);sort(a);return a;}

		public Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		public long[]nextLongSum(int n){long[]a=new long[n];low=high=sum=a[0]=nextLong();
			for(int i=1;i<n;i++){updateStats(a[i]=nextLong());a[i]+=a[i-1];}return a;}

		public long[][]nextLongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextLong(w));return a;}

		public long[][]nextLongMatSum(int h,int w){long[][]a=new long[h+1][w+1];for(int i=1;i<=h;i++)
				for(int j=1;j<=w;j++)a[i][j]=nextLong()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public long[][][]nextLong3D(int x,int y,int z){long[][][]a=new long[x][y][z];setAll(a,b->nextLongMat(y,z));return a;}

		public long[][][]nextLongSum3D(int x,int y,int z){long[][][]e=new long[x+1][y+1][z+1];for(int a=1;a<=x;a++)for(int b=1;b<=y;b++)
			for(int c=1;c<=z;c++)e[a][b][c]=nextLong()+e[a-1][b][c]+e[a][b-1][c]+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1]-e[a][b-1][c-1]+e[a-1][b-1][c-1];return e;}

		private<T extends Collection<Integer>>T nextIntCollection(int n,Supplier<T>s){T c=s.get();resetStats();while(n-->0){int a=nextInt();c.add(a);updateStats(a);}return c;}

		public ArrayList<Integer>nextIntAL(int n){return nextIntCollection(n,ArrayList::new);}

		public LinkedList<Integer>nextIntLL(int n){return nextIntCollection(n,LinkedList::new);}

		public HashSet<Integer>nextIntHS(int n){return nextIntCollection(n,HashSet::new);}

		public LinkedHashSet<Integer>nextIntLHS(int n){return nextIntCollection(n,LinkedHashSet::new);}

		public TreeSet<Integer>nextIntTS(int n){return nextIntCollection(n,TreeSet::new);}

		private<T extends Collection<Long>>T nextLongCollection(int n,Supplier<T>s){T c=s.get();resetStats();while(n-->0){long a=nextLong();c.add(a);updateStats(a);}return c;}

		public ArrayList<Long>nextLongAL(int n){return nextLongCollection(n,ArrayList::new);}

		public LinkedList<Long>nextLongLL(int n){return nextLongCollection(n,LinkedList::new);}

		public HashSet<Long>nextLongHS(int n){return nextLongCollection(n,HashSet::new);}

		public LinkedHashSet<Long>nextLongLHS(int n){return nextLongCollection(n,LinkedHashSet::new);}

		public TreeSet<Long>nextLongTS(int n){return nextLongCollection(n,TreeSet::new);}

		private<T extends Collection<String>>T nextStringCollection(int n,Supplier<T>s){T c=s.get();while(n-->0){c.add(next());}return c;}

		public ArrayList<String>nextStringAL(int n){return nextStringCollection(n,ArrayList::new);}

		public LinkedList<String>nextStringLL(int n){return nextStringCollection(n,LinkedList::new);}

		public HashSet<String>nextStringHS(int n){return nextStringCollection(n,HashSet::new);}

		public LinkedHashSet<String>nextStringLHS(int n){return nextStringCollection(n,LinkedHashSet::new);}

		public TreeSet<String>nextStringTS(int n){return nextStringCollection(n,TreeSet::new);}

		private<T extends Collection<Character>>T nextCharacterCollection(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){char t=nextChar();c.add(t);updateStats(t);}return c;}

		public ArrayList<Character>nextCharacterAL(int n){return nextCharacterCollection(n,ArrayList::new);}

		public LinkedList<Character>nextCharacterLL(int n){return nextCharacterCollection(n,LinkedList::new);}

		public HashSet<Character>nextCharacterHS(int n){return nextCharacterCollection(n,HashSet::new);}

		public LinkedHashSet<Character>nextCharacterLHS(int n){return nextCharacterCollection(n,LinkedHashSet::new);}

		public TreeSet<Character>nextCharacterTS(int n){return nextCharacterCollection(n,TreeSet::new);}

		private<T extends Map<Integer,Integer>>T nextIntMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){int a=nextInt();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Integer,Integer>nextIntMultisetHM(int n){return nextIntMultiset(n,HashMap::new);}

		public LinkedHashMap<Integer,Integer>nextIntMultisetLHM(int n){return nextIntMultiset(n,LinkedHashMap::new);}

		public TreeMap<Integer,Integer>nextIntMultisetTM(int n){return nextIntMultiset(n,TreeMap::new);}

		private<T extends Map<Long,Integer>>T nextLongMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){long a=nextLong();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Long,Integer>nextLongMultisetHM(int n){return nextLongMultiset(n,HashMap::new);}

		public LinkedHashMap<Long,Integer>nextLongMultisetLHM(int n){return nextLongMultiset(n,LinkedHashMap::new);}

		public TreeMap<Long,Integer>nextLongMultisetTM(int n){return nextLongMultiset(n,TreeMap::new);}

		private<T extends Map<String,Integer>>T nextStringMultiset(int n,Supplier<T>s){
			T c=s.get();while(n-->0){String a=next();c.put(a,c.getOrDefault(a,0)+1);}return c;}

		public HashMap<String,Integer>nextStringMultisetHM(int n){return nextStringMultiset(n,HashMap::new);}

		public LinkedHashMap<String,Integer>nextStringMultisetLHM(int n){return nextStringMultiset(n,LinkedHashMap::new);}

		public TreeMap<String,Integer>nextStringMultisetTM(int n){return nextStringMultiset(n,TreeMap::new);}

		private<T extends Map<Character,Integer>>T nextCharMultiset(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){char a=nextChar();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Character,Integer>nextCharMultisetHM(int n){return nextCharMultiset(n,HashMap::new);}

		public LinkedHashMap<Character,Integer>nextCharacterMultisetLHM(int n){return nextCharMultiset(n,LinkedHashMap::new);}

		public TreeMap<Character,Integer>nextCharMultisetTM(int n){return nextCharMultiset(n,TreeMap::new);}

		public long getLowestNum(){return low;}

		public long getHighestNum(){return high;}

		public long getSum(){return sum;}

		private void resetStats(){low=Long.MAX_VALUE;high=Long.MIN_VALUE;sum=0;}

		private void updateStats(long a){sum+=a;low=min(low,a);high=max(high,a);}
	}

	@SuppressWarnings("unused")
	private static final class FastPrinter {
		private StringBuilder sb;

		public FastPrinter(){sb=new StringBuilder();}

		public FastPrinter println(){print("\n");return this;}

		public FastPrinter println(Object o){print(o).println();return this;}

		public FastPrinter println(String s){print(s).println();return this;}

		public FastPrinter println(boolean f){print(f).println();return this;}

		public FastPrinter println(char c){print(c).println();return this;}

		public FastPrinter println(int a){print(a).println();return this;}

		public FastPrinter println(long a){print(a).println();return this;}

		public FastPrinter println(double a){print(a).println();return this;}

		public FastPrinter println(double a,int n){print(a,n).println();return this;}

		public FastPrinter println(int a,int b){println(a).println(b);return this;}

		public FastPrinter println(int a,long b){println(a).println(b);return this;}

		public FastPrinter println(long a,int b){println(a).println(b);return this;}

		public FastPrinter println(long a,long b){println(a).println(b);return this;}

		public FastPrinter println(Object...o){for(Object x:o)println(x);return this;}

		public FastPrinter println(String[]s){for(String x:s)println(x);return this;}

		public FastPrinter println(char[]c){for(char x:c)println(x);return this;}

		public FastPrinter println(int[]a){for(int x:a)println(x);return this;}

		public FastPrinter println(long[]a){for(long x:a)println(x);return this;}

		public FastPrinter println(Object[][]o){for(Object[]x:o)print(x).println();return this;}

		public FastPrinter println(char[][]c){for(char[]x:c)print(x).println();return this;}

		public FastPrinter println(int[][]a){for(int[]x:a)print(x).println();return this;}

		public FastPrinter println(long[][]a){for(long[]x:a)print(x).println();return this;}

		public FastPrinter printChars(char[][]c){for(char[]x:c)printChars(x).println();return this;}

		public FastPrinter print(Object o){sb.append(o);return this;}

		public FastPrinter print(String s){sb.append(s);return this;}

		public FastPrinter print(boolean f){sb.append(f);return this;}

		public FastPrinter print(char c){sb.append(c);return this;}

		public FastPrinter print(int a){sb.append(a);return this;}

		public FastPrinter print(long a){sb.append(a);return this;}

		public FastPrinter print(double a){sb.append(a);return this;}

		public FastPrinter print(double a,int n){if(n==0){print(round(a));return this;}
			if(a<0){print("-");a=-a;}a+=pow(10,-n)/2;print((long)a).print(".");a-=(long)a;
			while(n-->0){a*=10;print((int)a);a-=(int)a;}return this;}

		public FastPrinter print(int a,int b){print(a).print(" ").print(b);return this;}

		public FastPrinter print(int a,long b){print(a).print(" ").print(b);return this;}

		public FastPrinter print(long a,int b){print(a).print(" ").print(b);return this;}

		public FastPrinter print(long a,long b){print(a).print(" ").print(b);return this;}

		public FastPrinter print(Object...o){print(o[0]);for(int i=1;i<o.length;i++){print(" ").print(o[i]);}return this;}

		public FastPrinter print(String[]s){print(s[0]);for(int i=1;i<s.length;i++){print(" ").print(s[i]);}return this;}

		public FastPrinter print(char[]c){print(c[0]);for(int i=1;i<c.length;i++){print(" ").print(c[i]);}return this;}

		public FastPrinter print(int[]a){print(a[0]);for(int i=1;i<a.length;i++){print(" ").print(a[i]);}return this;}

		public FastPrinter print(long[]a){print(a[0]);for(int i=1;i<a.length;i++){print(" ").print(a[i]);}return this;}

		public FastPrinter printChars(char[]c){sb.append(c);return this;}

		public FastPrinter printf(String format,Object...args){print(String.format(format,args));return this;}

		public FastPrinter printf(Locale locale,String format,Object...args){print(String.format(locale,format,args));return this;}

		public FastPrinter reverse(){sb.reverse();return this;}

		public FastPrinter flush(){System.out.print(sb);return this;}

		public FastPrinter clear(){sb.setLength(0);return this;}
	}
}