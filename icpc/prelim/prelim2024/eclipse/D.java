import java.util.*;

public class D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			long a = sc.nextLong();
			long b = sc.nextLong();
			
			long d = sc.nextLong();
			long k = d;
			TreeMap<Long, TreeSet<Long>> x_y = new TreeMap<>();
			TreeMap<Long, TreeSet<Long>> y_x = new TreeMap<>();
			
			for (int i = 0; i < n; i++) {
				long x = sc.nextLong();
				long y = sc.nextLong();
				if (x_y.get(x) == null) {
					TreeSet<Long> yy = new TreeSet<>();
					yy.add(y);
					x_y.put(x, yy);
				} else {
					TreeSet<Long> yy = x_y.get(x);
					yy.add(y);
					x_y.put(x, yy);
				}
				
				if (y_x.get(y) == null) {
					TreeSet<Long> xx = new TreeSet<>();
					xx.add(x);
					y_x.put(y, xx);
				} else {
					TreeSet<Long> xx = y_x.get(y);
					xx.add(x);
					y_x.put(y, xx);
				}
			}
			// x正0　y正1 x負2 y負3
			HashMap<String, Long> memo = new HashMap<>();
			int direction = 0;
			long dir = 0L;
			try {
				while (d > 0L) {
					if (direction % 4 == 0) {
						if (y_x.get(b) == null) {
							System.out.println(a + d + " " + b);
							break;
						} else {
							TreeSet<Long> x = y_x.get(b);
							if (x.higher(a) == null || x.higher(a) > a + d) {
								System.out.println(a + d + " " + b);
								break;
							} else {
								if (memo.get(a + " " + b + " " + (direction % 4)) == null) {
									memo.put(a + " " + b + " " + (direction % 4), dir);
									dir = Math.abs(x.higher(a) - a) - 1L;
								} else {
									d %= memo.get(a + " " + b + " " + (direction % 4));
									if (d == 0) {
										System.out.println(a + 1L + " " + b);
										break;
									}
									System.out.println(move(x_y, y_x, a, b, direction, d));
									break;
								}
								d -= Math.abs(x.higher(a) - a) - 1L;
								a = x.higher(a) - 1L;
								direction++;
							}
						}
					} else if (direction % 4 == 1) {
						if (x_y.get(a) == null) {
							System.out.println(a + " " + b + d);
							break;
						} else {
							TreeSet<Long> y = x_y.get(a);
							if (y.higher(b) == null || y.higher(b) > b + d) {
								System.out.println(a + " " + (b + d));
								break;
							} else {
								if (memo.get(a + " " + b + " " + (direction % 4)) == null) {
									memo.put(a + " " + b + " " + (direction % 4), dir);
									dir = Math.abs(y.higher(b) - b) - 1L;
								} else {
									d %= memo.get(a + " " + b + " " + (direction % 4));
									if (d == 0) {
										System.out.println(a + " " + (b + 1L));
										break;
									}
									System.out.println(move(x_y, y_x, a, b, direction, d));
									break;
								}
								d -= Math.abs(y.higher(b) - b) - 1L;
								b = y.higher(b) - 1L;
								direction++;
							}
						}
					} else if (direction % 4 == 2) {
						if (y_x.get(b) == null) {
							System.out.println(a - d + " " + b);
							break;
						} else {
							TreeSet<Long> x = y_x.get(b);
							if (x.lower(a) == null || x.lower(a) < a - d) {
								System.out.println(a - d + " " + b);
								break;
							} else {
								if (memo.get(a + " " + b + " " + (direction % 4)) == null) {
									memo.put(a + " " + b + " " + (direction % 4), dir);
									dir += Math.abs(x.lower(a) - a) - 1L;
								} else {
									d %= memo.get(a + " " + b + " " + (direction % 4));
									if (d == 0) {
										System.out.println(a - 1L + " " + b);
										break;
									}
									System.out.println(move(x_y, y_x, a, b, direction, d));
									break;
								}
								d -= Math.abs(x.lower(a) - a) - 1L;
								a = x.lower(a) + 1L;
								direction++;
							}
						}
					} else if (direction % 4 == 3) {
						if (x_y.get(a) == null) {
							System.out.println(a + " " + (b - d));
							break;
						} else {
							TreeSet<Long> y = x_y.get(a);
							if (y.lower(b) == null || y.lower(b) < b - d) {
								System.out.println(a + " " + (b - d));
								break;
							} else {
								if (memo.get(a + " " + b + " " + (direction % 4)) == null) {
									memo.put(a + " " + b + " " + (direction % 4), dir);
									dir += Math.abs(y.lower(b) - b) - 1L;
								} else {
									d %= memo.get(a + " " + b + " " + (direction % 4));
									if (d == 0) {
										System.out.println(a + " " + (b - 1L));
										break;
									}
									System.out.println(move(x_y, y_x, a, b, direction, d));
									break;
								}
								d -= Math.abs(y.lower(b) - b) - 1L;
								b = y.lower(b) + 1L;
								direction++;
							}
						}
					}
				}
			} catch (ArithmeticException e) {
				d %= (k - d);
				System.out.println(move(x_y, y_x, a, b, direction, d));
				break;
			}
			
		}
	}
	
	private static String move (TreeMap<Long, TreeSet<Long>> xy, TreeMap<Long, TreeSet<Long>> yx, long a, long b, int direction, long d) {
		while (d > 0L) {
			if (direction % 4 == 0) {
				if (yx.get(b) == null) {
					return a + d + " " + b;
				} else {
					TreeSet<Long> x = yx.get(b);
					if (x.higher(a) == null || x.higher(a) > a + d) {
						return a + d + " " + b;
					} else {
						d -= Math.abs(x.higher(a) - a) - 1L;
						a = x.higher(a) - 1L;
						direction++;
					}
				}
			} else if (direction % 4 == 1) {
				if (xy.get(a) == null) {
					return a + " " + (b + d);
				} else {
					TreeSet<Long> y = xy.get(a);
					if (y.higher(b) == null || y.higher(b) > b + d) {
						return a + " " + (b + d);
					} else {
						d -= Math.abs(y.higher(b) - b) - 1L;
						b = y.higher(b) - 1L;
						direction++;
					}
				}
			} else if (direction % 4 == 2) {
				if (yx.get(b) == null) {
					return a - d + " " + b;
				} else {
					TreeSet<Long> x = yx.get(b);
					if (x.lower(a) == null || x.lower(a) < a - d) {
						return a - d + " " + b;
					} else {
						d -= Math.abs(x.lower(a) - a) - 1L;
						a = x.lower(a) + 1L;
						direction++;
					}
				}
			} else if (direction % 4 == 3) {
				if (xy.get(a) == null) {
					return a + " " + (b - d);
				} else {
					TreeSet<Long> y = xy.get(a);
					if (y.lower(b) == null || y.lower(b) < b - d) {
						return a + " " + (b - d);
					} else {
						d -= Math.abs(y.lower(b) - b) - 1L;
						b = y.lower(b) + 1L;
						direction++;
					}
				}
			}
		}
		return a + " " + b;
	}
	
	
}