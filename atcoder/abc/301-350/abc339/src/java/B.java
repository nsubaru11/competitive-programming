import java.io.*;
import java.util.*;

public class B {

	private static int h;
	private static int w;
	private static int n;
	
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		h = Integer.parseInt(sc.next());
		w = Integer.parseInt(sc.next());
		n = Integer.parseInt(sc.next());
		char[][] map = new char[h][w];
		for (int i = 0; i < h; i++) {
			Arrays.fill(map[i], '.');
		}
		map = operate(map, 0, 0, 'U', 0);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}
	
	private static char[][] operate(char[][] map, int i, int j, char dir, int idx) {
		if (idx == n) {
			return map;
		}
		if (i == h) {
			i = 0;
		}
		if (i == -1) {
			i = h - 1;
		}
		if (j == w) {
			j = 0;
		}
		if (j == -1) {
			j = w - 1;
		}
		if (map[i][j] == '.') {
			map[i][j] = '#';
			if (dir == 'U') {
				operate(map, i, j + 1, 'R', idx + 1);
			} else if (dir == 'R') {
				operate(map, i + 1, j, 'D', idx + 1);
			} else if (dir == 'D') {
				operate(map, i, j - 1, 'L', idx + 1);
			} else if (dir == 'L') {
				operate(map, i - 1, j, 'U', idx + 1);
			}
			
		} else {
			map[i][j] = '.';
			if (dir == 'U') {
				operate(map, i, j - 1, 'L', idx + 1);
			} else if (dir == 'L') {
				operate(map, i + 1, j, 'D', idx + 1);
			} else if (dir == 'D') {
				operate(map, i, j + 1, 'R', idx + 1);
			} else if (dir == 'R') {
				operate(map, i - 1, j, 'U', idx + 1);
			}
		}
		return map;
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
