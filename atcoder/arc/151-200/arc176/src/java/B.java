import java.util.*;

public class B{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			long N = sc.nextLong();
			long M = sc.nextLong();
			long K = sc.nextLong();
			if ((N > M && M > K) || (M > N && N > K)) {
				N -= K;
				M -= K;
				K = 1;
			} else if ((N > K && K > M) || (K > N && N > M)) {
				N -= M;
				K -= M;
				M = 1;
			} else if ((K > M && M > N) || (M > K && K > N)) {
				M -= N;
				K -= N;
				N = 1;
			}
			System.out.println((long) (Math.pow(2, N) % (Math.pow(2, M) - Math.pow(2, K)) ) % 10);
		}
		
	}
}
