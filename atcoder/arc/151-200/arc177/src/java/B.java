import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		
		int n = Integer.parseInt(sc.next());
		
		StringBuilder s = new StringBuilder(sc.next());
		String S = s.toString();
		
		ArrayList<Integer> count  = new ArrayList<>(); //AおよびBを何回押さないといけないかのカウント
		
		for (int i = n - 1; i >= 0; i--) {
			if (S.charAt(i) == '1') {	//i番目が1のとき (i + 1)回Aを押す
				count.add(i + 1);
				for (int j = i - 1; j >= 0; j-- ) {
					if (S.charAt(j) == '0') {	//j番目が0のとき (j + 1)回Bを押す
						count.add(j + 1);
						i = j;
						break;
					} else if (j == 0) {
						i = 0;	//一番最初が1で始まるときの例外処理
						break;
					}
				}
			}
		}
		
		String Switch = "";
		for (int i = 0; i < count.size(); i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < count.get(i); j++) {
					Switch += "A";	//countの偶数番目の値はAを押す数
				}
			} else {
				for (int j = 0; j < count.get(i); j++) {
					Switch += "B";	////countの奇数番目の値はBを押す数
				}
			}
		}
		
		System.out.println(Switch.length());
		System.out.println(Switch);
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
