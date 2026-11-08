import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			//行で読み込み
			sc.nextLine();
			String S = sc.nextLine();
			S = S.replaceAll(" ", "");    //半角スペースを削除する
			int count = 0;
			for (int i = 0; i < n - 3; i++) {
				//2020があればcountし、iをさらに+1すすめる
				if (S.substring(i, i + 4).equals("2020")) {
					count++;
					i++;
				}
			}
			System.out.println(count);
		}
	}
}
