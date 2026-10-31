import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.next());
		String delimiter = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(sc.next());
				if (a == 1) {
					System.out.print(delimiter + (j + 1));
					delimiter = " ";
				}
			}
			if (delimiter.equals(" ")) {
				delimiter = "";
				System.out.println(delimiter);
			} else {
				System.out.println(delimiter);
			}
		}
	}

}
