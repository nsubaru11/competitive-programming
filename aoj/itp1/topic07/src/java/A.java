import java.io.*;
import java.util.*;
import static java.lang.String.*;
import static java.util.Arrays.*;

public class A {

	public static void main(String[] args) {
		while (true) {
			int m = sc.nextInt();
			int f = sc.nextInt();
			int r = sc.nextInt();
			if (m == -1 && f == -1 && r == -1) {
				out.flush();
				break;
			}
			if (m == -1 || f == -1) {
				out.println("F");
			} else if (m + f >= 80) {
				out.println("A");
			} else if (m + f >= 65) {
				out.println("B");
			} else if (m + f >= 50) {
				out.println("C");
			} else if (m + f >= 30) {
				if (r >= 50) {
					out.println("C");
				} else {
					out.println("D");
				}
			} else {
				out.println("F");
			}
		}
	}

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
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
	@SuppressWarnings("unused")
	private static class FastPrinter {
		private static final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out));

		void println(){out.println();}

		void println(Object o){out.println(o);}

		void println(String s){out.println(s);}

		void println(boolean f){out.println(f);}

		void println(char c){out.println(c);}

		void println(int a){out.println(a);}

		void println(long a){out.println(a);}

		void println(double a){out.println(a);}

		void println(Object...o){
			StringJoiner sj=new StringJoiner("\n");for(Object x:o)sj.add(valueOf(x));out.println(sj);}

		void println(char[]c){
			StringJoiner sj=new StringJoiner("\n");for(char x:c)sj.add(valueOf(x));out.println(sj);}

		void println(int[]a){StringJoiner sj=new StringJoiner("\n");
			for(int b:a)sj.add(Integer.toString(b));out.println(sj);}

		void println(long[]a){StringJoiner sj=new StringJoiner("\n");
			for(long b:a)sj.add(Long.toString(b));out.println(sj);}

		void println(Integer[]a){StringJoiner sj=new StringJoiner("\n");
		for(int b:a)sj.add(Integer.toString(b));out.println(sj);}

		void println(Long[]a){StringJoiner sj=new StringJoiner("\n");
		for(long b:a)sj.add(Long.toString(b));out.println(sj);}

		void println(Object[][]o){StringJoiner sj=new StringJoiner("\n");for(Object[]x:o){
			StringJoiner sj2=new StringJoiner(" ");for(Object y:x)sj2.add(valueOf(y));
			sj.add(sj2.toString());}out.println(sj);}

		void println(char[][]c){StringJoiner sj=new StringJoiner("\n");for(char[]x:c){
			StringJoiner sj2=new StringJoiner(" ");for(char y:x)sj2.add(valueOf(y));
			sj.add(sj2.toString());}out.println(sj);}

		void println(int[][]a){StringJoiner sj=new StringJoiner("\n");for(int[]b:a){
			StringJoiner sj2=new StringJoiner(" ");for(int c:b)sj2.add(Integer.toString(c));
			sj.add(sj2.toString());}out.println(sj);}

		void println(long[][]a){StringJoiner sj=new StringJoiner("\n");for(long[]b:a){
			StringJoiner sj2=new StringJoiner(" ");for(long c:b)sj2.add(Long.toString(c));
			sj.add(sj2.toString());}out.println(sj);}

		void print(Object o){out.print(o);}

		void print(String s){out.print(s);}

		void print(boolean f){out.print(f);}

		void print(char c){out.print(c);}

		void print(int a){out.print(a);}

		void print(long a){out.print(a);}

		void print(double a){out.print(a);}

		void print(Object...o){
			StringJoiner sj=new StringJoiner(" ");for(Object x:o)sj.add(valueOf(x));out.println(sj);}

		void print(char[]c){
			StringJoiner sj=new StringJoiner(" ");for(char x:c)sj.add(valueOf(x));out.println(sj);}

		void print(int[]a){
			StringJoiner sj=new StringJoiner(" ");for(int x:a)sj.add(Integer.toString(x));out.println(sj);}

		void print(long[]a){
			StringJoiner sj=new StringJoiner(" ");for(long x:a)sj.add(Long.toString(x));out.println(sj);}

		void print(Integer[]a){
			StringJoiner sj=new StringJoiner(" ");for(int x:a)sj.add(Integer.toString(x));out.println(sj);}

		void print(Long[]a){
			StringJoiner sj=new StringJoiner(" ");for(long x:a)sj.add(Long.toString(x));out.println(sj);}

		void printf(String frmt,Object...args){out.printf(frmt,args);}

		void printf(Locale l,String frmt,Object...args){out.printf(l,frmt,args);}

		void write(Object o){out.write(valueOf(o));}

		void write(String s){out.write(s);}

		void write(int a){out.write(Integer.toString(a));}

		void write(char c){out.write(valueOf(c));}

		void write(long a){out.write(Long.toString(a));}

		void writeln(){out.write("\n");}

		void writeln(Object o){out.write(valueOf(o));writeln();}

		void writeln(String s){out.write(s);writeln();}

		void writeln(int a){out.write(Integer.toString(a));writeln();}

		void writeln(char c){out.write(valueOf(c));writeln();}

		void writeln(long a){out.write(Long.toString(a));writeln();}

		void flush(){out.flush();}

		void close(){out.close();}

	}
}
