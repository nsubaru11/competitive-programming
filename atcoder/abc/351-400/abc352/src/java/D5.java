import lib.io.*;

import static java.lang.Math.*;

public class D5 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[sc.nextInt() - 1] = i;
		}
		DoublyLinkedList max = new DoublyLinkedList(k);
		DoublyLinkedList min = new DoublyLinkedList(k);
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (i - max.peekFirst() == k) {
				max.pollFirst();
			}
			while (!max.isEmpty() && a[i] > a[max.peekLast()]) {
				max.pollLast();
			}
			max.addLast(i);

			if (i - min.peekFirst() == k) {
				min.pollFirst();
			}
			while (!min.isEmpty() && a[i] < a[min.peekLast()]) {
				min.pollLast();
			}
			min.addLast(i);
			if (i >= k - 1) {
				ans = min(ans, a[max.peekFirst()] - a[min.peekFirst()]);
			}
		}
		System.out.println(ans);
	}

	@SuppressWarnings("unused")
	private static class DoublyLinkedList {
		final int size;
		final int[] list;
		int head, tail, len;

		public DoublyLinkedList(int n) {
			size = n;
			list = new int[size];
			len = head = tail = 0;
		}

		public DoublyLinkedList addLast(int k) {
			if (isEmpty()) {
				head = tail = 0;
			} else {
				tail = (tail + 1) % size;
			}
			list[tail] = k;
			len++;
			return this;
		}

		public DoublyLinkedList addFirst(int k) {
			if (isEmpty()) {
				head = tail = 0;
			} else {
				head = (head - 1) % size;
			}
			list[head] = k;
			len++;
			return this;
		}

		public int pollLast() {
			int res = list[tail];
			if (!isEmpty()) {
				tail = (tail - 1) % size;
				len--;
			}
			return res;
		}

		public int pollFirst() {
			int res = list[head];
			if (!isEmpty()) {
				head = (head + 1) % size;
				len--;
			}
			return res;
		}

		public int peekLast() {
			return list[tail];
		}

		public int peekFirst() {
			return list[head];
		}

		public DoublyLinkedList set(int k, int idx) {
			list[head + idx] = k;
			return this;
		}

		public DoublyLinkedList setLength(int len) {
			this.len = len;
			if (len == 0) {
				head = tail = 0;
			} else {
				tail = (head + len - 1) % size;
			}
			return this;
		}

		public boolean isEmpty() {
			return len == 0;
		}
	}
}
