import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long len = sc.nextLong();
		long Max = sc.nextLong();
		TreeSet<Long> set = new TreeSet<>();

		for (long i = 0; i < len; i++) {
			long x = sc.nextLong();
			if (x <= Max) {
				set.add(x);
			}
		}

		long sum = 0;
		long j = 0;
		for (long i : set) {
			sum += (i - j - 1) * (i + j) / 2;
			j = i;
		}
		sum += (Max - j) * (Max + j + 1) / 2;
		System.out.println(sum);
	}
}
