import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		SegmentTreeArray st = new SegmentTreeArray(n, (i, j) -> Math.max(i, j));
		for (int i = 0; i < n; i++) {
			int k = (int) (Math.random() * n);
			st.set(i, k);
		}
		System.out.println(st.query(0, n));
		sc.close();
	}
}
