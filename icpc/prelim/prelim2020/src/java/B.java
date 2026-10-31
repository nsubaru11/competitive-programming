import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<Integer> direct = new HashSet<>(); //感染の疑いが出た人の集合

		while (true) {
			//入力
			int m = Integer.parseInt(sc.next());	//人数
			int n = Integer.parseInt(sc.next());	//件数
			int p = Integer.parseInt(sc.next());	//感染源
			if (m == 0) {
				sc.close();
				return;
			}

			direct.clear(); //初期化
			direct.add(p);
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(sc.next());
				int b = Integer.parseInt(sc.next());
				//集合にどちらかが含まれていたらaddする
				if (direct.contains(a) || direct.contains(b)) {
					direct.add(b);
					direct.add(a);
				}
			}
			System.out.println(direct.size());
		}

	}
}
