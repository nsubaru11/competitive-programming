import java.io.*;
import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.*;
import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.MIN_VALUE;
import static java.lang.Math.*;

public class A {

	public static void main(String[] args) {
		int n = sc.nextInt();
		int L = sc.nextInt(), R = sc.nextInt();
		StringJoiner sj = new StringJoiner(" ");
		for (int i = 1; i <= n; i++) {
			if (i == L) {
				for (i = R; i >= L; i--) {
					sj.add(Integer.toString(i));
				}
				i = R;
			} else {
				sj.add(Integer.toString(i));
			}
		}
		System.out.println(sj);
	}

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private static long sum,low,high;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		public String next(){return nextSb().toString();}

		public StringBuilder nextSb(){StringBuilder sb=new StringBuilder();int b=read();
			while(b<'!'||'~'<b){b=read();}while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb;}

		public String nextln(){StringBuilder sb=new StringBuilder();int b=read();
			while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}if(b=='\r')read();return sb.toString();}

		public int nextInt(){return(int)nextLong();}

		public long nextLong(){int b=read();while(!Character.isDigit(b)&&b!='-'){b=read();}
			boolean neg=b=='-';if(neg)b=read();long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();}return neg?-n:n;}

		public double nextDouble(){return Double.parseDouble(next());}

		public String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		public String[]nextSortedStrings(int n){String[]s=nextStrings(n);sort(s);return s;}

		public String[][]nextStringMat(int n,int m){String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		public char nextChar(){return next().charAt(0);}

		public char[]nextChars(){return next().toCharArray();}

		public char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=nextChar();return c;}

		public char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		public char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		public char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		public char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		public int[]nextInt(int n){int[]a=new int[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public Integer[]nextInts(int n){Integer[]a=new Integer[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public int[]nextSortedInt(int n){int[]a=nextInt(n);sort(a);return a;}

		public Integer[]nextSortedInts(int n){Integer[]a=nextInts(n);sort(a);return a;}

		public int[]nextIntSum(int n){int[]a=new int[n];resetStats();updateStats(a[0]=nextInt());
			for(int i=1;i<n;i++){updateStats(a[i]=nextInt());a[i]+=a[i-1];}return a;}

		public int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInt(w));return a;}

		public int[][]nextIntMatSum(int h,int w){int[][]a=new int[h+1][w+1];for(int i=1;i<=h;i++)
				for(int j=1;j<=w;j++)a[i][j]=nextInt()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public int[][][]nextInt3D(int x,int y,int z){
			int[][][]a=new int[x][y][z];setAll(a,b->nextIntMat(y,z));return a;}

		public int[][][]nextIntSum3D(int x,int y,int z){int[][][]e=new int[x+1][y+1][z+1];for(int a=1;a<=x;a++)
			for(int b=1;b<=y;b++)for(int c=1;c<=z;c++)e[a][b][c]=nextInt()+e[a-1][b][c]+e[a][b-1][c]
				+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1]-e[a][b-1][c-1]+e[a-1][b-1][c-1];return e;}

		public long[]nextLong(int n){long[]a=new long[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public Long[]nextLongs(int n){Long[]a=new Long[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public long[]nextSortedLong(int n){long[]a=nextLong(n);sort(a);return a;}

		public Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		public long[]nextLongSum(int n){long[]a=new long[n];resetStats();updateStats(a[0]=nextLong());
			for(int i=1;i<n;i++){updateStats(a[i]=nextLong());a[i]+=a[i-1];}return a;}

		public long[][]nextLongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextLong(w));return a;}

		public long[][]nextLongMatSum(int h,int w){long[][]a=new long[h+1][w+1];for(int i=1;i<=h;i++)
			for(int j=1;j<=w;j++)a[i][j]=nextLong()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public long[][][]nextLong3D(int x,int y,int z){
			long[][][]a=new long[x][y][z];setAll(a,b->nextLongMat(y,z));return a;}

		public long[][][]nextLongSum3D(int x,int y,int z){long[][][]e=new long[x+1][y+1][z+1];
			for(int a=1;a<=x;a++)for(int b=1;b<=y;b++)for(int c=1;c<=z;c++)e[a][b][c]=nextLong()+e[a-1][b][c]
				+e[a][b-1][c]+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1]-e[a][b-1][c-1]+e[a-1][b-1][c-1];return e;}

		private<T extends Collection<Integer>>T nextIntCollection(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){int a=nextInt();c.add(a);updateStats(a);}return c;}

		public ArrayList<Integer>nextIntAL(int n){return nextIntCollection(n,ArrayList::new);}

		public LinkedList<Integer>nextIntLL(int n){return nextIntCollection(n,LinkedList::new);}

		public HashSet<Integer>nextIntHS(int n){return nextIntCollection(n,HashSet::new);}

		public LinkedHashSet<Integer>nextIntLHS(int n){return nextIntCollection(n,LinkedHashSet::new);}

		public TreeSet<Integer>nextIntTS(int n){return nextIntCollection(n,TreeSet::new);}

		private<T extends Collection<Long>>T nextLongCollection(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){long a=nextLong();c.add(a);updateStats(a);}return c;}

		public ArrayList<Long>nextLongAL(int n){return nextLongCollection(n,ArrayList::new);}

		public LinkedList<Long>nextLongLL(int n){return nextLongCollection(n,LinkedList::new);}

		public HashSet<Long>nextLongHS(int n){return nextLongCollection(n,HashSet::new);}

		public LinkedHashSet<Long>nextLongLHS(int n){return nextLongCollection(n,LinkedHashSet::new);}

		public TreeSet<Long>nextLongTS(int n){return nextLongCollection(n,TreeSet::new);}

		private<T extends Collection<String>>T nextStringCollection(int n,Supplier<T>s){
			T c=s.get();while(n-->0){c.add(next());}return c;}

		public ArrayList<String>nextStringAL(int n){return nextStringCollection(n,ArrayList::new);}

		public LinkedList<String>nextStringLL(int n){return nextStringCollection(n,LinkedList::new);}

		public HashSet<String>nextStringHS(int n){return nextStringCollection(n,HashSet::new);}

		public LinkedHashSet<String>nextStringLHS(int n){return nextStringCollection(n,LinkedHashSet::new);}

		public TreeSet<String>nextStringTS(int n){return nextStringCollection(n,TreeSet::new);}

		private<T extends Collection<Character>>T nextCharacterCollection(int n,Supplier<T>s){
			T c=s.get();while(n-->0){c.add(nextChar());}return c;}

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

		private<T extends Map<Character,Integer>>T nextCharMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){char a=nextChar();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Character,Integer>nextCharMultisetHM(int n){return nextCharMultiset(n,HashMap::new);}

		public LinkedHashMap<Character,Integer>nextCharacterMultisetLHM(int n){return nextCharMultiset(n,LinkedHashMap::new);}

		public TreeMap<Character,Integer>nextCharMultisetTM(int n){return nextCharMultiset(n,TreeMap::new);}

		public long getLowestNum(){return low;}

		public long getHighestNum(){return high;}

		public long getSum(){return sum;}

		private static void resetStats(){low=MAX_VALUE;high=MIN_VALUE;sum=0;}

		private static void updateStats(long a){sum+=a;low=min(low,a);high=max(high,a);}
	}
}
