import java.io.*;
import java.util.*;
import java.util.stream.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		long n = Long.parseLong(sc.next());
		ArrayList<Integer> ary = nAryNum(5, n - 1);
		long ans = 0L;
		long tenPow = 1L;
		for (int i = 0; i < ary.size(); i++) {
			ans += ary.get(i) * 2 * tenPow;
			tenPow *= 10;
		}
		System.out.println(ans);
		
	}
	
	private static ArrayList<Integer> nAryNum(long base, long num) {
		// n進数表記 （nは10未満） をListのidx番号に対応させて再現
		ArrayList<Integer> ary = new ArrayList<>();
		long pow = 1L;
		long max = num;
		while (pow < max) {
			pow *= base;
			ary.add((int) (num % pow / (pow / base)));
			num -= num % pow;
		}
		return ary;
	}

	private static TreeMap<Long, Integer> powMap(int num, int i, int j) {
		// (num ^ i) から (num ^ j) までのnumのべき乗をTreeMapに入れる
		TreeMap<Long, Integer> powMap = new TreeMap<>();
		long strt = pow(num, i);
		powMap.put(strt, i);
		for (int idx = i + 1; idx < j; idx++) {
			strt *= num;
			powMap.put(strt, idx);
		}
		return powMap;
	}

	private static long modPow(long a, long b, long mod) {
		long ans = 1L;
		b %= mod - 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans = ans * a % mod;
			}
			a = a * a % mod;
			b >>= 1;
		}
		return ans;
	}

	private static long pow(long a, long b) {
		long ans = 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans *= a;
			}
			a *= a;
			b >>= 1;
		}
		return ans;
	}

	private static class FastScanner {
	    private final InputStream in = System.in;
	    private final byte[] buffer = new byte[1024];
	    private int ptr = 0;
	    private int buflen = 0;
	    private boolean hasNextByte() {
	        if (ptr < buflen) {
	            return true;
	        }else{
	            ptr = 0;
	            try {
	                buflen = in.read(buffer);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            if (buflen <= 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
	    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
	    private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
	    public boolean hasNext() { skipUnprintable(); return hasNextByte();}
	    public String next() {
	        if (!hasNext()) throw new NoSuchElementException();
	        StringBuilder sb = new StringBuilder();
	        int b = readByte();
	        while(isPrintableChar(b)) {
	            sb.appendCodePoint(b);
	            b = readByte();
	        }
	        return sb.toString();
	    }
	    public long nextLong() {
	        if (!hasNext()) throw new NoSuchElementException();
	        long n = 0;
	        boolean minus = false;
	        int b = readByte();
	        if (b == '-') {
	            minus = true;
	            b = readByte();
	        }
	        if (b < '0' || '9' < b) {
	            throw new NumberFormatException();
	        }
	        while(true){
	            if ('0' <= b && b <= '9') {
	                n *= 10;
	                n += b - '0';
	            }else if(b == -1 || !isPrintableChar(b)){
	                return minus ? -n : n;
	            }else{
	                throw new NumberFormatException();
	            }
	            b = readByte();
	        }
	    }
	}

}