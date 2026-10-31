import java.io.*;
import static java.util.Arrays.*;

public class B {

	public static void main(String[] args) {
		int n = sc.nextInt();
		boolean s[] = new boolean[14];
		boolean h[] = new boolean[14];
		boolean d[] = new boolean[14];
		boolean c[] = new boolean[14];
		for (int i = 0; i < n; i++) {
			String a = sc.next();
			int x = sc.nextInt();
			if (a.equals("S")) {
				s[x] = true;
			} else if (a.equals("H")) {
				h[x] = true;
			} else if (a.equals("C")) {
				c[x] = true;
			} else {
				d[x] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 14; i++) {
			if (!s[i]) sb.append("S ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!h[i]) sb.append("H ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!c[i]) sb.append("C ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!d[i]) sb.append("D ").append(i).append("\n");
		}
		System.out.print(sb);
	}

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private long sum,low,high;

		public int read(){
			if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		String nextLine(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
			if(b=='\r')read();return sb.toString();}

		int nextInt(){return(int)nextLong();}

		long nextLong(){
			int b=read(),c=0;while(b<'!'||'~'<b){b=read();}
			boolean neg=false;if(b=='-'){neg=true;b=read();}
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		double nextDouble(){return Double.parseDouble(next());}

		String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		String[][]nextStringMat(int n,int m){
			String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		char[]nextChars(){return next().toCharArray();}

		char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=next().charAt(0);return c;}

		char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		int[]nextInts(int n){
			low=0x7fffffff;high=0x80000000;sum=0;int[]a=new int[n];
			for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Integer[]nextIntegers(int n){
			low=0x7fffffff;high=0x80000000;sum=0;Integer[]a=new Integer[n];
			for (int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		int[]nextSortedInts(int n){int[]a=nextInts(n);sort(a);return a;}

		Integer[]nextSortedIntegers(int n){Integer[]a=nextIntegers(n);sort(a);return a;}

		int[]nextIntSum(int n){int[]a=new int[n];a[0]=nextInt();low=a[0];high=a[0];for(int i=1;i<n;i++)
		{a[i]=nextInt();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		int[]nextSortedIntSum(int n){int[]a=nextSortedInts(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInts(w));return a;}

		int[][]nextIntMatSum(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextIntSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

		long[]nextlongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;long[]a=new long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Long[]nextLongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;Long[]a=new Long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		long[]nextSortedlongs(int n){long[]a=nextlongs(n);sort(a);return a;}

		Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		long[]nextlongSum(int n){long[]a=new long[n];a[0]=nextLong();low=a[0];high=a[0];for(int i=1;i<n;i++){
			a[i]=nextLong();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		long[]nextSortedlongSum(int n){long[]a=nextSortedlongs(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		long[][]nextlongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongs(w));return a;}

		long[][]nextlongMatSum(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

	}
}
