import java.util.Arrays;

public class A_LinearSearch {

	public static void main(String[] args) {
		int n = 10;
		int target = (int) (Math.random() * n);
		int[] data = new int[n];
		Arrays.setAll(data, i -> (int) (Math.random() * n));
		int index = -1;
		for (int i = 0; i < n; i++) {
			if (data[i] == target) {
				index = i;
				break;
			}
		}
		System.out.println(Arrays.toString(data));
		System.out.println("target: " + target + ", index: " + index);
	}
}
