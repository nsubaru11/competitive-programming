import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int[][][] a = new int[4][3][10];
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int b = sc.nextInt() - 1;
			int f = sc.nextInt() - 1;
			int r = sc.nextInt() - 1;
			int v = sc.nextInt();
			a[b][f][r] += v;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 10; k++) {
					sb.append(" ").append(a[i][j][k]);
				}
				sb.append("\n");
			}
			if (i < 3) {
				sb.append("####################\n");
			}
		}
		System.out.print(sb);
	}
}
