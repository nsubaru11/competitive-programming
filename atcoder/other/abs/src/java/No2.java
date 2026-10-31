import java.io.*;

public class No2 {

	public static void main(String[] args) {
		int cnt = 0;
		for (char c: sc.nextChars()) {
			if (c == '1') {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static final FastScanner sc = new FastScanner();
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){StringBuilder sb=new StringBuilder();int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		char[]nextChars(){return next().toCharArray();}

	}
}
