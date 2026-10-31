import java.util.*;

public class J {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int m = Integer.parseInt(sc.next());
			System.out.println(n * m);
		}
	}

}
