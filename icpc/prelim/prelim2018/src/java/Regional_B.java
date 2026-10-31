import java.io.*;
import java.util.*;

public class Regional_B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		int n = Integer.parseInt(sc.next());
		HashSet<Long> set = new HashSet<>();	//オリジナル
		HashSet<Long> setd = new HashSet<>();	//考えうる公差の集合
		
		for (int i = 0; i < n; i++) {
			long x = sc.nextLong();
			set.add(x);
		}
		
		for (long x: set) {
			 for (long z: set) {
				 if (z - x != 0) {
					 setd.add(Math.abs(z - x)); 
				 }
			 }
		}
		
		long max = 0;
		HashSet<Long> sets = new HashSet<>();	//引き算後の配列の要素の集合
		HashSet<Long> setw = new HashSet<>();	//割り算後の配列の要素の集合
		for (long x: set) {
			
			sets.clear();
			for (long ｙ: set) {
				sets.add(ｙ - x);
			}
			
			for (long d: setd) {
				
				setw.clear();
				for (long s: sets) {
					if (s % d == 0) {
						setw.add(s / d);
					}
				}
				
				long i = 0;
				long count = 0;

				while (setw.contains(i)) {
						count++;
						i++;
				}
				
				if (max < count) {
					max = count;
				}
				
			}
		}
		
		System.out.println(max);
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

