import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class D3 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[sc.nextInt() - 1] = i;
		}
		Deque<Integer> maxDq = new ArrayDeque<>();
		Deque<Integer> minDq = new ArrayDeque<>();
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			while (!maxDq.isEmpty() && a[i] > a[maxDq.peekLast()]) {
				maxDq.removeLast();
			}
			maxDq.addLast(i);
			if (i - maxDq.peekFirst() >= k) {
				maxDq.removeFirst();
			}

			while (!minDq.isEmpty() && a[i] < a[minDq.peekLast()]) {
				minDq.removeLast();
			}
			minDq.addLast(i);
			if (i - minDq.peekFirst() >= k) {
				minDq.removeFirst();
			}
			if (i >= k - 1) {
				ans = min(ans, a[maxDq.peekFirst()] - a[minDq.peekFirst()]);
			}
		}
		System.out.println(ans);
	}
}
