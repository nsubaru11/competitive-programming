import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double ms = 0;
		for (int k = 0; k < 10; k++) {
			int n = Integer.MAX_VALUE - 5;
			long t = System.nanoTime();
			boolean[] table = new boolean[n + 1];
			for (int i = 4; i <= n; i += 2) table[i] = true;
			for (int i = 9; i <= n; i += 6) table[i] = true;
			for (int i = 5; i <= n; i += 6) {
	            if (!table[i]) {
	            	for (int j = i; (long) i * j <= n; j += 6) {
	            		table[j * i] = true;
	            		if ((long) j * (i + 2) > n) break;
	            		table[j * (i + 2)] = true;
	            	}
				}
	            int idx = i + 2;
	            if (idx <= n && !table[idx]) {
	            	for (int j = idx; (long) i * j <= n; j += 6) {
	            		table[i * j] = true;
	            		if ((long) j * (i + 4) > n) break;
	            		table[j * (i + 4)] = true;
	            	}
				}
			}
			long add = System.nanoTime() - t;
			System.out.println(add);
			ms += add / 1000000;
		}
		System.out.println(ms / 10);
		sc.close();
	}

}
