import lib.io.*;

public class B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		String S = sc.next();
		int len = S.length();
		String lower = "zyxwvutsrqponmlkjihgfedcba";    //あらかじめ小文字列を用意
		int count = 0;
		char maxLetter = 'a';
		for (int i = 0; i < lower.length(); i++) {
			if (S.contains(lower.substring(i, i + 1))) { //lowerのi番目の文字がSにどれだけ含まれるか
				int maxCounts = 0;
				for (int j = 0; j < len; j++) {
					if (S.charAt(j) == lower.charAt(i)) {
						maxCounts++;
					}
				}
				if (count <= maxCounts) {    //最大値と文字を更新
					count = maxCounts;
					maxLetter = lower.charAt(i);
				}
			}
		}
		System.out.println(maxLetter);
	}
}
