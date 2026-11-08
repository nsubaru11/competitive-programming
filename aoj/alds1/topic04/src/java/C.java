import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt();
		BitSet bi = new BitSet(1 << 24 - 1);
		int[] a = new int['T' + 1];
		a['A'] = 0;
		a['C'] = 1;
		a['G'] = 2;
		a['T'] = 3;
		while (--n >= 0) {
			String s = sc.nextLine();
			if (s.charAt(0) == 'i') {
				int key = 1;
				for (char c : s.substring(7).toCharArray()) {
					key <<= 2;
					key += a[c];
				}
				bi.set(key);
			} else {
				int key = 1;
				for (char c : s.substring(5).toCharArray()) {
					key <<= 2;
					key += a[c];
				}
				out.println(bi.get(key) ? "yes" : "no");
			}
		}
		out.flush();
	}
}
