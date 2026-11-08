import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stk = new Stack<>();
		for (String s : sc.nextLine().split(" ")) {
			switch (s) {
				case "+":
					int a = stk.pop();
					int b = stk.pop();
					stk.push(a + b);
					break;
				case "-":
					a = stk.pop();
					b = stk.pop();
					stk.push(b - a);
					break;
				case "*":
					a = stk.pop();
					b = stk.pop();
					stk.push(a * b);
					break;
				case "/":
					a = stk.pop();
					b = stk.pop();
					stk.push(b / a);
					break;
				default:
					stk.push(Integer.parseInt(s));
			}
		}
		System.out.println(stk.pop());
	}
}
