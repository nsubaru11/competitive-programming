import java.io.*;
import java.math.*;
import java.util.*;

public class F {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		String a = sc.next(), b = sc.next();
		BigInteger aa = new BigInteger(a);
		BigInteger bb = new BigInteger(b);
		out.println(aa.multiply(bb));
		out.flush();
	}
}
