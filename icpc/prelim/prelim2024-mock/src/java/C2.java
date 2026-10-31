import java.util.*;

public class C2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			
			int[][] coo = new int[n][3];
			for (int i = 0; i < n; i++) {
				coo[i][0] = sc.nextInt();
				coo[i][1] = sc.nextInt();
				coo[i][2] = sc.nextInt();
			}
			
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						for (int l = k + 1; l < n; l++) {
							int[][] tempCoo1 = new int[4][3];
							int[][] tempCoo2 = new int[4][3];
							int[][] tempCoo3 = new int[4][3];
							
							tempCoo1[0] = coo[i];
							tempCoo1[1] = coo[j];
							tempCoo1[2] = coo[k];
							tempCoo1[3] = coo[l];

							tempCoo2[0] = coo[i];
							tempCoo2[1] = coo[k];
							tempCoo2[2] = coo[j];
							tempCoo2[3] = coo[l];
							
							tempCoo3[0] = coo[i];
							tempCoo3[1] = coo[j];
							tempCoo3[2] = coo[l];
							tempCoo3[3] = coo[k];
							
							if(check(tempCoo1) || check(tempCoo2) || check(tempCoo3)) {
								count++;
							}						
						}
					}
				}
			}
			System.out.println(count);
			
		}
	}
	
	//長方形です。
	public static boolean check(int[][] coo) {
		for (int i = 0; i < 4; i++) {
			if(!check2(coo[i], coo[(i + 1) % 4], coo[(i + 2) % 4])) {
				return false;
			}
		}
		
		return true;
	}
	//ないせきがぜろ;
	public static boolean check2(int[] coo1, int[] coo2, int[] coo3) {
		int result = 0;
		
		int[] vec1 = new int[3];
		int[] vec2 = new int[3];
		
		for(int i = 0; i < 3; i++) { 
			vec1[i] = coo1[i] - coo2[i];
			vec2[i] = coo3[i] - coo2[i];
		}
		
		for(int i = 0; i < 3; i++) { 
			result += vec1[i] * vec2[i];
		}
		return result == 0;
	}

}