import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Arrays.*;
import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.MIN_VALUE;
import static java.lang.Math.*;

public class B {

	public static void main(String[] args) {
		long a = sc.nextLong();
		long m = sc.nextLong();
		long l = sc.nextLong() - a;
		long r = sc.nextLong() - a;
		System.out.println(MathFn.floorLong(r, m) - MathFn.ceilLong(l, m) + 1);
	}

	private static final FastScanner sc = new FastScanner();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buf=new byte[1<<17];
		private static int pos=0,cnt=0;private static long sum,low,high;

		public byte read(){if(pos==cnt){try{cnt=reader.read(buf,pos=0,1<<17);}
			catch(IOException ignored){}}if(cnt<0)return 0;return buf[pos++];}

		public char nextChar(){byte b=read();while(b<'!'||'~'<b)b=read();return(char)b;}

		public String next(){return nextSb().toString();}

		public StringBuilder nextSb(){StringBuilder sb=new StringBuilder();
			int b=read();while(b<'!'||'~'<b)b=read();while('!'<=b&&b<='~'){
				sb.appendCodePoint(b);b=read();}return sb;}

		public String nextLine(){StringBuilder sb=new StringBuilder();
			int b=read();while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
			if(b=='\r')read();return sb.toString();}

		public int nextInt(){int b=nextChar();boolean neg=b=='-';if(neg)b=read();
			int n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();}return neg?-n:n;}

		public long nextLong(){int b=nextChar();boolean neg=b=='-';if(neg)b=read();
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();}return neg?-n:n;}

		public int nextIntRadix(int radix){return Integer.parseInt(next(),radix);}

		public long nextLongRadix(int radix){return Long.parseLong(next(),radix);}

		public BigInteger nextBigInteger(){return new BigInteger(next());}

		public BigInteger nextBigInteger(int radix){return new BigInteger(next(),radix);}

		public double nextDouble(){return Double.parseDouble(next());}

		public String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		public String[]nextSortedStrings(int n){String[]s=nextStrings(n);sort(s);return s;}

		public String[][]nextStringMat(int n,int m){String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		public char nextCharAt(int i){return next().charAt(i);}

		public char[]nextChars(){return next().toCharArray();}

		public char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=nextChar();return c;}

		public char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		public char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		public char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		public char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		public int[]nextInt(int n){int[]a=new int[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public Integer[]nextInts(int n){Integer[]a=new Integer[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextInt());return a;}

		public int[]nextSortedInt(int n){int[]a=nextInt(n);sort(a);return a;}

		public Integer[]nextSortedInts(int n){Integer[]a=nextInts(n);sort(a);return a;}

		public int[]nextIntSum(int n){int[]a=new int[n];resetStats();updateStats(a[0]=nextInt());
			for(int i=1;i<n;i++){updateStats(a[i]=nextInt());a[i]+=a[i-1];}return a;}

		public int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInt(w));return a;}

		public int[][]nextIntMatSum(int h,int w){int[][]a=new int[h+1][w+1];for(int i=1;i<=h;i++)
			for(int j=1;j<=w;j++)a[i][j]=nextInt()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public int[][][]nextInt3D(int x,int y,int z){
			int[][][]a=new int[x][y][z];setAll(a,b->nextIntMat(y,z));return a;}

		public int[][][]nextIntSum3D(int x,int y,int z){int[][][]e=new int[x+1][y+1][z+1];for(int a=1;a<=x;a++)
			for(int b=1;b<=y;b++)for(int c=1;c<=z;c++)e[a][b][c]=nextInt()+e[a-1][b][c]+e[a][b-1][c]
					+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1]-e[a][b-1][c-1]+e[a-1][b-1][c-1];return e;}

		public long[]nextLong(int n){long[]a=new long[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public Long[]nextLongs(int n){Long[]a=new Long[n];resetStats();
			for(int i=0;i<n;i++)updateStats(a[i]=nextLong());return a;}

		public long[]nextSortedLong(int n){long[]a=nextLong(n);sort(a);return a;}

		public Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		public long[]nextLongSum(int n){long[]a=new long[n];resetStats();updateStats(a[0]=nextLong());
			for(int i=1;i<n;i++){updateStats(a[i]=nextLong());a[i]+=a[i-1];}return a;}

		public long[][]nextLongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextLong(w));return a;}

		public long[][]nextLongMatSum(int h,int w){long[][]a=new long[h+1][w+1];for(int i=1;i<=h;i++)
			for(int j=1;j<=w;j++)a[i][j]=nextLong()+a[i-1][j]+a[i][j-1]-a[i-1][j-1];return a;}

		public long[][][]nextLong3D(int x,int y,int z){
			long[][][]a=new long[x][y][z];setAll(a,b->nextLongMat(y,z));return a;}

		public long[][][]nextLongSum3D(int x,int y,int z){long[][][]e=new long[x+1][y+1][z+1];
			for(int a=1;a<=x;a++)for(int b=1;b<=y;b++)for(int c=1;c<=z;c++)e[a][b][c]=nextLong()+e[a-1][b][c]
					+e[a][b-1][c]+e[a][b][c-1]-e[a-1][b-1][c]-e[a-1][b][c-1]-e[a][b-1][c-1]+e[a-1][b-1][c-1];return e;}

		private<T extends Collection<Integer>>T nextIntCollection(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){int a=nextInt();c.add(a);updateStats(a);}return c;}

		public ArrayList<Integer>nextIntAL(int n){return nextIntCollection(n,ArrayList::new);}

		public LinkedList<Integer>nextIntLL(int n){return nextIntCollection(n,LinkedList::new);}

		public HashSet<Integer>nextIntHS(int n){return nextIntCollection(n,HashSet::new);}

		public LinkedHashSet<Integer>nextIntLHS(int n){return nextIntCollection(n,LinkedHashSet::new);}

		public TreeSet<Integer>nextIntTS(int n){return nextIntCollection(n,TreeSet::new);}

		private<T extends Collection<Long>>T nextLongCollection(int n,Supplier<T>s){
			T c=s.get();resetStats();while(n-->0){long a=nextLong();c.add(a);updateStats(a);}return c;}

		public ArrayList<Long>nextLongAL(int n){return nextLongCollection(n,ArrayList::new);}

		public LinkedList<Long>nextLongLL(int n){return nextLongCollection(n,LinkedList::new);}

		public HashSet<Long>nextLongHS(int n){return nextLongCollection(n,HashSet::new);}

		public LinkedHashSet<Long>nextLongLHS(int n){return nextLongCollection(n,LinkedHashSet::new);}

		public TreeSet<Long>nextLongTS(int n){return nextLongCollection(n,TreeSet::new);}

		private<T extends Collection<String>>T nextStringCollection(int n,Supplier<T>s){
			T c=s.get();while(n-->0){c.add(next());}return c;}

		public ArrayList<String>nextStringAL(int n){return nextStringCollection(n,ArrayList::new);}

		public LinkedList<String>nextStringLL(int n){return nextStringCollection(n,LinkedList::new);}

		public HashSet<String>nextStringHS(int n){return nextStringCollection(n,HashSet::new);}

		public LinkedHashSet<String>nextStringLHS(int n){return nextStringCollection(n,LinkedHashSet::new);}

		public TreeSet<String>nextStringTS(int n){return nextStringCollection(n,TreeSet::new);}

		private<T extends Collection<Character>>T nextCharacterCollection(int n,Supplier<T>s){
			T c=s.get();while(n-->0){c.add(nextChar());}return c;}

		public ArrayList<Character>nextCharacterAL(int n){return nextCharacterCollection(n,ArrayList::new);}

		public LinkedList<Character>nextCharacterLL(int n){return nextCharacterCollection(n,LinkedList::new);}

		public HashSet<Character>nextCharacterHS(int n){return nextCharacterCollection(n,HashSet::new);}

		public LinkedHashSet<Character>nextCharacterLHS(int n){return nextCharacterCollection(n,LinkedHashSet::new);}

		public TreeSet<Character>nextCharacterTS(int n){return nextCharacterCollection(n,TreeSet::new);}

		private<T extends Map<Integer,Integer>>T nextIntMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){int a=nextInt();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Integer,Integer>nextIntMultisetHM(int n){return nextIntMultiset(n,HashMap::new);}

		public LinkedHashMap<Integer,Integer>nextIntMultisetLHM(int n){return nextIntMultiset(n,LinkedHashMap::new);}

		public TreeMap<Integer,Integer>nextIntMultisetTM(int n){return nextIntMultiset(n,TreeMap::new);}

		private<T extends Map<Long,Integer>>T nextLongMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){long a=nextLong();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Long,Integer>nextLongMultisetHM(int n){return nextLongMultiset(n,HashMap::new);}

		public LinkedHashMap<Long,Integer>nextLongMultisetLHM(int n){return nextLongMultiset(n,LinkedHashMap::new);}

		public TreeMap<Long,Integer>nextLongMultisetTM(int n){return nextLongMultiset(n,TreeMap::new);}

		private<T extends Map<String,Integer>>T nextStringMultiset(int n,Supplier<T>s){
			T c=s.get();while(n-->0){String a=next();c.put(a,c.getOrDefault(a,0)+1);}return c;}

		public HashMap<String,Integer>nextStringMultisetHM(int n){return nextStringMultiset(n,HashMap::new);}

		public LinkedHashMap<String,Integer>nextStringMultisetLHM(int n){return nextStringMultiset(n,LinkedHashMap::new);}

		public TreeMap<String,Integer>nextStringMultisetTM(int n){return nextStringMultiset(n,TreeMap::new);}

		private<T extends Map<Character,Integer>>T nextCharMultiset(int n,Supplier<T>s){T c=s.get();
			resetStats();while(n-->0){char a=nextChar();c.put(a,c.getOrDefault(a,0)+1);updateStats(a);}return c;}

		public HashMap<Character,Integer>nextCharMultisetHM(int n){return nextCharMultiset(n,HashMap::new);}

		public LinkedHashMap<Character,Integer>nextCharacterMultisetLHM(int n){return nextCharMultiset(n,LinkedHashMap::new);}

		public TreeMap<Character,Integer>nextCharMultisetTM(int n){return nextCharMultiset(n,TreeMap::new);}

		public long getLowestNum(){return low;}

		public long getHighestNum(){return high;}

		public long getSum(){return sum;}

		private static void resetStats(){low=MAX_VALUE;high=MIN_VALUE;sum=0;}

		private static void updateStats(long a){sum+=a;low=min(low,a);high=max(high,a);}

	}
}

class MathFn {

	/**
	 * a / b以下で最大の長整数を返します。
	 *
	 * @param a 割られる値(long)
	 * @param b 割る値(long)
	 * @return ⌊a / b⌋
	 */
	public static long floorLong(long a, long b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	/**
	 * a / b以下で最大の整数を返します。
	 *
	 * @param a 割られる値(int)
	 * @param b 割る値(int)
	 * @return ⌊a / b⌋
	 */
	public static int floorInt(int a, int b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	/**
	 * a / b以上で最大の長整数を返します。
	 *
	 * @param a 割られる値(long)
	 * @param b 割る値(long)
	 * @return ⌈a / b⌉
	 */
	public static long ceilLong(long a, long b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	/**
	 * a / b以上で最大の整数を返します。
	 *
	 * @param a 割られる値(int)
	 * @param b 割る値(int)
	 * @return ⌈a / b⌉
	 */
	public static long ceilInt(int a, int b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	/**
	 * doubleの高速なフォーマット。小数点第n - 1位を四捨五入します。
	 *
	 * @param x フォーマットする対象
	 * @param n 少数点以下の桁数
	 * @return String
	 */
	public static String formatDouble(double x, int n) {
		StringBuilder sb = new StringBuilder();
		if (n == 0) return sb.append(round(x)).toString();
		if (x < 0) {
			sb.append("-");
			x = -x;
		}
		x += pow(10, -n) / 2;
		sb.append((long) x).append(".");
		x -= (long) x;
		while (n-- > 0) {
			x *= 10;
			sb.append((int) x);
			x -= (int) x;
		}
		return sb.toString();
	}

	/**
	 * a ^ b % mod を求めます。
	 *
	 * @param a   底
	 * @param b   指数
	 * @param mod 割る値(素数)
	 * @return a ^ b % mod
	 */
	public static double modPow(long a, long b, long mod) {
		long ans = 1;
		boolean f = b < 0;
		if (f) b = -b;
		b %= mod - 1;
		for (; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1)
				ans = ans * a % mod;
		}
		return f ? (double) 1 / ans : ans;
	}

	/**
	 * aのb乗を求めます
	 *
	 * @param a 底
	 * @param b 指数
	 * @return a ^ b
	 */
	public static double pow(double a, long b) {
		double ans = 1;
		boolean f = b < 0;
		if (f) b = -b;
		for (; b > 0; a *= a, b >>= 1) {
			if ((b & 1) == 1)
				ans *= a;
		}
		return f ? (double) 1 / ans : ans;
	}

	/**
	 * nが20以下の階乗を返します。
	 *
	 * @param n int
	 * @return n!
	 */
	public static long factorial_max20(int n) {
		long[] SmallFactorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
				39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L,
				20922789888000L, 355687428096000L, 6402373705728000L,
				121645100408832000L, 2432902008176640000L};
		return SmallFactorials[n];
	}

	/**
	 * nの階乗をmodで割った余りを計算します。
	 *
	 * @param n int
	 * @param mod long
	 * @return n! % mod
	 */
	public static long modFactorial(int n, long mod) {
		if (n <= 20) return factorial_max20(n) % mod;
		if (n >= mod) return 0;
		if (n == mod - 1) return n;
		int twoExponent = n - Integer.bitCount(n);
		long[] oddPart = new long[(n >> 1) + 1];
		oddPart[0] = 1;
		for (int i = 3; i <= n; i += 2) {
			int idx = i >> 1;
			oddPart[idx] = oddPart[idx - 1] * i % mod;
		}
		long ans = oddPart[(n - 1) >> 1];
		for (int i = 1; n >> i > 1; i++) {
			int k = n;
			if ((k >>= i) % 2 == 1) {
				ans = ans * oddPart[k >> 1] % mod;
			} else {
				ans = ans * oddPart[(k - 1) >> 1] % mod;
			}
		}
		long twoExp = (1L << 62) % mod;
		while (twoExponent > 62) {
			ans = ans * twoExp % mod;
			twoExponent -= 62;
		}
		return ans * ((1L << twoExponent) % mod) % mod;
	}

	/**
	 * 平方数かどうかの判定
	 *
	 * @param s 判定する対象
	 * @return boolean
	 */
	public static boolean isSquareNum(long s) {
		long sqrt = round(sqrt(s));
		return s == sqrt * sqrt;
	}

	/**
	 * 立方数かどうかの判定
	 *
	 * @param c 判定する対象
	 * @return boolean
	 */
	public static boolean isCubicNum(long c) {
		long cbrt = round(cbrt(c));
		return c == cbrt * cbrt * cbrt;
	}

	/**
	 * nCrを求めます。
	 *
	 * @param n 二項係数を求めるのに用いる値
	 * @param r 二項係数を求めるのに用いる値
	 * @return nCr
	 */
	public static long comb(long n, long r) {
		if (n < 0 || r < 0) return 0;
		long ans = 1;
		r = min(n - r, r);
		for (int i = 1; i <= r; i++) {
			ans *= n - i;
			ans /= i + 1;
		}
		return ans;
	}

	/**
	 * nCrをmodで割った余りを求めます。
	 *
	 * @param n 二項係数を求めるのに用いる値
	 * @param r 二項係数を求めるのに用いる値
	 * @param mod 法とする整数
	 * @return nCr % mod
	 */
	public static long modComb(long n, long r, long mod) {
		if (n < 0 || r < 0) return 0;
		long ans = 1;
		r = min(n - r, r);
		for (int i = 0; i < r; i++) {
			ans = ans * (n - i) % mod;
			ans = ans * (long) modPow(i + 1, mod - 2, mod) % mod;
		}
		return ans;
	}

	public static long isCrossed(int Ax1, int Ay1, int Ax2, int Ay2, int Bx1, int By1, int Bx2, int By2) {
		return 1;
	}

	/**
	 * 最小公倍数を求めます
	 *
	 * @param x long
	 * @param y long
	 * @return x * (y / GCD(x, y))
	 */
	public static long LCM(long x, long y) {
		return x == 0 || y == 0 ? 0 : x * (y / GCD(x, y));
	}

	/**
	 * 最大公約数を求めます
	 *
	 * @param x long
	 * @param y long
	 * @return GCD(x, y)
	 */
	public static long GCD(long x, long y) {
		return y > 0 ? GCD(y, x % y) : x;
	}

	/**
	 * ax + by = GCD(x, y)となるx, yの組を見つけます。
	 *
	 * @param a long
	 * @param b long
	 * @param x AtomicLong
	 * @param y AtomicLong
	 * @return |x| + |y|の最小値
	 */
	public static long exGCD(long a, long b, AtomicLong x, AtomicLong y) {
		if (b == 0) {
			x.set(1);
			y.set(0);
			return a;
		}
		AtomicLong xx = new AtomicLong(), yy = new AtomicLong();
		long d = exGCD(b, a % b, yy, xx);
		y.set(yy.get() - (a / b) * xx.get());
		x.set(xx.get());
		return d;
	}

	/**
	 * オイラーのトーシェント関数に基づき1からnまでのnと互いに素な数字の個数を調べます。
	 *
	 * @param n long
	 * @return long
	 */
	public static long EulerTotientFunction(long n) {
		long sum = 1;
		for (int i = 2; i <= 3; i++) {
			long temp = 1;
			while (n % i == 0) {
				n /= i;
				temp *= i;
			}
			sum *= temp - temp / i;
		}
		for (int i = 5; (long) i * i <= n; i += 6) {
			for (int j = i; j <= i + 2; j += 2) {
				if (n % j == 0) {
					long temp = 1;
					while (n % j == 0) {
						n /= j;
						temp *= j;
					}
					sum *= temp - temp / j;
				}
			}
		}
		return n == 1 ? sum : sum * (n - 1);
	}

}