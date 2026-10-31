import java.io.*;
import java.util.*;
import static java.lang.String.*;
import static java.util.Arrays.*;

public class A {

	public static void main (String[] args) {
		int n = sc.nextInt();
		Integer[] a = sc.nextIntegers(n);
		Collections.reverse(asList(a));
		out.print(a);
		out.flush();
	}

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		int nextInt(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		Integer[]nextIntegers(int n){Integer[]a=new Integer[n];setAll(a,x->x=nextInt());return a;}

	}	@SuppressWarnings("unused")
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
