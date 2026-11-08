import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(sc.nextLine());
		DoublyLinkedList dl = new DoublyLinkedList(n);
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			if (s.charAt(0) == 'i') {
				dl.insert(Integer.parseInt(s.substring(7)));
			} else if ((s = s.substring(6)).charAt(0) == 'F') {
				dl.deleteFirst();
			} else if (s.charAt(0) == 'L') {
				dl.deleteLast();
			} else {
				dl.delete(Integer.parseInt(s.substring(1)));
			}
		}
		out.println(dl);
		out.flush();
	}

	private static class DoublyLinkedList {
		private int head, tail;
		private int[] list;

		DoublyLinkedList(int n) {
			list = new int[n];
			fill(list, -1);
			head = tail = 0;
		}

		void insert(int k) {
			list[head++] = k;
		}

		void delete(int k) {
			if (list[head - 1] == k) {
				deleteFirst();
			} else {
				for (int i = head - 2; i >= tail; i--) {
					if (list[i] == k) {
						if (i == tail) {
							deleteLast();
						} else {
							list[i] = -1;
							break;
						}
					}
				}
			}
		}

		void deleteFirst() {
			list[head - 1] = -1;
			while (tail < head && list[head - 1] < 0) {
				head--;
			}
		}

		void deleteLast() {
			list[tail] = -1;
			while (tail < head && list[tail] < 0) {
				tail++;
			}
		}

		public String toString() {
			StringBuilder sb = new StringBuilder("");
			for (int i = head; i >= tail; i--) {
				if (list[i] >= 0) {
					sb.append(list[i]).append(" ");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
	}
}
