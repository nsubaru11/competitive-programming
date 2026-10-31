import java.io.*;
import java.util.*;
import static java.lang.String.*;
import static java.util.Arrays.*;

public class D {

	public static void main(String[] args) {
		int m = sc.nextInt();
		int n = sc.nextInt();
		int l = sc.nextInt();
		Matrix A = new Matrix(sc.nextIntMat(m, n));
		Matrix B = new Matrix(sc.nextIntMat(n, l));
		out.println(A.multiply(B));
		out.flush();
	}

	private static long GCD(long x, long y) {
		if (x < 0) x *= -1;
		if (y < 0) y *= -1;
		return (y > 0) ? GCD(y, x % y) : x;
	}

	@SuppressWarnings("unused")
	private static class Matrix {
		// 行列 -> 分数の2次元配列
		// m * n次行列
		private Fraction[][] matrix;
		private int m, n, rank;
		private Fraction determinant;

		Matrix(int[][] A) {
			m = A.length; n = A[m - 1].length;
			matrix = new Fraction[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = new Fraction(A[i][j]);
				}
			}
		}

		Matrix(long[][] A) {
			m = A.length; n = A[m - 1].length;
			matrix = new Fraction[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = new Fraction(A[i][j]);
				}
			}
		}

		Matrix(Fraction[][] matrix) {
			m = matrix.length; n = matrix[m - 1].length;
			this.matrix = matrix;
		}

		private void add(Matrix mat) {
			//行列の和
			if (m != mat.m || n != mat.n) {
				throw new ArithmeticException("not defined!");
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = matrix[i][j].add(mat.matrix[i][j]);
				}	
			}
		}

		private void subtract(Matrix mat) {
			//行列の差
			if (m != mat.m || n != mat.n) {
				throw new ArithmeticException("not defined!");
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = matrix[i][j].subtract(mat.matrix[i][j]);
				}
			}
		}

		private Matrix multiply(Matrix mat) {
			//行列の積
			if (n != mat.m) {
				throw new ArithmeticException("not defined!");
			}
			int p = mat.n;
			Matrix AB = new Matrix(new long[m][p]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < p; j++) {
					for (int k = 0; k < n; k++) {
						Fraction product = matrix[i][k].multiply(mat.matrix[k][j]);
						AB.matrix[i][j] = AB.matrix[i][j].add(product);
					}
				}
			}
			return AB;
		}

		private Matrix multiply(Fraction[][] mat) {
			//行列の積
			if (n != mat.length) {
				throw new ArithmeticException("not defined!");
			}
			int p = mat[0].length;
			Matrix AB = new Matrix(new long[m][p]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < p; j++) {
					for (int k = 0; k < n; k++) {
						Fraction product = matrix[i][k].multiply(mat[k][j]);
						AB.matrix[i][j] = AB.matrix[i][j].add(product);
					}
				}
			}
			return AB;
		}

		private Matrix multiply(Fraction k) {
			//スカラー倍
			Matrix kA = new Matrix(new Fraction[m][n]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					kA.matrix[i][j] = matrix[i][j].multiply(k);
				}
			}
			return kA;
		}

		private Matrix multiply(long k) {
			//スカラー倍
			Matrix kA = new Matrix(new Fraction[m][n]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					kA.matrix[i][j] = matrix[i][j].multiply(k);
				}
			}
			return kA;
		}

		private Matrix pow(int k) {
			//行列の累乗
			Matrix ans = identityMat();
			for (Matrix temp = new Matrix(matrix); k > 0; temp = temp.multiply(temp),k >>= 1) {
				if ((k & 1) == 1) ans = ans.multiply(temp);
			}
			return ans;
		}

		private Matrix transpose() {
			//転置行列
			Matrix mat = new Matrix(new Fraction[n][m]);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					mat.matrix[i][j] = matrix[j][i];
				}
			}
			return mat;
		}

		private Fraction det2D() {
			//2次の行列式
			if (m == 2 && n == 2) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]);
				determinant = det.subtract(matrix[0][1].multiply(matrix[1][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Fraction det2D_ex() {
			//2次の拡大係数行列の行列式
			if (m == 2 && n == 3) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]);
				determinant = det.subtract(matrix[0][1].multiply(matrix[1][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 2D_ex!");
			}
		}

		private Fraction det3D() {
			//3次の行列式
			if (m == 3 && n == 3) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2]);
				det = det.add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]));
				det = det.add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]));
				determinant = det.subtract(matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 3D!");
			}
		}

		private Fraction det3D_ex() {
			//3次の拡大係数行列の行列式
			if (m == 3 && n == 4) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2]);
				det = det.add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]));
				det = det.add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]));
				determinant = det.subtract(matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 3D_ex!");
			}
		}

		private Fraction det() {
			//n次の行列式
			if (m == n) {
				if (m == 2) return det2D();
				if (m == 3) return det3D();
				Fraction det = new Fraction(0);
				for (int i = 0; i < m; i++) {
					if (matrix[i][0].equals(new Fraction(0))) continue;
					Fraction[][] minor = new Fraction[m - 1][m - 1];
					for (int k = 0, s = 0; k < m; k++) {
						if (k == i) continue;
						for (int t = 0; t < m - 1; t++) {
							minor[s][t] = matrix[k][t + 1];
						}
						s++;
					}
					Fraction D = matrix[i][0].multiply(LaplaceExpansion(i, m - 1, minor));
					if (i % 2 != 0) {
						det = det.add(D.multiply(-1));
					} else {
						det = det.add(D);
					}
				}
				determinant = det;
				return det;
			} else {
				throw new ArithmeticException("not defined!");
			}
		}

		private Fraction LaplaceExpansion(int i, int m, Fraction[][] matrix) {
			if (m == 3) {
				Matrix mat = new Matrix(matrix);
				return mat.det3D();
			}
			Fraction det = new Fraction(0);
			for (i = 0; i < m; i++) {
				if (matrix[i][0].equals(new Fraction(0))) continue;
				Fraction[][] minor = new Fraction[m - 1][m - 1];
				for (int k = 0, s = 0; k < m; k++) {
					if (k == i) continue;
					for (int t = 0; t < m - 1; t++) {
						minor[s][t] = matrix[k][t + 1];
					}
					s++;
				}
				Fraction D = matrix[i][0].multiply(LaplaceExpansion(i, m - 1, minor));
				if (i % 2 != 0) {
					det = det.add(D.multiply(-1));
				} else {
					det = det.add(D);
				}
			}
			return det;
		}

		private Matrix identityMat() {
			// min(m, n)次の単位行列の作成
			int k = Math.min(m, n);
			int[][] A = new int[k][k];
			for (int i = 0; i < k; i++) {
				A[i][i] = 1;
			}
			Matrix identity = new Matrix(A);
			return identity;
		}

		private Matrix inverse2D() {
			//2次の逆行列
			if (m == 2 && n == 2) {
				Fraction det = det2D();
				Matrix inverse = new Matrix(new Fraction[2][2]);
				inverse.matrix[0][0] = matrix[1][1];
				inverse.matrix[0][1] = matrix[0][1].multiply(-1);
				inverse.matrix[1][1] = matrix[0][0];
				inverse.matrix[1][0] = matrix[1][0].multiply(-1);
				return inverse.multiply(det.getInverse());
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Matrix inverse2D_ex() {
			//2次の拡大係数行列の逆行列
			if (m == 2 && n == 3) {
				Fraction det = det2D_ex();
				Matrix inverse = new Matrix(new Fraction[2][2]);
				inverse.matrix[0][0] = matrix[1][1];
				inverse.matrix[0][1] = matrix[0][1].multiply(-1);
				inverse.matrix[1][1] = matrix[0][0];
				inverse.matrix[1][0] = matrix[1][0].multiply(-1);
				return inverse.multiply(det.getInverse());
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Matrix inverse() {
			//n次の逆行列
			Matrix inverse = identityMat();
			rank = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
							inverse.swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						inverse.multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
							inverse.addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			return inverse;
		}

		private Matrix inverse_ex() {
			//n次の拡大係数行列の連立方程式
			rank = 0;
			Matrix inverse = identityMat();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
							inverse.swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						inverse.multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
							inverse.addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			return inverse;
		}

		private Fraction[] simultaneousEquation() {
			//拡大係数行列の連立方程式
			rank = 0;
			Matrix inverse = identityMat();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			Fraction[] x = new Fraction[rank];
			for (int i = 0; i < rank; i++) {
				x[i] = matrix[i][n - 1];
			}
			return x;
		}

		private void swapRow(int i, int j) {
			//行の入れ替え
			for (int idx = 0; idx < n; idx++) {
				Fraction temp = matrix[i][idx];
				matrix[i][idx] = matrix[j][idx];
				matrix[j][idx] = temp;
			}
		}

		private void addMultiplyRow(int i, int j, long k, long l) {
			//行ｊ を k / l倍して行i に加える
			Fraction f = new Fraction(k, l);
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].add(matrix[j][idx].multiply(f));
			}
		}

		private void addMultiplyRow(int i, int j, Fraction f) {
			//行ｊ を f 倍して行i に加える
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].add(matrix[j][idx].multiply(f));
			}
		}

		private void multiplyRow(int i, long k, long l) {
			//行i を k / l倍
			Fraction f = new Fraction(k, l);
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].multiply(f);
			}
		}

		private void multiplyRow(int i, Fraction f) {
			//行i を f倍
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].multiply(f);
			}
		}

		public boolean equals(Object mat) {
			//二つの行列が等しいかどうか
			return toString().equals(mat.toString());
		}

		public String toString() {
			StringJoiner sj = new StringJoiner("\n");
			for (int i = 0; i < m; i++) {
				StringJoiner sj2 = new StringJoiner(" ");
				for (int j = 0; j < n; j++) {
					sj2.add(matrix[i][j].toString());
				}
				sj.add(sj2.toString());
			}
			return sj.toString();
		}

	}

	@SuppressWarnings("unused")
	private static class Fraction {
		private long numerator;
		private long denominator;

		Fraction(long numerator, long denominator) {
			if (denominator == 0) throw new ArithmeticException(" / by zero");
			this.numerator = numerator;
			this.denominator = denominator;
			reduction();
		}

		Fraction(long numerator) {
			this.numerator = numerator;
			this.denominator = 1;
		}

		Fraction(Fraction f) {
			numerator = f.numerator;
			denominator = f.denominator;
		}

		private void reduction() {
			long gcd =  GCD(numerator, denominator);
			numerator /= gcd;
			denominator /= gcd;
			if (denominator < 0) {
				numerator *= -1L;
				denominator *= -1L;
			}
		}

		private Fraction add(Fraction f) {
			long n = numerator * f.denominator + f.numerator * denominator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction add(long k) {
			long n = numerator + k * denominator;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction subtract(Fraction f) {
			long n = numerator * f.denominator - f.numerator * denominator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction subtract(long k) {
			long n = numerator - k * denominator;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction multiply(Fraction f) {
			long n = numerator * f.numerator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction multiply(long k) {
			long n = numerator * k;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction divide(Fraction f) {
			long n = numerator * f.denominator;
			long d = denominator * f.numerator;
			return new Fraction(n, d);
		}

		private Fraction divide(long k) {
			long n = numerator;
			long d = denominator * k;
			return new Fraction(n, d);
		}

		private void pow(int i) {
			long nmrtr = 1L;
			long dnmntr = 1L;
			while(i > 0) {
				if ((i & 1) == 1) {
					nmrtr *= numerator;
					dnmntr *= denominator;
				}
				numerator *= numerator;
				denominator *= denominator;
				i >>= 1;
			}
			numerator = nmrtr;
			denominator = dnmntr;
		}

		private long getNumerator() {
			return numerator;
		}

		private long getDenominator() {
			return denominator;
		}

		private Fraction getInverse() {
			return new Fraction(denominator, numerator);
		}

		private String getDecimal() {
			double d = (double) numerator / denominator;
			return String.format("%.5f", d);
		}

		public boolean equals(Object f) {
			return toString().equals(f.toString());
		}

		public String toString() {
			if (denominator == 1) {
				return Long.toString(numerator);
			}
			return numerator + "/" + denominator;
		}
	}

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	@SuppressWarnings("unused")
	private static class FastScanner {
		private static final BufferedInputStream reader=new BufferedInputStream(System.in);
		private static final byte[]buffer=new byte[0x10000];
		private static int i=0,len=0;private long sum,low,high;

		public int read(){
			if(i==len){i=0;try{len=reader.read(buffer);}catch(IOException ignored){}
			if(len==-1)return 0;}if(len<=i)throw new RuntimeException();return buffer[i++];}

		String next(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b<'!'||'~'<b){b=read();}
			while('!'<=b&&b<='~'){sb.appendCodePoint(b);b=read();}return sb.toString();}

		String nextLine(){
			StringBuilder sb=new StringBuilder();
			int b=read();while(b!=0&&b!='\r'&&b!='\n'){sb.appendCodePoint(b);b=read();}
			if(b=='\r')read();return sb.toString();}

		int nextInt(){return(int)nextLong();}

		long nextLong(){
			int b=read(),c=0;while(b<'!'||'~'<b){b=read();}
			boolean neg=false;if(b=='-'){neg=true;b=read();}
			long n=0;while('0'<=b&&b<='9'){n=n*10+b-'0';b=read();c++;}
			if(c==0||c>=2&&n==0)throw new NumberFormatException();return neg?-n:n;}

		double nextDouble(){return Double.parseDouble(next());}

		String[]nextStrings(int n){String[]s=new String[n];setAll(s,x->next());return s;}

		String[][]nextStringMat(int n,int m){
			String[][]s=new String[n][m];setAll(s,x->nextStrings(m));return s;}

		char[]nextChars(){return next().toCharArray();}

		char[]nextChars(int n){char[]c=new char[n];for(int i=0;i<n;i++)c[i]=next().charAt(0);return c;}

		char[]nextSortedChars(){char[]c=nextChars();sort(c);return c;}

		char[]nextSortedChars(int n){char[]c=nextChars(n);sort(c);return c;}

		char[][]nextCharMat(int n){char[][]c=new char[n][];setAll(c,x->nextChars());return c;}

		char[][]nextCharMat(int n,int m){char[][]c=new char[n][m];setAll(c,x->nextChars(m));return c;}

		int[]nextInts(int n){
			low=0x7fffffff;high=0x80000000;sum=0;int[]a=new int[n];
			for(int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Integer[]nextIntegers(int n){
			low=0x7fffffff;high=0x80000000;sum=0;Integer[]a=new Integer[n];
			for (int i=0;i<n;i++){a[i]=nextInt();sum+=a[i];
			if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		int[]nextSortedInts(int n){int[]a=nextInts(n);sort(a);return a;}

		Integer[]nextSortedIntegers(int n){Integer[]a=nextIntegers(n);sort(a);return a;}

		int[]nextIntSum(int n){int[]a=new int[n];a[0]=nextInt();low=a[0];high=a[0];for(int i=1;i<n;i++)
		{a[i]=nextInt();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		int[]nextSortedIntSum(int n){int[]a=nextSortedInts(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		int[][]nextIntMat(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextInts(w));return a;}

		int[][]nextIntMatSum(int h,int w){int[][]a=new int[h][w];setAll(a,x->nextIntSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

		long[]nextlongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;long[]a=new long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		Long[]nextLongs(int n){low=0x7fffffffffffffffL;high=0x8000000000000000L;sum=0;Long[]a=new Long[n];
		for(int i=0;i<n;i++){a[i]=nextLong();sum+=a[i];if(low>a[i])low=a[i];if(high<a[i])high=a[i];}return a;}

		long[]nextSortedlongs(int n){long[]a=nextlongs(n);sort(a);return a;}

		Long[]nextSortedLongs(int n){Long[]a=nextLongs(n);sort(a);return a;}

		long[]nextlongSum(int n){long[]a=new long[n];a[0]=nextLong();low=a[0];high=a[0];for(int i=1;i<n;i++){
			a[i]=nextLong();if(low>a[i])low=a[i];if(high<a[i])high=a[i];a[i]+=a[i-1];}sum=a[n-1];return a;}

		long[]nextSortedlongSum(int n){long[]a=nextSortedlongs(n);for(int i=1;i<n;i++)a[i]+=a[i-1];return a;}

		long[][]nextlongMat(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongs(w));return a;}

		long[][]nextlongMatSum(int h,int w){long[][]a=new long[h][w];setAll(a,x->nextlongSum(w));
			for(int i=1;i<h;i++)for(int j=0;j<w;j++)a[i][j]+=a[i-1][j];return a;}

	}
	@SuppressWarnings("unused")
	private static class FastPrinter {
		private static final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out));

		void println(){out.println();}

		void println(Object o){out.println(o);}

		void println(String s){out.println(s);}

		void println(boolean f){out.println(f);}

		void println(char c){out.println(c);}

		void println(int a){out.println(a);}

		void println(long a){out.println(a);}

		void println(double a){out.println(a);}

		void println(Object...o){
			StringJoiner sj=new StringJoiner("\n");for(Object x:o)sj.add(valueOf(x));out.println(sj);}

		void println(char[]c){
			StringJoiner sj=new StringJoiner("\n");for(char x:c)sj.add(valueOf(x));out.println(sj);}

		void println(int[]a){StringJoiner sj=new StringJoiner("\n");
			for(int b:a)sj.add(Integer.toString(b));out.println(sj);}

		void println(long[]a){StringJoiner sj=new StringJoiner("\n");
			for(long b:a)sj.add(Long.toString(b));out.println(sj);}

		void println(Integer[]a){StringJoiner sj=new StringJoiner("\n");
		for(int b:a)sj.add(Integer.toString(b));out.println(sj);}

		void println(Long[]a){StringJoiner sj=new StringJoiner("\n");
		for(long b:a)sj.add(Long.toString(b));out.println(sj);}

		void println(Object[][]o){StringJoiner sj=new StringJoiner("\n");for(Object[]x:o){
			StringJoiner sj2=new StringJoiner(" ");for(Object y:x)sj2.add(valueOf(y));
			sj.add(sj2.toString());}out.println(sj);}

		void println(char[][]c){StringJoiner sj=new StringJoiner("\n");for(char[]x:c){
			StringJoiner sj2=new StringJoiner(" ");for(char y:x)sj2.add(valueOf(y));
			sj.add(sj2.toString());}out.println(sj);}

		void println(int[][]a){StringJoiner sj=new StringJoiner("\n");for(int[]b:a){
			StringJoiner sj2=new StringJoiner(" ");for(int c:b)sj2.add(Integer.toString(c));
			sj.add(sj2.toString());}out.println(sj);}

		void println(long[][]a){StringJoiner sj=new StringJoiner("\n");for(long[]b:a){
			StringJoiner sj2=new StringJoiner(" ");for(long c:b)sj2.add(Long.toString(c));
			sj.add(sj2.toString());}out.println(sj);}

		void print(Object o){out.print(o);}

		void print(String s){out.print(s);}

		void print(boolean f){out.print(f);}

		void print(char c){out.print(c);}

		void print(int a){out.print(a);}

		void print(long a){out.print(a);}

		void print(double a){out.print(a);}

		void print(Object...o){
			StringJoiner sj=new StringJoiner(" ");for(Object x:o)sj.add(valueOf(x));out.println(sj);}

		void print(char[]c){
			StringJoiner sj=new StringJoiner(" ");for(char x:c)sj.add(valueOf(x));out.println(sj);}

		void print(int[]a){
			StringJoiner sj=new StringJoiner(" ");for(int x:a)sj.add(Integer.toString(x));out.println(sj);}

		void print(long[]a){
			StringJoiner sj=new StringJoiner(" ");for(long x:a)sj.add(Long.toString(x));out.println(sj);}

		void print(Integer[]a){
			StringJoiner sj=new StringJoiner(" ");for(int x:a)sj.add(Integer.toString(x));out.println(sj);}

		void print(Long[]a){
			StringJoiner sj=new StringJoiner(" ");for(long x:a)sj.add(Long.toString(x));out.println(sj);}

		void printf(String frmt,Object...args){out.printf(frmt,args);}

		void printf(Locale l,String frmt,Object...args){out.printf(l,frmt,args);}

		void write(Object o){out.write(valueOf(o));}

		void write(String s){out.write(s);}

		void write(int a){out.write(Integer.toString(a));}

		void write(char c){out.write(valueOf(c));}

		void write(long a){out.write(Long.toString(a));}

		void writeln(){out.write("\n");}

		void writeln(Object o){out.write(valueOf(o));writeln();}

		void writeln(String s){out.write(s);writeln();}

		void writeln(int a){out.write(Integer.toString(a));writeln();}

		void writeln(char c){out.write(valueOf(c));writeln();}

		void writeln(long a){out.write(Long.toString(a));writeln();}

		void flush(){out.flush();}

		void close(){out.close();}

	}
}
