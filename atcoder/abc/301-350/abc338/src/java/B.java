import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		String S = sc.next();
		int len = S.length();
		String lower = "zyxwvutsrqponmlkjihgfedcba";	//あらかじめ小文字列を用意
		int count = 0;
		char maxLetter = 'a';
		for (int i = 0; i < lower.length(); i++) {
			if (S.contains(lower.substring(i, i + 1))) { //lowerのi番目の文字がSにどれだけ含まれるか
				int maxCounts = 0;
				for (int j = 0; j < len; j++) {
					if (S.charAt(j) == lower.charAt(i)) {
						maxCounts++;
					}
				}
				if (count <= maxCounts) {	//最大値と文字を更新
					count = maxCounts;
					maxLetter = lower.charAt(i);
				}
			}
		}
		System.out.println(maxLetter);
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
