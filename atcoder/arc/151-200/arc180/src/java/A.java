import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		String S = sc.next();
		long res = 1L;
		int count = 1;
		int mod = 1000000007;
		for (int i = 1; i < n; i++) {
			if (S.charAt(i) != S.charAt(i - 1)) {
				count++;
			} else {
				res = res * ((count + 1L) / 2L) % mod;
				count = 1;
			}
		}
		System.out.println(res * ((count + 1L) / 2L) % mod);
		sc.close();
	}

}
