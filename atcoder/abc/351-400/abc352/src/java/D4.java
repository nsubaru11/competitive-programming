import lib.io.*;

import static java.lang.Math.*;

public class D4 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[sc.nextInt() - 1] = i;
		}
		SimpleDoublyLinkedList max = new SimpleDoublyLinkedList(n);
		SimpleDoublyLinkedList min = new SimpleDoublyLinkedList(n);
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (i - max.getFirst() == k) {
				max.removeFirst();
			}
			while (!max.isEmpty() && a[i] > a[max.getLast()]) {
				max.removeLast();
			}
			max.add(i);

			if (i - min.getFirst() == k) {
				min.removeFirst();
			}
			while (!min.isEmpty() && a[i] < a[min.getLast()]) {
				min.removeLast();
			}
			min.add(i);
			if (i >= k - 1) {
				ans = min(ans, a[max.getFirst()] - a[min.getFirst()]);
			}
		}
		System.out.println(ans);
	}

	private static class SimpleDoublyLinkedList {
		int[] list;
		private int l, r, size;

		SimpleDoublyLinkedList(int n) {
			list = new int[n];
			l = r = size = 0;
		}

		public void add(int k) {
			if (size == 0) {
				list[r] = k;
			} else {
				list[++r] = k;
			}
			size++;
		}

		public int getFirst() {
			return list[l];
		}

		public int getLast() {
			return list[r];
		}

		public void removeFirst() {
			if (size != 1) {
				l++;
			}
			size--;
		}

		public void removeLast() {
			if (size != 1) {
				r--;
			}
			size--;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
}
