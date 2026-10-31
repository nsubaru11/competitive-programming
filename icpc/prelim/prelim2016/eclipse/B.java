import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}

			// solve
			int max = 0;
			int max2 = 0;
			String winner = null;
			int time = 0;
			HashMap<String, Integer> candidate = new HashMap<>();
			HashMap<Integer, HashSet<String>> votes = new HashMap<>();
			for (int i = 1; i < n + 1; i++) {
				String S = sc.next();
				int v = 0;

				if (candidate.get(S) == null) {	// 候補者から投票数への写像
					v = 1;
					candidate.put(S, v);
				} else {
					v = candidate.get(S) + 1;
					candidate.put(S, v);
				}

				if (max < candidate.get(S)) {	// 暫定一位の投票数
					winner = S;
					max = candidate.get(S);
				} else if (max2 <= candidate.get(S)) {	// 暫定二位の投票数
					max2 = candidate.get(S);
				}

				// 投票数から候補者への写像
				HashSet<String> members = votes.get(v);
				if (members == null) {
					members = new HashSet<>();
					members.add(S);
					votes.put(v, members);
				} else {
					members.add(S);
				}

				// ある候補者に投票が入ったときにその候補者の投票数を更新。
				if (v > 1) {
					members = votes.get(v - 1);
					members.remove(S);
				}

				// 最高投票数者が一人の時二位が挽回できるかどうかの判定を行う。
				members = votes.get(max);
				if (members.size() == 1 && max > n - i + max2) {
					time = i;
					sc.nextLine();
					break;
				}
			}

			// output
			HashSet<String> members = votes.get(max);
			System.out.println((members.size() >= 2) ? "TIE": (time != 0) ? winner + " " + time: winner + " " + n);
		}
	}

}