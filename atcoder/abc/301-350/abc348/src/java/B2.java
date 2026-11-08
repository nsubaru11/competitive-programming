import java.util.*;


public class B2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X[] = new int[N];
		int Y[] = new int[N];
		int base = 0;
		ArrayList<Double> D = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			X[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int d = (X[i] - X[j]) * (X[i] - X[j]) + (Y[i] - Y[j]) * (Y[i] - Y[j]);
				D.add(Math.sqrt(d));
			}
			int n = 0;
			for (int j = 1; j < N; j++) {
				if (D.get(n + base) < D.get(j + base)) {
					n = j;
				}
			}
			System.out.println(n + 1);
			base = base + N;
		}


	}
}
