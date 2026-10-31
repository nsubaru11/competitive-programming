import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class A3 {

    public static void main(String args[] ) {
        FastScanner sc = new FastScanner();
        FastPrinter out = new FastPrinter();
        
        int q = sc.nextInt();
        int[] a = new int[q];
        int max = 0;
        for (int i = 0; i < q; i++) {
        	a[i] = sc.nextInt();
        	max = Math.max(max, a[i]);
        }
        int n = max;

        int[] table = new int[n + 1];
        for (int i = 2; i <= n; i *= 2) table[i] = 2;
		for (int i = 3; i <= n; i += 3) table[i] = 3;
		for (int i = 5; i <= n; i += 6) {
            if (table[i] == 0) {
            	for (int j = i; j < n + 1; j += i) {
            		table[j] = i;
            	}
			}
            int idx = i + 2;
            if (idx <= n && table[idx] == 0) {
            	for (int j = idx; j < n + 1; j += idx) {
            		table[j] = idx;
            	}
			}
		}

		String[] x = new String[n + 1];
		x[0] = "0";
		x[1] = "0";
		String[] y = new String[n + 1];
		y[0] = "0";
		y[1] = "0";
		
		for (int i = 2; i < n + 1; i++) {
			BigInteger b1 = new BigInteger(Integer.toString(table[i]));
    		b1 = b1.add(new BigInteger(x[i - 1]));
    		x[i] = b1.toString();
    		
		}
        out.flush();
    }

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

    }

    private static class FastPrinter {
        private final PrintWriter out = new PrintWriter(System.out);

        void println(Object o) {
        	out.println(o);
        }

        void flush() {
            out.flush();
        }
    }

}