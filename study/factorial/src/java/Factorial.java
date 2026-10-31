
public class Factorial {

	public static void main(String[] args) {
		int x = 100;
		System.out.println(factorial(x));
	}

	public static double factorial(int x) {
		// 階乗
		return (x == 1) ? 1 : x * factorial(x - 1);
	}

}
