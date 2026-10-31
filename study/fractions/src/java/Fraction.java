import java.math.BigDecimal;
import java.util.Objects;

/**
 * 分数を表すクラス。 四則演算、比較、変換などの操作を提供します。
 */
public class Fraction implements Comparable<Fraction> {
	private long numer;
	private long denom;

	// 1. コンストラクタ, valueOf
	/**
	 * 分子と分母を指定して分数を作成します。 分母に0を指定すると例外が発生します。
	 * 
	 * @param n 分子
	 * @param d 分母 (0不可)
	 * @throws ArithmeticException 分母が0の場合
	 */
	public Fraction(long n, long d) {
		if (d == 0)
			throw new ArithmeticException(" / by zero");
		numer = n;
		denom = d;
		reduceFraction();
	}

	/**
	 * 整数を分子とし、分母を1とする分数を作成します。
	 * 
	 * @param l 整数
	 */
	public Fraction(long l) {
		this(l, 1);
	}

	/**
	 * 実数の分子と分母から分数を作成します。
	 * 
	 * @param n 分子（double）
	 * @param d 分母（double）
	 */
	public Fraction(double n, double d) {
		this(n / d);
	}

	/**
	 * 実数を最も近い分数に変換して作成します。
	 * 
	 * @param d 実数
	 */
	public Fraction(double d) {
		String s = BigDecimal.valueOf(d).toPlainString();
		int decimalPlace = s.contains(".") ? s.length() - s.indexOf(".") - 1 : 0;
		denom = (long) Math.pow(10, decimalPlace);
		numer = (long) (Double.parseDouble(s) * denom);
		reduceFraction();
	}

	/**
	 * 既存の分数オブジェクトをコピーして新しい分数を作成します。
	 * 
	 * @param f コピー元の分数
	 */
	public Fraction(Fraction f) {
		this(f.numer, f.denom);
	}

	/**
	 * 分子と分母を指定して新しいFractionを作成します。
	 * 
	 * @param n 分子
	 * @param d 分母（0不可）
	 * @return 作成されたFraction
	 */
	public static Fraction valueOf(long n, long d) {
		return new Fraction(n, d);
	}

	/**
	 * 整数を分子とし、分母を1とするFractionを作成します。
	 * 
	 * @param l 整数
	 * @return 作成されたFraction
	 */
	public static Fraction valueOf(long l) {
		return new Fraction(l);
	}

	/**
	 * 実数の分子と分母から分数を作成します。
	 * 
	 * @param n 分子（double）
	 * @param d 分母（double）
	 * @return 作成されたFraction
	 */
	public static Fraction valueOf(double n, double d) {
		return new Fraction(n, d);
	}

	/**
	 * 実数を最も近いFractionに変換します。
	 * 
	 * @param d 実数
	 * @return 作成されたFraction
	 */
	public static Fraction valueOf(double d) {
		return new Fraction(d);
	}

	// 2. 値の取得
	/**
	 * 分子を取得します。
	 * 
	 * @return 分子
	 */
	public long numerator() {
		return numer;
	}

	/**
	 * 分母を取得します。
	 * 
	 * @return 分母
	 */
	public long denominator() {
		return denom;
	}

	// 3. 四則演算, 累乗
	/**
	 * 整数を加算した新しいFractionを返します。
	 * 
	 * @param l 加算する整数
	 * @return 計算後のFraction
	 */
	public Fraction add(long l) {
		long n = numer + l * denom;
		long d = denom;
		return new Fraction(n, d);
	}

	/**
	 * 実数を加算した新しいFractionを返します。
	 * 
	 * @param d 加算する実数
	 * @return 計算後のFraction
	 */
	public Fraction add(double d) {
		return add(Fraction.valueOf(d));
	}

	/**
	 * 分数を加算した新しいFractionを返します。
	 * 
	 * @param f 加算する分数
	 * @return 計算後のFraction
	 */
	public Fraction add(Fraction f) {
		long n = numer * f.denom + f.numer * denom;
		long d = denom * f.denom;
		return new Fraction(n, d);
	}

	/**
	 * 整数を減算した新しいFractionを返します。
	 * 
	 * @param l 減算する整数
	 * @return 計算後のFraction
	 */
	public Fraction sub(long l) {
		long n = numer - l * denom;
		long d = denom;
		return new Fraction(n, d);
	}

	/**
	 * 実数を減算した新しいFractionを返します。
	 * 
	 * @param d 減算する実数
	 * @return 計算後のFraction
	 */
	public Fraction sub(double d) {
		return sub(Fraction.valueOf(d));
	}

	/**
	 * 分数を減算した新しいFractionを返します。
	 * 
	 * @param f 減算する分数
	 * @return 計算後のFraction
	 */
	public Fraction sub(Fraction f) {
		long n = numer * f.denom - f.numer * denom;
		long d = denom * f.denom;
		return new Fraction(n, d);
	}

	/**
	 * 整数を乗算した新しいFractionを返します。
	 * 
	 * @param l 乗算する整数
	 * @return 計算後のFraction
	 */
	public Fraction mul(long l) {
		long n = numer * l;
		long d = denom;
		return new Fraction(n, d);
	}

	/**
	 * 実数を乗算した新しいFractionを返します。
	 * 
	 * @param d 乗算する実数
	 * @return 計算後のFraction
	 */
	public Fraction mul(double d) {
		return mul(Fraction.valueOf(d));
	}

	/**
	 * 分数を乗算した新しいFractionを返します。
	 * 
	 * @param f 乗算する分数
	 * @return 計算後のFraction
	 */
	public Fraction mul(Fraction f) {
		long n = numer * f.numer;
		long d = denom * f.denom;
		return new Fraction(n, d);
	}

	/**
	 * 整数を除算した新しいFractionを返します。
	 * 
	 * @param l 除算する整数
	 * @return 計算後のFraction
	 */
	public Fraction div(long l) {
		long n = numer;
		long d = denom * l;
		return new Fraction(n, d);
	}

	/**
	 * 実数を除算した新しいFractionを返します。
	 * 
	 * @param d 除算する実数
	 * @return 計算後のFraction
	 */
	public Fraction div(double d) {
		return div(Fraction.valueOf(d));
	}

	/**
	 * 分数を除算した新しいFractionを返します。
	 * 
	 * @param f 除算する分数
	 * @return 計算後のFraction
	 */
	public Fraction div(Fraction f) {
		long n = numer * f.denom;
		long d = denom * f.numer;
		return new Fraction(n, d);
	}

	/**
	 * 分数をi乗した値を返します。
	 * 
	 * @param i 指数部分
	 * @return 分数
	 */
	public Fraction pow(int i) {
		boolean neg = false;
		if (i < 0) {
			neg = true;
			i *= -1;
		}
		long numer = 1, denom = 1;
		for (long n = this.numer, d = this.denom; i > 0; i >>= 1) {
			if ((i & 1) == 1) {
				numer *= n;
				denom *= d;
			}
			n *= n;
			d *= d;
		}
		return neg ? new Fraction(denom, numer) : new Fraction(numer, denom);
	}

	// 4. 比較, 判定
	public int compareTo(Fraction f) {
		return Long.compare(numer * f.denom, denom * f.numer);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Fraction))
			return false;
		Fraction other = (Fraction) obj;
		return numer == other.numer && denom == other.denom;
	}

	public int hashCode() {
		return Objects.hash(numer, denom);
	}

	/**
	 * 0かどうか返します。
	 * 
	 * @return 0ならtrue、そうでなければfalse
	 */
	public boolean isZero() {
		return numer == 0;
	}

	/**
	 * 1かどうか返します。
	 * 
	 * @return 1ならtrue、そうでなければfalse
	 */
	public boolean isOne() {
		return numer == denom;
	}

	/**
	 * 整数かどうか返します。
	 * 
	 * @return 整数ならtrue、そうでなければfalse
	 */
	public boolean isInteger() {
		return denom == 1;
	}

	/**
	 * 真分数をかどうか判定します。
	 * 
	 * @return 真分数ならtrue、そうでなければfalse
	 */
	public boolean isProper() {
		return Math.abs(numer) < denom;
	}

	/**
	 * 仮分数をかどうか判定します。
	 * 
	 * @return 仮分数ならtrue、そうでなければfalse
	 */
	public boolean isImproper() {
		return Math.abs(numer) >= denom;
	}

	// 6. 整数, 小数変換
	/**
	 * 指定した分数の逆数を取得します。
	 * 
	 * @return 逆数のFraction
	 */
	public Fraction inverse() {
		return new Fraction(denom, numer);
	}

	/**
	 * 符号を反転したFractionを返します。
	 * 
	 * @return 反転後のFraction
	 */
	public Fraction negate() {
		return new Fraction(-numer, denom);
	}

	/**
	 * 分数の小数部分（真分数部分）を取得します。
	 * 
	 * @return 真分数部分のFraction
	 */
	public Fraction fractionPart() {
		return new Fraction(Math.abs(numer) % denom, denom);
	}

	/**
	 * 分数の整数部分を取得します。
	 * 
	 * @return 整数部分
	 */
	public long integerPart() {
		return numer / denom;
	}

	/**
	 * この値以下で最大の整数を取得します。
	 *
	 * @return 整数
	 */
	public long floor() {
		return numer < 0 ? (numer - denom + 1) / denom : numer / denom;
	}

	/**
	 * この値以上で最小の整数を取得します。
	 *
	 * @return 整数
	 */
	public long ceil() {
		return numer < 0 ? numer / denom : (numer + denom - 1) / denom;
	}

	/**
	 * 分数を小数第一位で四捨五入した整数を取得します。
	 * 
	 * @return 整数
	 */
	public long round() {
		return Math.round(getDecimal());
	}

	/**
	 * 分数を小数にして返します。
	 * 
	 * @return 小数
	 */
	public double getDecimal() {
		return (double) numer / denom;
	}

	// 7. 文字列変換
	public String toString() {
		return denom == 1 ? Long.toString(numer) : numer + "/" + denom;
	}

	/**
	 * 帯分数表記で返します。
	 * 
	 * @return 帯分数の文字列
	 */
	public String toMixedString() {
		long intPart = numer / denom;
		long fracNumer = numer % denom;
		return fracNumer == 0 ? Long.toString(intPart) : intPart + " " + Math.abs(fracNumer) + "/" + denom;
	}

	/**
	 * 分数を小数表記にして返します。
	 * 
	 * @param precision 小数点下位桁数
	 * @return 小数表記
	 */
	public String toDecimalString(int precision) {
		return String.format("%." + precision + "f", getDecimal());
	}

	/**
	 * パーセンテージ表記で返します。
	 * 
	 * @param precision 小数点下位桁数
	 * @return パーセンテージ表記文字列
	 */
	public String toPercent(int precision) {
		return String.format("%." + precision + "f%%", getDecimal() * 100);
	}

	// 8. staticユーティリティ
	/**
	 * 2つの分数のうち小さい方を返します。
	 * 
	 * @param a 分数
	 * @param b 分数
	 * @return
	 */
	public static Fraction min(Fraction a, Fraction b) {
		return a.compareTo(b) <= 0 ? a : b;
	}

	/**
	 * 2つの分数のうち大きい方を返します。
	 * 
	 * @param a 分数
	 * @param b 分数
	 * @return
	 */
	public static Fraction max(Fraction a, Fraction b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	/**
	 * 2つの分数を比較します。
	 * 
	 * @param a 分数
	 * @param b 分数
	 * @return
	 */
	public static int compare(Fraction a, Fraction b) {
		return a.compareTo(b);
	}

	/**
	 * 指定した文字列を分数に変換します。 文字列は"実数 / 実数"で表せることを望みます。
	 * 
	 * @param s パースする文字列
	 * @return パース後のFraction
	 */
	public static Fraction parseFraction(String s) {
		s = s.replaceAll(" ", "");
		int pos = s.indexOf("/");
		if (pos == -1)
			pos = s.length();
		double numer = Double.parseDouble(s.substring(0, pos));
		if (pos < s.length()) {
			double denom = Double.parseDouble(s.substring(pos + 1, s.length()));
			return new Fraction(numer, denom);
		}
		return new Fraction(numer);
	}

	/**
	 * 指定した分数の最大公約数を返します。 ここでいう最大公約数は2つの分数を約分したときの共通部分を持つ最大の分数
	 * 
	 * @param a 分数
	 * @param b 分数
	 * @return 分数の最大公約数
	 */
	public static Fraction gcd(Fraction a, Fraction b) {
		return new Fraction(gcd(a.numer, b.numer), lcm(a.denom, b.denom));
	}

	/**
	 * 指定した分数の最小公倍数を返します。 ここでいう最小公倍数は2つの分数を通分したときに出来る最小の分数を指します。
	 * 
	 * @param a 分数
	 * @param b 分数
	 * @return 分数の最小公倍数
	 */
	public static Fraction lcm(Fraction a, Fraction b) {
		return new Fraction(lcm(a.numer, b.numer), gcd(a.denom, b.denom));
	}

	/**
	 * 指定した分数の絶対値を返します。
	 * 
	 * @param f 分数
	 * @return 指定した分数の絶対値
	 */
	public static Fraction abs(Fraction f) {
		return new Fraction(Math.abs(f.numer), f.denom);
	}

	// 9. 内部処理
	/**
	 * 分数を既約分数にします。
	 */
	private void reduceFraction() {
		long gcd = gcd(numer, denom);
		numer /= gcd;
		denom /= gcd;
		if (denom < 0) {
			numer *= -1;
			denom *= -1;
		}
	}

	/**
	 * 2つの整数の最大公約数を求めます。
	 * 
	 * @param x 整数
	 * @param y 整数
	 * @return 最大公約数
	 */
	private static long gcd(long x, long y) {
		x = Math.abs(x);
		y = Math.abs(y);
		return (y > 0) ? gcd(y, x % y) : x;
	}

	/**
	 * 2つの整数の最小公倍数を行います。
	 * 
	 * @param x 整数
	 * @param y 整数
	 * @return 最小公倍数
	 */
	private static long lcm(long x, long y) {
		return x * y == 0 ? 0 : x * (y / gcd(x, y));
	}
}
