import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = Integer.parseInt(sc.next());
		int b = Integer.parseInt(sc.next());
		for (int i = 0; i < 10; i++) {
			if (i != a + b) {
				System.out.println(i);
				return;
			}
		}
	}
}
