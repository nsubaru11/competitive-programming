import java.io.*;

public class No4 {

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		int x = sc.nextInt();
		int cnt = 0;
		for (int i = 0; i < c + 1; i++) {
			if (x % 100 == 50 && i % 2 == 0 && i != c) i++;
			if (x % 100 == 0 && i % 2 == 1 && i != c) i++;
			if (x < 50 * i) break;
			for (int j = 0; j < b + 1; j++) {
				if (x < (50 * i + 100 * j)) break;
				for (int k = 0; k < a + 1; k++) {
					if (x == (50 * i + 100 * j + 500 * k)) {
						cnt++;
						break;
					}
					if (x < (50 * i + 100 * j + 500 * k)) break;
				}
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

		int nextInt(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

	}
}
