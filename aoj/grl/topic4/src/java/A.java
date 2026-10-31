import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args){
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();
		
		int n = Integer.parseInt(sc.next());
		for (int i = 0; i < n; i++) {
			
		}
		
		out.flush();
	}

	@SuppressWarnings("unused")
	private static class FastScanner {
        private final BufferedInputStream reader = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[0x10000];
        private int i = 0;
        private int length = 0;

        public int read() {
            if (i == length) {
                i = 0;
                try {
                    length = reader.read(buffer);
                } catch (IOException ignored) {
                }
                if (length == -1) return 0;
            }
            if (length <= i) throw new RuntimeException();
            return buffer[i++];
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int b = read();
            while (b < '!' || '~' < b) b = read();
            while ('!' <= b && b <= '~') {
                sb.appendCodePoint(b);
                b = read();
            }
            return sb.toString();
        }

        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int b = read();
            while (b != 0 && b != '\r' && b != '\n') {
                sb.appendCodePoint(b);
                b = read();
            }
            if (b == '\r') read();
            return sb.toString();
        }

        String[] nextStrings(int n) {
            String[] s = new String[n];
            for (int i = 0; i < n; i++) s[i] = next();
            return s;
        }

        int nextInt() {
            long a = nextLong();
            if ((int)a != a) throw new NumberFormatException();
            return (int)a;
        }

        long nextLong() {
            int b = read();
            while (b < '!' || '~' < b) b = read();
            boolean neg = false;
            if (b == '-') {
                neg = true;
                b = read();
            }
            long n = 0;
            int c = 0;
            while ('0' <= b && b <= '9') {
                n = n * 10 + b - '0';
                b = read();
                c++;
            }
            if (c == 0 || c >= 2 && n == 0) throw new NumberFormatException();
            return neg ? -n : n;
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        char[] nextChars() {
            return next().toCharArray();
        }

        char[][] nextCharMat(int n, int m) {
            char[][] a = new char[n][m];
            for (int i = 0; i < n; i++) a[i] = next().toCharArray();
            return a;
        }

        int[] nextInts(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int[] nextSortedInts(int n) {
        	int[] a = new int[n];
        	for (int i = 0; i < n; i++) a[i] = nextInt();
        	Arrays.sort(a);
        	return a;
        }

        int[] nextIntSum(int n) {
        	int[] a = new int[n];
        	a[0] = nextInt();
            for (int i = 1; i < n; i++) a[i] += nextInt() + a[i - 1];
            return a;
        }

        int[][] nextIntMat(int h, int w) {
            int[][] a = new int[h][w];
            for (int i = 0; i < h; i++) a[i] = nextInts(w);
            return a;
        }

        int[][] nextIntMatSum(int h, int w) {
        	int[][] a = new int[h][w];
        	for (int i = 0; i < h; i++) a[i] = nextIntSum(w);
    		for (int i = 1; i < h; i++) {
    			for (int j = 0; j < w; j++) a[i][j] += a[i - 1][j];
    		}
        	return a;
        }

        long[] nextLongs(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        long[] nextSortedLongs(int n) {
        	long[] a = new long[n];
        	for (int i = 0; i < n; i++) a[i] = nextLong();
        	Arrays.sort(a);
        	return a;
        }

        long[] nextLongSum(int n) {
        	long[] a = new long[n];
        	a[0] = nextLong();
        	for (int i = 1; i < n; i++) a[i] += nextLong() + a[i - 1];
            return a;
        }

        long[][] nextLongMat(int h, int w) {
            long[][] a = new long[h][w];
            for (int i = 0; i < h; i++) a[i] = nextLongs(w);
            return a;
        }

        long[][] nextLongMatSum(int h, int w) {
        	long[][] a = new long[h][w];
        	for (int i = 0; i < h; i++) a[i] = nextLongSum(w);
    		for (int i = 1; i < h; i++) {
    			for (int j = 0; j < w; j++) a[i][j] += a[i - 1][j];
    		}
        	return a;
        }

    }

	@SuppressWarnings("unused")
    private static class FastPrinter {
        private final PrintWriter out = new PrintWriter(System.out);

        void println(Object o) {
        	out.println(o);
        }

        void print(Object o) {
        	out.print(o);
        }

        void println(char a) {
            out.println(a);
        }

        void println(int a) {
            out.println(a);
        }

        void println(long a) {
            out.println(a);
        }

        void println(double a) {
            out.println(String.format("%.10f", a));
        }

        void println(String s) {
            out.println(s);
        }

        void println(char[] s) {
            out.println(String.valueOf(s));
        }

        void println(int[] a) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i: a) sj.add(Integer.toString(i));
            out.println(sj);
        }

        void println(long[] a) {
            StringJoiner sj = new StringJoiner(" ");
            for (long i: a) sj.add(Long.toString(i));
            out.println(sj);
        }

        void print(char a) {
            out.print(a);
        }

        void print(int a) {
            out.print(a);
        }

        void print(long a) {
            out.print(a);
        }

        void print(double a) {
            out.print(String.format("%.10f", a));
        }

        void print(String s) {
            out.print(s);
        }

        void print(char[] s) {
            out.print(String.valueOf(s));
        }

        void print(int[] a) {
            StringJoiner sj = new StringJoiner(" ");
            for (int i: a) sj.add(Integer.toString(i));
            out.print(sj);
        }

        void print(long[] a) {
            StringJoiner sj = new StringJoiner(" ");
            for (long i: a) sj.add(Long.toString(i));
            out.print(sj);
        }

        void flush() {
            out.flush();
        }
    }

}