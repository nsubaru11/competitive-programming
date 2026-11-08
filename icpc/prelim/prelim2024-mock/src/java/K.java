import java.util.*;

public class K {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int m = Integer.parseInt(sc.next());
		for (int i = 0; i < m; i++) {
			double n = Math.sqrt(Integer.parseInt(sc.next()));
			System.out.println(Math.hypot(n, n) / 2);
		}

		sc.close();
	}

}
