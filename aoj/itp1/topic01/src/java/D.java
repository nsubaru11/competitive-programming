import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int t = sc.nextInt();
		int h = t / 3600;
		t %= 3600;
		int m = t / 60, s = t % 60;
		StringJoiner sj = new StringJoiner(":");
		sj.add(Integer.toString(h));
		sj.add(Integer.toString(m));
		sj.add(Integer.toString(s));
		System.out.println(sj);
	}
}
