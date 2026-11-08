import java.io.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int[] arr = new int[128];
		while ((s = br.readLine()) != null) {
			for (char c : s.toCharArray()) {
				arr[Character.toLowerCase(c)]++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 'a'; i <= 'z'; i++) {
			sb.append(Character.toString(i)).append(" : ").append(arr[i]).append("\n");
		}
		System.out.print(sb);
	}

}
