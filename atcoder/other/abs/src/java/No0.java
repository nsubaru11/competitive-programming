import java.io.*;
import java.util.*;
import static java.lang.String.*;

public class No0 {

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		String s = sc.next();
		out.print(a + b + c, s);
		out.flush();
	}

	private static final FastPrinter out = new FastPrinter();
	private static final FastScanner sc = new FastScanner();
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){StringBuilder sb=new StringBuilder();int b=read();while(b<'!'||'~'<b){b=read();}
		while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		int nextInt(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

	}
	private static class FastPrinter {
		private static final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out));

		void print(Object...o){
			StringJoiner sj=new StringJoiner(" ");for(Object x:o)sj.add(valueOf(x));out.println(sj);}

		void flush(){out.flush();}

	}
}
