import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashSet<Integer> hs = new HashSet<>(n);
		for (int i = 0; i < n; i++) {
			hs.add(sc.nextInt());
		}
		int q = sc.nextInt();
		int cnt = 0;
		for (int i = 0; i < q; i++) {
			if (hs.contains(sc.nextInt())) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
