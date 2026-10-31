import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class TreeSet<T> {
	private int size;
	private T label;
	private TreeSet<T> left, right;
	private Comparator<? super T> comparator;

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<? super T>) Comparator.naturalOrder());
	}

	public TreeSet(Comparator<? super T> comparator) {
		size = 0;
		label = null;
		left = right = null;
		this.comparator = comparator;
	}

	private TreeSet(T label, Comparator<? super T> comparator) {
		size = 1;
		this.label = label;
		this.comparator = comparator;
	}

	public boolean add(T n) {
		if (label == null) {
			label = n;
			size++;
			return true;
		}
		int cmp = comparator.compare(label, n);
		if (cmp > 0) {
			if (left == null) {
				left = new TreeSet<T>(n, comparator);
				size++;
				return true;
			} else {
				boolean isAdded = left.add(n);
				if (isAdded) size++;
				return isAdded;
			}
		} else if (cmp < 0) {
			if (right == null) {
				right = new TreeSet<T>(n, comparator);
				size++;
				return true;
			} else {
				boolean isAdded = right.add(n);
				if (isAdded) size++;
				return isAdded;
			}
		} else {
			return false;
		}
	}

	public boolean addAll(Collection<? extends T> c) {
		return c.parallelStream().map(this::add).reduce(false, (a, b) -> a || b);
	}

	public T get(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index " + idx + ". Valid range is [0, " + (size - 1) + "].");
		}
		int leftSize = left != null ? left.size() : 0;
		switch (Integer.compare(leftSize, idx)) {
		case 1:
			return left.get(idx);
		case 0:
			return label;
		case -1:
			return right.get(idx - leftSize - 1);
		default:
			throw new IllegalArgumentException("Unexpected value: " + comparator);
		}
	}

	public int indexOf(T n) {
		int c = comparator.compare(label, n);
		if (c > 0) {
			return left != null ? left.indexOf(n) : ~0;
		} else if (c < 0) {
			int leftSize = left != null ? left.size() : 0;
			if (right == null) {
				return ~(leftSize + 1);
			} else {
				int idx = right.indexOf(n);
				if (idx < 0) {
					idx = ~(leftSize + 1 + ~idx);
				} else {
					idx = leftSize + 1 + idx;
				}
				return idx;
			}
		} else {
			return left != null ? left.size() : 0;
		}
	}

	private T findBoundary(T n, boolean inclusive, boolean higher) {
		int cmp = comparator.compare(label, n);
		switch (cmp) {
		case 1:
			if (higher) {
				if (left == null) return label;
				T t2 = left.findBoundary(n, inclusive, higher);
				return t2 != null ? t2 : label;
			} else {
				return left != null ? left.findBoundary(n, inclusive, higher) : null;
			}
		case 0:
			if (inclusive) return label;
			if (higher) {
				return right != null ? right.findBoundary(n, inclusive, higher) : null;
			} else {
				return left != null ? left.findBoundary(n, inclusive, higher) : null;
			}
		case -1:
			if (higher) {
				return right != null ? right.findBoundary(n, inclusive, higher) : null;
			} else {
				if (right == null) return label;
				T t2 = right.findBoundary(n, inclusive, higher);
				return t2 != null ? t2 : label;
			}
		default:
			throw new IllegalArgumentException("Unexpected value: " + cmp);
		}
	}

	public T higher(T n) {
		return findBoundary(n, false, true);
	}

	public T ceiling(T n) {
		return findBoundary(n, true, true);
	}

	public T lower(T n) {
		return findBoundary(n, false, false);
	}

	public T floor(T n) {
		return findBoundary(n, true, false);
	}

	public boolean remove(T n) {
		if (label == null) return false;
		int prevSize = size;
		TreeSet<T> removed = removeAndReplace(n);
		if (removed == null) {
			label = null;
			return true;
		}
		label = removed.label;
		left = removed.left;
		right = removed.right;
		size = removed.size;
		return size != prevSize;
	}

	private TreeSet<T> removeAndReplace(T n) {
		if (label == null) return null;
		int cmp = comparator.compare(label, n);
		if (cmp > 0) {
			if (left == null) return this;
			left = replace(left, left.removeAndReplace(n));
			return this;
		} else if (cmp < 0) {
			if (right == null) return this;
			right = replace(right, right.removeAndReplace(n));
			return this;
		} else {
			size--;
			if (left == null && right == null) {
				label = null;
				return null;
			} else if (left == null) {
				return right;
			} else if (right == null) {
				return left;
			} else {
				if (left.size < right.size) {
					right = findMin(right);
				} else {
					left = findMax(left);
				}
				return this;
			}
		}
	}

	private TreeSet<T> replace(TreeSet<T> prev, TreeSet<T> next) {
		if (next == null || prev.size != next.size) size--;
		return next;
	}

	private TreeSet<T> findMax(TreeSet<T> ts) {
		if (ts == null) return null;
		if (ts.right == null) {
			label = ts.label;
			return ts.left;
		} else {
			ts.right = findMax(ts.right);
			return ts;
		}
	}

	private TreeSet<T> findMin(TreeSet<T> ts) {
		if (ts == null) return null;
		if (ts.left == null) {
			label = ts.label;
			return ts.right;
		} else {
			ts.left = findMin(ts.left);
			return ts;
		}
	}

	public boolean removeAll(Collection<? extends T> c) {
		return c.parallelStream().map(this::remove).reduce(false, (a, b) -> a || b);
	}

	public boolean removeIf(Predicate<? super T> filter) {
		Iterator<T> itr = iterator();
		boolean removed = false;
		while (itr.hasNext()) {
			T next = itr.next();
			if (filter.test(next)) {
				removed |= remove(next);
			}
		}
		return removed;
	}

	public int size() {
		return size;
	}

	public List<T> toList() {
		ArrayList<T> al = new ArrayList<>(size);
		toList(this, al);
		return al;
	}

	private void toList(TreeSet<T> ts, List<T> l) {
		if (ts == null || ts.label == null) return;
		toList(ts.left, l);
		l.add(ts.label);
		toList(ts.right, l);
	}

	public Iterator<T> iterator() {
		return new Itr(this);
	}
	
	private class Itr implements Iterator<T> {
		private final Predicate<TreeSet<T>> isNull = (ts) -> ts == null || ts.label == null;
		private final Deque<TreeSet<T>> dq;

		public Itr(TreeSet<T> ts) {
			dq = new ArrayDeque<>(ts.size());
			if (!isNull.test(ts)) dq.add(ts);
		}

		public boolean hasNext() {
			return !dq.isEmpty();
		}

		public T next() {
			TreeSet<T> ts = dq.poll();
			if (!isNull.test(ts.left)) dq.add(ts.left);
			if (!isNull.test(ts.right)) dq.add(ts.right);
			return ts.label;
		}
		
	}

	public String toString() {
		String s = "";
		if (left != null) {
			s += left.toString() + ", ";
		}
		s += label;
		if (right != null) {
			s += ", " + right.toString();
		}
		return s;
	}
}
