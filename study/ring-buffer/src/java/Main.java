
public class Main {

	public static void main(String[] args) {
		int n = 10;
		UtilRingBuffer.RingBuffer<Pair> rb = new UtilRingBuffer.RingBuffer<>(n, () -> new Pair(0, 0));
		for (int i = 0; i < n / 2; i++) {
			rb.addFirst(new Pair(1, 0));
			rb.addLast(new Pair(n - i - 1, 0));
		}
		System.out.println(rb);
		for (int i = 0; i < n / 2; i++) {
			rb.pollFirst();
			rb.pollLast();
		}
		System.out.println(rb);
		System.out.println(rb.setLength(n));
		rb.setAll(p -> new Pair(rb.get(p).i, rb.get(p).j + 1));
		for (Pair p : rb) {
			System.out.println(p);
		}
		for (int i = 0; i < n; i += 2) {
			rb.set(i, new Pair(i, n * n));
		}
		System.out.println(rb);
	}

	private static class Pair {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}

}
