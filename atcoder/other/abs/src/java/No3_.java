import java.io.*;

public class No3_ {

	public static void main(String[] args) {
		int n = sc.nextInt();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int cnt = 0;
			while (a % 2 == 0 && cnt < min) {
				cnt++;
				a /= 2;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min);
	}

	private static final FastScanner sc = new FastScanner();
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		int nextInt(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

	}
}
