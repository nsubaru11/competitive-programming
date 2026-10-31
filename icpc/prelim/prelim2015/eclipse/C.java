import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			String[] str = new String[n];
			for (int i = 0; i < n; i++) {
				str[i] = sc.next();
			}
			if (str[0].charAt(0) == '+') {
				System.out.println(plus(str, n, 1, 1));
			} else if(str[0].charAt(0) == '*') {
				System.out.println(multiply(str, n, 1, 1));
			} else {
				System.out.println(str[0]);
			}

		}
	}

	private static int multiply(String[] str, int n, int i,int j) {
		int sum = 0;
		for (; j < n; j++) {
			if (str[j].charAt(i) == '+') {
				
				while (str[j].charAt(i + 1) == '.') {
					
				}
				
			} else {
				
			}
		}
		return 0;
	}

	private static int plus(String[] str, int n, int i, int j) {
		
		return 0;
	}
}