import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		int h = Integer.parseInt(sc.next());
		int w = Integer.parseInt(sc.next());
		int n = Integer.parseInt(sc.next());
		String t = sc.next();
		Character[][] map = new Character[h][w];
		for (int i = 0; i < h; i++) {
			String s = sc.next();
			for (int j = 0; j < w; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		System.out.println(search(map, h, w, n, t));
	}
	
	private static int search(Character[][] map, int h, int w, int n, String t) {
		int count = 0;
		for (int i = 1; i < h - 1; i++) {
			for (int j = 1; j < w - 1; j++) {
				if (map[i][j] == '.') {
					if (move(t, map, i, j, 0, n)) {
						count++;
					}
				}
			}
		}
		return count;
	}
	
	private static boolean move(String t, Character[][] map, int i, int j, int idx, int n) {
		boolean f = false;
		if (idx == n) {
			return true;
		}
		if (t.charAt(idx) == 'L') {
			if (map[i][j - 1] == '.') {
				f = move(t, map, i, j - 1, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'R') {
			if (map[i][j + 1] == '.') {
				f = move(t, map, i, j + 1, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'U') {
			if (map[i - 1][j] == '.') {
				f = move(t, map, i - 1, j, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'D') {
			if (map[i + 1][j] == '.') {
				f = move(t, map, i + 1, j, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		}
		return f;
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
