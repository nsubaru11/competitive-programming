import java.awt.*;
import java.io.*;
import java.util.*;

public final class D3 {

	private static final int[] dx = {1, 0, -1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final long[][][] visited = new long[101][101][4];
	private static final Comparator<Point> y_comparator = Comparator.comparingInt(o -> o.y);
	private static final Comparator<Point> x_comparator = Comparator.comparingInt(o -> o.x);
	private static final HashMap<Integer, TreeSet<Point>> x_to_y = new HashMap<>();
	private static final HashMap<Integer, TreeSet<Point>> y_to_x = new HashMap<>();

	private static void solve(Scanner sc, PrintWriter out, int n) {
		// input
		int a = sc.nextInt();
		int b = sc.nextInt();
		long distance = sc.nextLong();
		while (n-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point point = new Point(x, y);
			x_to_y.computeIfAbsent(x, ts -> new TreeSet<>(y_comparator)).add(point);
			y_to_x.computeIfAbsent(y, ts -> new TreeSet<>(x_comparator)).add(point);
		}

		// solve
		Point current = new Point(a, b);
		for (int direction = 0; distance > 0; direction = (direction + 1) % 4) {
			int x0 = current.x, y0 = current.y;
			// サイクル処理
			long dist = visited[x0][y0][direction];
			if (dist != 0) {
				long cycle = dist - distance;
				distance %= cycle;
			}
			visited[x0][y0][direction] = distance;

			// 進行方向の障害物の検出
			Point next = getNextPointOrNull(current, direction);
			if (next == null) {
				long x = x0 + dx[direction] * distance;
				long y = y0 + dy[direction] * distance;
				out.print(x);
				out.print(" ");
				out.println(y);
				distance = 0;
			} else {
				distance = moveAndGetRemainDistance(out, current, next, distance, direction);
			}
		}
	}

	private static Point getNextPointOrNull(Point current, int direction) {
		Point next;
		int x = current.x, y = current.y;
		TreeSet<Point> xPoint = y_to_x.get(y);
		TreeSet<Point> yPoint = x_to_y.get(x);
		if (direction == 0) {
			next = xPoint == null ? null : xPoint.higher(current);
		} else if (direction == 1) {
			next = yPoint == null ? null : yPoint.higher(current);
		} else if (direction == 2) {
			next = xPoint == null ? null : xPoint.lower(current);
		} else {
			next = yPoint == null ? null : yPoint.lower(current);
		}
		return next;
	}

	private static long moveAndGetRemainDistance(PrintWriter out, Point current, Point next, long distance,
												 int direction) {
		int x1 = current.x, y1 = current.y;
		int x2 = next.x, y2 = next.y;
		int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2) - 1;
		if (distance > dist) {
			current.move(x2 - dx[direction], y2 - dy[direction]);
			distance -= dist;
		} else {
			long x = x1 + dx[direction] * distance;
			long y = y1 + dy[direction] * distance;
			out.print(x);
			out.print(" ");
			out.println(y);
			distance = 0;
		}
		return distance;
	}

	private static void init() {
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				visited[i][j][0] = 0;
				visited[i][j][1] = 0;
				visited[i][j][2] = 0;
				visited[i][j][3] = 0;
			}
		}
		x_to_y.clear();
		y_to_x.clear();
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); PrintWriter out = new PrintWriter(System.out)) {
			int n = sc.nextInt();
			while (n != 0) {
				init();
				solve(sc, out, n);
				n = sc.nextInt();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
