import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int m = Integer.parseInt(sc.next());	//竹の寿命(開花に必要な年数)
			int n = Integer.parseInt(sc.next());	//区画の数
			if (m == 0) {
				sc.close();
				return;
			}

			// solve
			boolean[] flowering = new boolean[7368792];		//区画に植えた竹の年数の倍数をすべてtrueに返す
			int count = 0;		//区画に植えた竹の数
			for (int i = m; ; i++) {

				if (count < n && !flowering[i]) {		//区画の空きがなくなるまで、今までに植えた竹の寿命の倍数でない竹を植える
					flowering[i] = true;
					for (int j = 1; i * j <= 7368791; j++) {	//エラトステネスの篩にのっとり植えた竹の倍数を最大値までtrueに返す
						flowering[i * j] = true;
					}
					count++;
				}

				if (!flowering[i]) {
					System.out.println(i);
					break;
				}
			}
		}
	}
}