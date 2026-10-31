import java.io.*;

public class A09 {

	public static void main(String[] args) {
		int h = sc.nextInt(), w = sc.nextInt();
		int n = sc.nextInt();
		int[][] x = new int[h + 2][w + 2];
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt(), b = sc.nextInt();
			int c = sc.nextInt(), d = sc.nextInt();
			x[a][b]++;
			x[a][d + 1]--;
			x[c + 1][b]--;
			x[c + 1][d + 1]++;
		}
		for (int i = 1; i <= h; i++) {
			for (int j = 2; j <= w; j++) {
				x[i][j] += x[i][j - 1];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				if (j > 1) sb.append(" ");
				x[i][j] += (i > 1) ? x[i - 1][j]: 0;
				sb.append(x[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private long sum,low,high;

		public int read(){if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		int nextInt(){int b=read(),c=0;while(b<'!'||'~'<b){b=read();}boolean neg=false;
			if(b=='-'){neg=true;b=read();}int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

	}
}
