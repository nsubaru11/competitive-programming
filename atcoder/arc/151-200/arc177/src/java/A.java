import java.util.*;

import lib.io.*;

public class A {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		HashMap<Integer, Integer> coins = new HashMap<>();
		int sum_coins = 0;
		int x = Integer.parseInt(sc.next());
		coins.put(1, x);
		sum_coins += 1 * x;

		x = Integer.parseInt(sc.next());
		coins.put(5, x);
		sum_coins += 5 * x;

		x = Integer.parseInt(sc.next());
		coins.put(10, x);
		sum_coins += 10 * x;

		x = Integer.parseInt(sc.next());
		coins.put(50, x);
		sum_coins += 50 * x;

		x = Integer.parseInt(sc.next());
		coins.put(100, x);
		sum_coins += 100 * x;

		x = Integer.parseInt(sc.next());
		coins.put(500, x);
		sum_coins += 500 * x;

		int n = Integer.parseInt(sc.next());

		int one = 0;
		int five = 0;
		int ten = 0;
		int fifty = 0;
		int hundred = 0;
		int fivehundred = 0;
		int sum_all = 0;
		for (int i = 0; i < n; i++) {
			x = Integer.parseInt(sc.next());
			sum_all += x;
			one += x % 5;
			five += x % 10 / 5;
			ten += x % 50 / 10;
			fifty += x % 100 / 50;
			hundred += x % 500 / 100;
			fivehundred += x / 500;
		}
		int add = 0;

		if (sum_coins < sum_all) {
			System.out.println("No");
			return;
		}

		if (coins.get(1) - one >= 5) {
			add = coins.get(1) - one;
			coins.put(5, coins.get(5) + add / 5);
		} else if (one > coins.get(1)) {
			System.out.println("No");
			return;
		}

		if ((coins.get(5) - five) * 5 >= 10) {
			add = coins.get(5) - five;
			coins.put(10, coins.get(10) + add * 5 / 10);
		} else if (five > coins.get(5)) {
			System.out.println("No");
			return;
		}

		if ((coins.get(10) - ten) * 10 >= 50) {
			add = coins.get(10) - ten;
			coins.put(50, coins.get(50) + add * 10 / 50);
		} else if (ten > coins.get(10)) {
			System.out.println("No");
			return;
		}

		if ((coins.get(50) - fifty) * 50 >= 100) {
			add = coins.get(50) - fifty;
			coins.put(100, coins.get(100) + add * 50 / 100);
		} else if (fifty > coins.get(50)) {
			System.out.println("No");
			return;
		}

		if ((coins.get(100) - hundred) * 100 >= 500) {
			add = coins.get(100) - hundred;
			coins.put(500, coins.get(500) + add * 100 / 500);
		} else if (hundred > coins.get(100)) {
			System.out.println("No");
			return;
		}

		if (fivehundred <= coins.get(500)) {
			System.out.println("Yes");
			return;
		} else {
			System.out.println("No");
			return;
		}

	}
}
