import java.util.*;

import lib.io.*;

public class B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		int n = Integer.parseInt(sc.next());

		StringBuilder s = new StringBuilder(sc.next());
		String S = s.toString();

		ArrayList<Integer> count = new ArrayList<>(); //AおよびBを何回押さないといけないかのカウント

		for (int i = n - 1; i >= 0; i--) {
			if (S.charAt(i) == '1') {    //i番目が1のとき (i + 1)回Aを押す
				count.add(i + 1);
				for (int j = i - 1; j >= 0; j--) {
					if (S.charAt(j) == '0') {    //j番目が0のとき (j + 1)回Bを押す
						count.add(j + 1);
						i = j;
						break;
					} else if (j == 0) {
						i = 0;    //一番最初が1で始まるときの例外処理
						break;
					}
				}
			}
		}

		String Switch = "";
		for (int i = 0; i < count.size(); i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < count.get(i); j++) {
					Switch += "A";    //countの偶数番目の値はAを押す数
				}
			} else {
				for (int j = 0; j < count.get(i); j++) {
					Switch += "B";    ////countの奇数番目の値はBを押す数
				}
			}
		}

		System.out.println(Switch.length());
		System.out.println(Switch);
	}
}
