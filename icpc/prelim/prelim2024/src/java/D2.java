import java.awt.*;
import java.util.*;

public class D2 {

	private static long d;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int a = sc.nextInt();    //出発地点x座標
			int b = sc.nextInt();    //出発地点y座標
			d = sc.nextLong();    //移動距離
			HashMap<Integer, TreeSet<Integer>> x_y = new HashMap<>();    //障害物のx座標からy座標への写像
			HashMap<Integer, TreeSet<Integer>> y_x = new HashMap<>();    //障害物のy座標からx座標への写像
			for (int i = 0; i < n; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				//x -> y
				if (x_y.get(x) == null) {
					TreeSet<Integer> yy = new TreeSet<>();
					yy.add(y);
					x_y.put(x, yy);
				} else {
					TreeSet<Integer> yy = x_y.get(x);
					yy.add(y);
					x_y.put(x, yy);
				}
				//y -> x
				if (y_x.get(y) == null) {
					TreeSet<Integer> xx = new TreeSet<>();
					xx.add(x);
					y_x.put(y, xx);
				} else {
					TreeSet<Integer> xx = y_x.get(y);
					xx.add(x);
					y_x.put(y, xx);
				}
			}

			// solve
			HashMap<Point, HashMap<Integer, Long>> memo = new HashMap<>();
			Point xy = new Point(a, b);
			while (d > 0) {
				if (Memo(memo, xy, 0)) {
					while (d > 0) {
						plusX(xy, x_y);
						if (d == 0) break;
						plusY(xy, x_y);
						if (d == 0) break;
						minusX(xy, y_x);
						if (d == 0) break;
						minusY(xy, x_y);
						if (d == 0) break;
						if (memo.get(xy) != null && memo.get(xy).get(0) != null) {
							d %= (long) (d - memo.get(xy).get(0));
							if (d == 0) {
								System.out.println(xy.x + " " + xy.y);
							} else {
								while (d > 0) {
									plusX(xy, x_y);
									if (d == 0) break;
									plusY(xy, x_y);
									if (d == 0) break;
									minusX(xy, y_x);
									if (d == 0) break;
									minusY(xy, x_y);
								}
							}
						}
					}
				}
				if (d == 0) break;
				plusX(xy, y_x);

				if (Memo(memo, xy, 1)) {
					while (d > 0) {
						plusY(xy, x_y);
						if (d == 0) break;
						minusX(xy, y_x);
						if (d == 0) break;
						minusY(xy, x_y);
						if (d == 0) break;
						plusX(xy, x_y);
						if (d == 0) break;
						if (memo.get(xy) != null && memo.get(xy).get(1) != null) {
							d %= (long) (d - memo.get(xy).get(1));
							if (d == 0) {
								System.out.println(xy.x + " " + xy.y);
							} else {
								while (d > 0) {
									plusX(xy, x_y);
									if (d == 0) break;
									plusY(xy, x_y);
									if (d == 0) break;
									minusX(xy, y_x);
									if (d == 0) break;
									minusY(xy, x_y);
								}
							}
						}

					}
				}
				if (d == 0) break;
				plusY(xy, x_y);

				if (Memo(memo, xy, 2)) {
					while (d > 0) {
						minusX(xy, y_x);
						if (d == 0) break;
						minusY(xy, x_y);
						if (d == 0) break;
						plusX(xy, x_y);
						if (d == 0) break;
						plusY(xy, x_y);
						if (d == 0) break;
						if (memo.get(xy) != null && memo.get(xy).get(2) != null) {
							d %= (long) (d - memo.get(xy).get(2));
							if (d == 0) {
								System.out.println(xy.x + " " + xy.y);
							} else {
								while (d > 0) {
									plusX(xy, x_y);
									if (d == 0) break;
									plusY(xy, x_y);
									if (d == 0) break;
									minusX(xy, y_x);
									if (d == 0) break;
									minusY(xy, x_y);
								}
							}
						}
					}
				}
				if (d == 0) break;
				minusX(xy, y_x);

				if (Memo(memo, xy, 3)) {
					while (d > 0) {
						minusY(xy, x_y);
						if (d == 0) break;
						plusX(xy, x_y);
						if (d == 0) break;
						plusY(xy, x_y);
						if (d == 0) break;
						minusX(xy, y_x);
						if (d == 0) break;
						if (memo.get(xy) != null && memo.get(xy).get(3) != null) {
							d %= (long) (d - memo.get(xy).get(3));
							if (d == 0) {
								System.out.println(xy.x + " " + xy.y);
							} else {
								while (d > 0) {
									plusX(xy, x_y);
									if (d == 0) break;
									plusY(xy, x_y);
									if (d == 0) break;
									minusX(xy, y_x);
									if (d == 0) break;
									minusY(xy, x_y);
								}
							}
						}
					}
				}
				if (d == 0) break;
				minusY(xy, x_y);
			}

		}
	}

	private static boolean Memo(HashMap<Point, HashMap<Integer, Long>> memo, Point xy, int direction) {
		if (memo.get(xy) == null) {
			HashMap<Integer, Long> dir_d = new HashMap<>();
			dir_d.put(direction, d);
			memo.put(xy.getLocation(), dir_d);
		} else {
			HashMap<Integer, Long> dir_d = memo.get(xy);
			if (dir_d.get(direction) == null) {
				dir_d.put(direction, d);
			} else {
				if (dir_d.get(direction) == 0) {
					dir_d.put(direction, d);
				}
				return true;
			}
		}
		return false;
	}

	private static void plusX(Point xy, HashMap<Integer, TreeSet<Integer>> y_x) {
		if (y_x.get(xy.y) == null) {
			System.out.println((long) (xy.x + d) + " " + xy.y);
			d = 0;
		} else {
			TreeSet<Integer> x = y_x.get(xy.y);
			if (x.higher(xy.x) == null || x.higher(xy.x) > (long) xy.x + d) {
				System.out.println((long) (xy.x + d) + " " + xy.y);
				d = 0;
			} else {
				d -= Math.abs(x.higher(xy.x) - xy.x) - 1;
				xy.move(x.higher(xy.x) - 1, xy.y);
			}
		}
	}

	private static void plusY(Point xy, HashMap<Integer, TreeSet<Integer>> x_y) {
		if (x_y.get(xy.x) == null) {
			System.out.println(xy.x + " " + (long) (xy.y + d));
			d = 0;
		} else {
			TreeSet<Integer> y = x_y.get(xy.x);
			if (y.higher(xy.y) == null || y.higher(xy.y) > (long) xy.y + d) {
				System.out.println(xy.x + " " + (long) (xy.y + d));
				d = 0;
			} else {
				d -= Math.abs(y.higher(xy.y) - xy.y) - 1;
				xy.move(xy.x, y.higher(xy.y) - 1);
			}
		}
	}

	private static void minusX(Point xy, HashMap<Integer, TreeSet<Integer>> y_x) {
		if (y_x.get(xy.y) == null) {
			System.out.println((long) (xy.x - d) + " " + xy.y);
			d = 0;
		} else {
			TreeSet<Integer> x = y_x.get(xy.y);
			if (x.lower(xy.x) == null || x.lower(xy.x) < (long) xy.x - d) {
				System.out.println((long) (xy.x - d) + " " + xy.y);
				d = 0;
			} else {
				d -= Math.abs(x.lower(xy.x) - xy.x) - 1;
				xy.move(x.lower(xy.x) + 1, xy.y);
			}
		}
	}

	private static void minusY(Point xy, HashMap<Integer, TreeSet<Integer>> x_y) {
		if (x_y.get(xy.x) == null) {
			System.out.println(xy.x + " " + (long) (xy.y - d));
			d = 0;
		} else {
			TreeSet<Integer> y = x_y.get(xy.x);
			if (y.lower(xy.y) == null || y.lower(xy.y) < (long) xy.y - d) {
				System.out.println(xy.x + " " + (long) (xy.y - d));
				d = 0;
			} else {
				d -= Math.abs(y.lower(xy.y) - xy.y) - 1;
				xy.move(xy.x, y.lower(xy.y) + 1);
			}
		}
	}

}
