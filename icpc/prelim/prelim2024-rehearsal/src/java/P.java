import java.util.*;

public class P {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// input
			int n = sc.nextInt();
			if (n == 0) {
				sc.close();
				return;
			}
			
			int max = 0;
			for (int i = 0; i < n; i++) {
				int input = sc.nextInt();
				if (input >= max) {
					max = input;
				}
			}
			
			System.out.println(max);
		}
	}

}
