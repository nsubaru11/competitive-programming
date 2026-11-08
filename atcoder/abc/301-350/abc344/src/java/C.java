import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<Integer> A = new HashSet<>();
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			A.add(x);
		}

		HashSet<Integer> B = new HashSet<>();
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			B.add(x);
		}

		HashSet<Integer> C = new HashSet<>();
		int L = sc.nextInt();
		for (int i = 0; i < L; i++) {
			int x = sc.nextInt();
			C.add(x);
		}

		int Q = sc.nextInt();
		ArrayList<Integer> X = new ArrayList<Integer>();
		for (int i = 0; i < Q; i++) {
			int x = sc.nextInt();
			X.add(x);
		}

		HashSet<Integer> D = new HashSet<>();
		boolean doesOutput = false;
		for (int a : A) {
			for (int b : B) {
				for (int c : C) {
					D.add(a + b + c);
				}
			}
		}
		for (int x : X) {
			if (D.contains(x)) {
				System.out.println("Yes");
			} else System.out.println("No");
		}
	}
}
