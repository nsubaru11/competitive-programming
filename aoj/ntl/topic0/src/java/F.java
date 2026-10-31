import java.io.*;
import java.util.*;

public class F {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			
		}
		out.flush();
	}

	@SuppressWarnings("unused")
	private static class FastScanner {
        private final BufferedInputStream reader=new BufferedInputStream(System.in);
        private final byte[]buffer=new byte[0x10000];
        private int i=0,length=0;private long sum=0;

        public int read(){
            if(i==length){
                i=0;
            	try{length = reader.read(buffer);}
                catch(IOException ignored){}
                if(length==-1)return 0;
            }
            if(length<=i)throw new RuntimeException();
            return buffer[i++];
        }

        String next(){
            StringBuilder sb=new StringBuilder();
            int b=read();
            while(b<'!'||'~'<b){b=read();}
            while('!'<=b&&b<='~'){sb.appendCodePoint(b);b = read();}
            return sb.toString();
        }

        String nextLine(){
            StringBuilder sb=new StringBuilder();
            int b=read();
            while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
            if(b == '\r')read();
            return sb.toString();
        }

        int nextInt(){
            long a=nextLong();
            if((int)a!=a)throw new NumberFormatException();
            return (int)a;
        }

        long nextLong(){
            int b=read();while(b<'!'||'~'<b){b=read();}
            boolean neg=false;
            if(b=='-'){neg=true;b=read();}
            long n=0;int c=0;
            while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
            if(c==0||c>=2&&n==0)throw new NumberFormatException();
            return neg?-n:n;
        }

        double nextDouble(){return Double.parseDouble(next());}

        String[]nextStrings(int n){
            String[]s=new String[n];
            for(int i=0;i<n;i++)s[i]=next();
            return s;
        }

        String[][]nextStringMat(int n,int m){
            String[][]s=new String[n][m];
            for(int i=0;i<n;i++)s[i]=nextStrings(m);
            return s;
        }

        char[]nextChars(){return next().toCharArray();}

        char[]nextChars(int n){
        	char[]c=new char[n];
        	for(int i=0;i<n;i++)c[i]=next().charAt(0);
        	return c;
        }

        char[]nextSortedChars(){
            char[]c=nextChars();
            Arrays.sort(c);
        	return c;
        }

        char[]nextSortedChars(int n){
            char[]c=nextChars(n);
            Arrays.sort(c);
        	return c;
        }

        char[][]nextCharMat(int n){
            char[][]c=new char[n][];
            for(int i=0;i<n;i++)c[i]=nextChars();
            return c;
        }

        char[][]nextCharMat(int n,int m){
            char[][]c=new char[n][m];
            for(int i=0;i<n;i++)c[i]=nextChars(m);
            return c;
        }

        int[]nextInts(int n){
            int[]a=new int[n];
            for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];}
            return a;
        }

        Integer[]nextIntegers(int n){
            Integer[]a=new Integer[n];
            for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];}
            return a;
        }

        int[]nextSortedInts(int n){
        	int[]a=nextInts(n);
        	Arrays.sort(a);return a;
        }

        Integer[]nextSortedIntegers(int n){
        	Integer[]a=nextIntegers(n);
        	Arrays.sort(a);return a;
        }

        int[]nextIntSum(int n){
        	int[]a=new int[n];a[0]=nextInt();
            for(int i=1;i<n;i++)a[i]+=nextInt()+a[i-1];
            return a;
        }

        int[]nextSortedIntSum(int n){
        	int[]a=nextSortedInts(n);
        	for(int i=1;i<n;i++)a[i]+=a[i-1];
        	return a;
        }

        int[][]nextIntMat(int h,int w){
            int[][]a=new int[h][w];
            for(int i=0;i<h;i++)a[i]=nextInts(w);
            return a;
        }

        int[][]nextIntMatSum(int h,int w) {
        	int[][]a=new int[h][w];
        	for(int i=0;i<h;i++)a[i]=nextIntSum(w);
    		for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];
        	return a;
        }

        long[]nextlongs(int n){
            long[]a=new long[n];
            for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];}
            return a;
        }

        Long[]nextLongs(int n){
            Long[]a=new Long[n];
            for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];}
            return a;
        }

        long[]nextSortedlongs(int n){
        	long[]a=nextlongs(n);
        	Arrays.sort(a);return a;
        }

        Long[]nextSortedLongs(int n){
        	Long[]a=nextLongs(n);
        	Arrays.sort(a);return a;
        }

        long[]nextlongSum(int n){
        	long[]a=new long[n];a[0]=nextLong();
        	for(int i=1;i<n;i++)a[i]+=nextLong()+a[i-1];
            return a;
        }

        long[]nextSortedlongSum(int n){
        	long[]a=nextSortedlongs(n);
        	for(int i=1;i<n;i++)a[i]+=a[i-1];
        	return a;
        }

        long[][]nextlongMat(int h,int w){
            long[][]a=new long[h][w];
            for(int i=0;i<h;i++)a[i]=nextlongs(w);
            return a;
        }

        long[][]nextlongMatSum(int h,int w){
        	long[][]a=new long[h][w];
        	for(int i=0;i<h;i++)a[i]=nextlongSum(w);
    		for (int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];
        	return a;
        }

    }

	@SuppressWarnings("unused")
	private static class FastPrinter {
        private final PrintWriter out=new PrintWriter(System.out);

        void println(Object o){out.println(o);}

        void print(Object o){out.print(o);}

        void println(char a){out.println(a);}

        void println(int a){out.println(a);}

        void println(long a){out.println(a);}

        void println(double a){out.println(a);}

        void println(String s){out.println(s);}

        void println(char[] s){out.println(s);}

        void println(int[] a){
            StringJoiner sj=new StringJoiner(" ");
            for(int i:a)sj.add(Integer.toString(i));
            out.println(sj);
        }

        void printf(String format, Object... args){
        	out.printf(format, args);
        }

        void printf(Locale l, String format, Object... args){
        	out.printf(l, format, args);
        }

        void println(long[] a){
            StringJoiner sj=new StringJoiner(" ");
            for(long i:a)sj.add(Long.toString(i));
            out.println(sj);
        }

        void print(boolean b){out.print(b);}

        void println(boolean b){out.println(b);}

        void print(char a){out.print(a);}

        void print(int a){out.print(a);}

        void print(long a){out.print(a);}

        void print(double a){out.print(a);}

        void print(String s){out.print(s);}

        void print(char[] s){out.print(String.valueOf(s));}

        void print(int[] a){
            StringJoiner sj = new StringJoiner(" ");
            for (int i: a) sj.add(Integer.toString(i));
            out.print(sj);
        }

        void print(long[] a){
            StringJoiner sj = new StringJoiner(" ");
            for (long i: a) sj.add(Long.toString(i));
            out.print(sj);
        }

        void flush(){out.flush();}

	}

}