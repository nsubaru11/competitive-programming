import java.io.*;
import java.util.*;
import static java.util.Arrays.*;
import static java.lang.Math.*;

public class D {

	private static int cnt, size;
	private static ArrayList<Integer> G;

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextInts(n);
		shellSort(a, n);
		StringBuilder sb = new StringBuilder();
		sb.append(size).append("\n");
		for (int i = size - 1; i >= 0; i--) {
			sb.append(G.get(i));
			if (i > 0) sb.append(" ");
		}
		sb.append("\n");
		sb.append(cnt).append("\n");
		for (int x: a) {
			sb.append(x).append("\n");
		}
		System.out.print(sb);
	}

	private static void insertionSort(int[] a, int n, int g) {
		for (int i = g; i < n; i++) {
			int v = a[i];
			int j = i - g;
			while (j >= 0 && a[j] > v) {
				a[j + g] = a[j];
				j -= g;
				cnt++;
			}
			if (j + g < n) a[j + g] = v;
		}
	}

	private static void shellSort(int[] a, int n) {
		cnt = 0;
		G = new ArrayList<Integer>();
		int h = 1;
		while(true) {
			if(h > n) {
				break;
			}
			G.add(h);
			h = 3 * h + 1;
		}
		size = G.size();
		for(int i = size - 1; i >= 0; i--) {
			insertionSort(a, n, G.get(i));
		}
	}

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private long sum,low,high;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){StringBuilder sb=new StringBuilder();int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		String nextLine(){StringBuilder sb=new StringBuilder();int b=read();while(b!=0&&b!='\r'&&b!='\n'){
			sb.appendCodePoint(b);b=read();}if(b=='\r')read();return sb.toString();}

		int nextInt(){return(int)nextLong();}

		long nextLong(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		double nextDouble(){return Double.parseDouble(next());}

		String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		String[][]nextStringMat(int n,int m){String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		char nextChar(){return next().charAt(0);}

		char[]nextChars(){return next().toCharArray();}

		char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=nextChar();return c;}

		char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		int[]nextInts(int n){low=0x7fffffff;high=0x80000000;sum=0;int[]a=new int[n];
			for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		Integer[]nextIntegers(int n){low=0x7fffffff;high=0x80000000;sum=0;Integer[]a=new Integer[n];
			for (int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		int[]nextSortedInts(int n){int[]a=nextInts(n);sort(a);return a;}

		Integer[]nextSortedIntegers(int n){Integer[]a=nextIntegers(n);sort(a);return a;}

		int[]nextIntSum(int n){int[]a=new int[n];a[0]=nextInt();low=high=a[0];for(int i=1;i<n;i++)
			{a[i]=nextInt();low=min(low,a[i]);high=max(high,a[i]);a[i]+=a[i-1];}return a;}

		int[]nextSortedIntSum(int n){int[]a=nextSortedInts(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInts(w));return a;}

		int[][]nextIntMatSum(int h,int w){int[][]a=new int[h+1][w+1];for(int i=1;i<=h;i++)for(int j=1;j<=w;j++)
			a[i][j]=nextInt()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		int[][][]nextInts3D(int x,int y,int z){
			int[][][]a=new int[x][y][z];setAll(a,b->nextIntMat(y,z));return a;}

		int[][][]nextInts3DSum(int x,int y,int z){int[][][]e=new int[x+1][y+1][z+1];for(int a=1;a<=x;a++){
			for(int b=1;b<=y;b++){for(int c=1;c<=z;c++){e[a][b][c]=nextInt()+e[a-1][b][c]+e[a][b-1][c]+e[a][b][c-1];
					e[a][b][c]-=e[a-1][b-1][c]+e[a-1][b][c-1]+e[a][b-1][c-1]-e[a-1][b-1][c-1];}}}return e;}

		long[]nextlongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;long[]a=new long[n];
			for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		Long[]nextLongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;Long[]a=new Long[n];
			for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		long[]nextSortedlongs(int n){long[]a=nextlongs(n);sort(a);return a;}

		Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		long[]nextlongSum(int n){long[]a=new long[n];a[0]=nextLong();low=high=a[0];for(int i=1;i<n;i++){
			a[i]=nextLong();low=min(low,a[i]);high=max(high,a[i]);a[i]+=a[i-1];}return a;}

		long[]nextSortedlongSum(int n){long[]a=nextSortedlongs(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		long[][]nextlongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongs(w));return a;}

		long[][]nextlongMatSum(int h,int w){long[][]a=new long[h+1][w+1];for(int i=1;i<=h;i++)for(int j=1;j<=w;j++)
			a[i][j]=nextLong()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		long[][][]nextlongs3D(int x,int y,int z){long[][][]a=new long[x][y][z];setAll(a,b->nextlongMat(y,z));return a;}

		long[][][]nextlongs3DSum(int x,int y,int z){long[][][]e=new long[x+1][y+1][z+1];for(int a=1;a<=x;a++){
			for(int b=1;b<=y;b++){for(int c=1;c<=z;c++){e[a][b][c]=nextLong()+e[a-1][b][c]+e[a][b-1][c]+e[a][b][c-1];
			e[a][b][c]-=e[a-1][b-1][c]+e[a-1][b][c-1]+e[a][b-1][c-1]-e[a-1][b-1][c-1];}}}return e;}

	}
}