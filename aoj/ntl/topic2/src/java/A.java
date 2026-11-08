import java.io.*;
import java.math.*;
import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		String a = sc.next(), b = sc.next();
		BigInteger aa = new BigInteger(a);
		BigInteger bb = new BigInteger(b);
		out.println(aa.add(bb));
		out.flush();
	}
}
