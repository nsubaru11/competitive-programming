import java.io.*;
import java.util.*;
import java.util.Map.*;

public class B {

	private static void solve(Scanner sc, PrintWriter out, int n) {
		char[] c = new char[n];
		sc.nextLine();
		char[] temp = sc.nextLine().toCharArray();
		for (int i = 0; i < n; i++) {
			c[i] = temp[i * 2];
		}
		HashMap<Character, Integer> map = new HashMap<>();
		TreeMap<Integer, List<Character>> treeMap = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			int value = map.getOrDefault(c[i], 0);
			map.put(c[i], value + 1);
			treeMap.computeIfAbsent(value + 1, k -> new ArrayList<>()).add(c[i]);
			if (value > 0) {
				treeMap.get(value).remove((Character) c[i]);
				if (treeMap.get(value).isEmpty()) {
					treeMap.remove(value);
				}
			}
			if (judge(treeMap, n, i + 1)) {
				char ans = treeMap.lastEntry().getValue().get(0);
				out.println(ans + " " + (i + 1));
				return;
			}
		}
		out.println("TIE");
	}

	private static boolean judge(TreeMap<Integer, List<Character>> treeMap, int n, int i) {
		Entry<Integer, List<Character>> entry = treeMap.lastEntry();
		if (entry.getValue().size() > 1) {
			return false;
		} else {
			Entry<Integer, List<Character>> entry2 = treeMap.lowerEntry(entry.getKey());
			int top = entry.getKey();
			int top2 = entry2 == null ? 0 : entry2.getKey();
			return top > top2 + (n - i);
		}
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
