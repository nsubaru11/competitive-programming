import java.io.*;
import static java.util.Arrays.*;

public class No6 {

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] arr = sc.nextSortedInt(n);
		int Alice = 0, Bob = 0;
		if (n % 2 == 0) {
			n /= 2;
			for (int i = 0; i < n; i++) {
				Alice += arr[2 * i + 1];
				Bob += arr[2 * i];
			}
		} else {
			Alice += arr[n - 1];
			n = (n - 1) / 2;
			for (int i = 0; i < n; i++) {
				Alice += arr[2 * i];
				Bob += arr[2 * i + 1];
			}
		}
		System.out.println(Alice - Bob);
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

		int[] nextSortedInt(int n){int[]a=new int[n];setAll(a,x->x=nextInt());sort(a);return a;}
	}
}
