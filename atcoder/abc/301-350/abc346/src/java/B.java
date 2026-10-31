import java.util.*;

public class B{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = "";
		int W = sc.nextInt();
		int B = sc.nextInt();
		for (int i = 0; i < 18; i++) {
			S += "wbwbwwbwbwbw";
		}
		for (int i = 0; i < 12; i++) {
			int count = 0;
			for (int j = 0; j < W + B; j++) {
				if (S.charAt(i + j) == 'w') {
					count++;
				}
			}
			if (count == W) {
				System.out.println("Yes");
				return;
			}
		}
		System.out.println("No");
	}
}