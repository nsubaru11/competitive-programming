import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class B {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		while (true) {
			int n = sc.nextInt(), x = sc.nextInt();
			if (n == 0 && x == 0) break;
			int cnt = 0;
			//a + b + c = x; a < b < c;
			for (int a = 1; a <= min(n, (x - 3) / 3); a++) {
				int b = a + 1;//bの最小値
				int c = min(x - a - b, n);//cの最小値
				int add = min((x - a + 1) / 2 - b, c - (x - a) / 2);
				cnt += (add < 0) ? 0: add;
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb);
	}

	private static FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
        private final BufferedInputStream reader=new BufferedInputStream(System.in);
        private final byte[]buffer=new byte[0x10000];
        private int i=0,len=0;private long sum,low,high;

        public int read(){
            if(i==len){
                i=0;
            	try{len=reader.read(buffer);}
                catch(IOException ignored){}
                if(len==-1)return 0;
            }
            if(len<=i)throw new RuntimeException();
            return buffer[i++];
        }

        String next(){
            StringBuilder sb=new StringBuilder();
            int b=read();
            while(b<'!'||'~'<b){b=read();}
            while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}
            return sb.toString();
        }

        String nextLine(){
            StringBuilder sb=new StringBuilder();
            int b=read();
            while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
            if(b=='\r')read();
            return sb.toString();
        }

        int nextInt(){return Math.toIntExact(nextLong());}

        long nextLong(){
            int b=read();while(b<'!'||'~'<b){b=read();}
            boolean neg=false;
            if(b=='-'){neg=true;b=read();}
            long n=0;int c=0;
            while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
            if(c==0||c>=2&&n==0)throw new NumberFormatException();
            return neg?-n:n;
        }

        double nextDouble(){return Double.parseDouble(next());}

        String[]nextStrings(int n){
            String[]s=new String[n];
            for(int i=0;i<n;i++)s[i]=next();
            return s;
        }

        String[][]nextStringMat(int n,int m){
            String[][]s=new String[n][m];
            for(int i=0;i<n;i++)s[i]=nextStrings(m);
            return s;
        }

        char[]nextChars(){return next().toCharArray();}

        char[]nextChars(int n){
        	char[]c=new char[n];
        	for(int i=0;i<n;i++)c[i]=next().charAt(0);
        	return c;
        }

        char[]nextSortedChars(){
            char[]c=nextChars();
            Arrays.sort(c);return c;
        }

        char[]nextSortedChars(int n){
            char[]c=nextChars(n);
            Arrays.sort(c);return c;
        }

        char[][]nextCharMat(int n){
            char[][]c=new char[n][];
            for(int i=0;i<n;i++)c[i]=nextChars();
            return c;
        }

        char[][]nextCharMat(int n,int m){
            char[][]c=new char[n][m];
            for(int i=0;i<n;i++)c[i]=nextChars(m);
            return c;
        }

        int[]nextInts(int n){
        	low=2147483647;high=-2147483648;
            sum=0;int[]a=new int[n];
            for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
            	if(low>a[i])low=a[i];if(high<a[i])high=a[i];}
            return a;
        }

        Integer[]nextIntegers(int n){
        	low=2147483647;high=-2147483648;
        	sum=0;Integer[]a=new Integer[n];
            for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
            	if(low>a[i])low=a[i];if(high<a[i])high=a[i];}
            return a;
        }

        int[]nextSortedInts(int n){
        	int[]a=nextInts(n);Arrays.sort(a);return a;
        }

        Integer[]nextSortedIntegers(int n){
        	Integer[]a=nextIntegers(n);Arrays.sort(a);return a;
        }

        int[]nextIntSum(int n){
        	int[]a=new int[n];a[0]=nextInt();
            for(int i=1;i<n;i++)a[i]=nextInt()+a[i-1];
            return a;
        }

        int[]nextSortedIntSum(int n){
        	int[]a=nextSortedInts(n);
        	for(int i=1;i<n;i++)a[i]+=a[i-1];
        	return a;
        }

        int[][]nextIntMat(int h,int w){
            int[][]a=new int[h][w];
            for(int i=0;i<h;i++)a[i]=nextInts(w);
            return a;
        }

        int[][]nextIntMatSum(int h,int w) {
        	int[][]a=new int[h][w];
        	for(int i=0;i<h;i++)a[i]=nextIntSum(w);
    		for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];
        	return a;
        }

        long[]nextlongs(int n){
        	low=9223372036854775807L;high=-9223372036854775808L;
        	sum=0;long[]a=new long[n];
            for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];
            	if(low>a[i])low=a[i];if(high<a[i])high=a[i];}
            return a;
        }

        Long[]nextLongs(int n){
        	low=9223372036854775807L;high=-9223372036854775808L;
        	sum=0;Long[]a=new Long[n];
            for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];
            	if(high<a[i])high=a[i];if(low>a[i])low=a[i];}
            return a;
        }

        long[]nextSortedlongs(int n){
        	long[]a=nextlongs(n);Arrays.sort(a);return a;
        }

        Long[]nextSortedLongs(int n){
        	Long[]a=nextLongs(n);Arrays.sort(a);return a;
        }

        long[]nextlongSum(int n){
        	long[]a=new long[n];a[0]=nextLong();
        	for(int i=1;i<n;i++)a[i]=nextLong()+a[i-1];
            return a;
        }

        long[]nextSortedlongSum(int n){
        	long[]a=nextSortedlongs(n);
        	for(int i=1;i<n;i++)a[i]+=a[i-1];
        	return a;
        }

        long[][]nextlongMat(int h,int w){
            long[][]a=new long[h][w];
            for(int i=0;i<h;i++)a[i]=nextlongs(w);
            return a;
        }

        long[][]nextlongMatSum(int h,int w){
        	long[][]a=new long[h][w];
        	for(int i=0;i<h;i++)a[i]=nextlongSum(w);
    		for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];
        	return a;
        }

    }
}
