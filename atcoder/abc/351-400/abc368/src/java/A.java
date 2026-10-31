import java.io.*;
import java.util.*;
import static java.lang.String.*;
import static java.util.Arrays.*;
import static java.lang.Math.*;

public class A {

	public static void main(String[] args) {
		int n = sc.nextInt();
		int r = sc.nextInt();
		ArrayList<Integer> al = new ArrayList<>(n);
		while (n-- > 0) {
			al.add(sc.nextInt());
		}
		Collections.rotate(al, r);
		out.print(al.toArray()).flush();
	}

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;public long sum,low,high;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		public String next(){StringBuilder sb=new StringBuilder();int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		public String nextLine(){StringBuilder sb=new StringBuilder();int b=read();while(b!=0&&b!='\r'&&b!='\n'){
			sb.appendCodePoint(b);b=read();}if(b=='\r')read();return sb.toString();}

		public int nextInt(){return(int)nextLong();}

		public long nextLong(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		public double nextDouble(){return Double.parseDouble(next());}

		public String[]next(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		public String[][]next(int n,int m){String[][]s=new String[n][m];setAll(s,x->next(m));return s;}

		public char nextChar(){return next().charAt(0);}

		public char[]nextChars(){return next().toCharArray();}

		public char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=nextChar();return c;}

		public char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		public char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		public char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		public char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		public int[]nextInt(int n){low=0x7fffffff;high=0x80000000;sum=0;int[]a=new int[n];
			for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		public Integer[]nextInteger(int n){low=0x7fffffff;high=0x80000000;sum=0;Integer[]a=new Integer[n];
			for (int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		public int[]nextSortedInt(int n){int[]a=nextInt(n);sort(a);return a;}

		public Integer[]nextSortedInteger(int n){Integer[]a=nextInteger(n);sort(a);return a;}

		public int[]nextIntSum(int n){int[]a=new int[n];a[0]=nextInt();low=high=a[0];for(int i=1;i<n;i++)
			{a[i]=nextInt();low=min(low,a[i]);high=max(high,a[i]);a[i]+=a[i-1];}return a;}

		public int[]nextSortedIntSum(int n){int[]a=nextSortedInt(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		public int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInt(w));return a;}

		public int[][]nextIntMatSum(int h,int w){int[][]a=new int[h+1][w+1];for(int i=1;i<=h;i++)for(int j=1;j<=w;j++)
			a[i][j]=nextInt()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public int[][][]nextInt3D(int x,int y,int z){
			int[][][]a=new int[x][y][z];setAll(a,b->nextIntMat(y,z));return a;}

		public int[][][]nextIntSum3D(int x,int y,int z){int[][][]e=new int[x+1][y+1][z+1];for(int a=1;a<=x;a++){
			for(int b=1;b<=y;b++){for(int c=1;c<=z;c++){e[a][b][c]=nextInt()+e[a-1][b][c]+e[a][b-1][c]+e[a][b][c-1];
					e[a][b][c]-=e[a-1][b-1][c]+e[a-1][b][c-1]+e[a][b-1][c-1]-e[a-1][b-1][c-1];}}}return e;}

		public long[]nextlong(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;long[]a=new long[n];
			for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		public Long[]nextLong(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;Long[]a=new Long[n];
			for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];low=min(low,a[i]);high=max(high,a[i]);}return a;}

		public long[]nextSortedlong(int n){long[]a=nextlong(n);sort(a);return a;}

		public Long[]nextSortedLong(int n){Long[]a=nextLong(n);sort(a);return a;}

		public long[]nextlongSum(int n){long[]a=new long[n];a[0]=nextLong();low=high=a[0];for(int i=1;i<n;i++){
			a[i]=nextLong();low=min(low,a[i]);high=max(high,a[i]);a[i]+=a[i-1];}return a;}

		public long[]nextSortedlongSum(int n){long[]a=nextSortedlong(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		public long[][]nextlongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlong(w));return a;}

		public long[][]nextlongMatSum(int h,int w){long[][]a=new long[h+1][w+1];for(int i=1;i<=h;i++)for(int j=1;j<=w;j++)
			a[i][j]=nextLong()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public long[][][]nextlong3D(int x,int y,int z){long[][][]a=new long[x][y][z];setAll(a,b->nextlongMat(y,z));return a;}

		public long[][][]nextlongSum3D(int x,int y,int z){long[][][]e=new long[x+1][y+1][z+1];for(int a=1;a<=x;a++){
			for(int b=1;b<=y;b++){for(int c=1;c<=z;c++){e[a][b][c]=nextLong()+e[a-1][b][c]+e[a][b-1][c]+e[a][b][c-1];
			e[a][b][c]-=e[a-1][b-1][c]+e[a-1][b][c-1]+e[a][b-1][c-1]-e[a-1][b-1][c-1];}}}return e;}

	}
	@SuppressWarnings("unused")
	private static class FastPrinter {
		private static final StringBuilder sb=new StringBuilder();

		public FastPrinter println(){sb.append("\n");return this;}

		public FastPrinter println(Object o){print(o).println();return this;}

		public FastPrinter println(String s){print(s).println();return this;}

		public FastPrinter println(boolean f){print(f).println();return this;}

		public FastPrinter println(char c){print(c).println();return this;}

		public FastPrinter println(int a){print(a).println();return this;}

		public FastPrinter println(long a){print(a).println();return this;}

		public FastPrinter println(double a){print(a).println();return this;}

		public FastPrinter println(double a,int n){print(a,n).println();return this;}

		public FastPrinter println(Object...o){for(Object x:o)println(x);return this;}

		public FastPrinter println(String[]s){for(String x:s)println(x);return this;}

		public FastPrinter println(char[]c){for(char x:c)println(x);return this;}

		public FastPrinter println(int[]a){for(int x:a)println(x);return this;}

		public FastPrinter println(long[]a){for(long x:a)println(x);return this;}

		public FastPrinter println(Object[][]o){for(Object[]x:o)print(x);return this;}

		public FastPrinter println(char[][]c){for(char[]x:c)print(x);return this;}

		public FastPrinter println(int[][]a){for(int[]x:a)print(x);return this;}

		public FastPrinter println(long[][]a){for(long[]x:a)print(x);return this;}

		public FastPrinter print(Object o){sb.append(o);return this;}

		public FastPrinter print(String s){sb.append(s);return this;}

		public FastPrinter print(boolean f){sb.append(f);return this;}

		public FastPrinter print(char c){sb.append(c);return this;}

		public FastPrinter print(int a){sb.append(a);return this;}

		public FastPrinter print(long a){sb.append(a);return this;}

		public FastPrinter print(double a){sb.append(a);return this;}

		public FastPrinter print(double a,int n){if(n==0){sb.append(round(a));return this;}
		if(a<0){sb.append("-");a=-a;}a+=pow(10,-n)/2;sb.append((long)a).append(".");a-=(long)a;
		while(n-->0){a*=10;sb.append((int)a);a-=(int)a;}return this;}

		public FastPrinter print(Object...o){sb.append(o[0]);for(int i=1;i<o.length;i++){
			sb.append(" ").append(o[i]);}sb.append("\n");return this;}

		public FastPrinter print(String[]s){sb.append(s[0]);for(int i=1;i<s.length;i++){
			sb.append(" ").append(s[i]);}sb.append("\n");return this;}

		public FastPrinter print(char[]c){sb.append(c[0]);for(int i=1;i<c.length;i++){
			sb.append(" ").append(c[i]);}sb.append("\n");return this;}

		public FastPrinter print(int[]a){sb.append(a[0]);for(int i=1;i<a.length;i++){
			sb.append(" ").append(a[i]);}sb.append("\n");return this;}

		public FastPrinter print(long[]a){sb.append(a[0]);for(int i=1;i<a.length;i++){
			sb.append(" ").append(a[i]);}sb.append("\n");return this;}

		public FastPrinter printf(String frmt,Object...args){sb.append(format(frmt,args));return this;}

		public FastPrinter printf(Locale l,String frmt,Object...args){sb.append(format(l,frmt,args));return this;}

		public FastPrinter flush(){System.out.print(sb);return this;}

		public FastPrinter clear(){sb.setLength(0);return this;}
	}
}