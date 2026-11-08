import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int r = sc.nextInt();
		int c = sc.nextInt();
		int[][] a = new int[r][c];
		int[] b = new int[c];
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < r; i++) {
			int sumRow = 0;
			for (int j = 0; j < c; j++) {
				if (j > 0) sb.append(" ");
				a[i][j] = sc.nextInt();
				sb.append(a[i][j]);
				sumRow += a[i][j];
				sum += a[i][j];
				b[j] += a[i][j];
			}
			sb.append(" ").append(sumRow).append("\n");
		}
		for (int bb : b) {
			sb.append(bb).append(" ");
		}
		sb.append(sum);
		System.out.println(sb);
	}
}
