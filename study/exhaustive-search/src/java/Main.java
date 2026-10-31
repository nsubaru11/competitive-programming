import java.util.List;

public class Main {

	public static void main(String[] args) {
		testGraphBFS();
		System.out.println();
		testGridBFS();
	}

	private static void testGraphBFS() {
		GraphBFS bfs = new GraphBFS(6, 7);
		bfs.addEdge(0, 1);
		bfs.addEdge(0, 2);
		bfs.addEdge(1, 3);
		bfs.addEdge(2, 4);
		bfs.addEdge(3, 5);
		bfs.addEdge(4, 5);
		int distance = bfs.searchGoal(0, 5);
		System.out.println("Distance: " + distance);
		distance = bfs.searchGoal(1, 4);
		System.out.println("Distance: " + distance);
	}

	private static void testGridBFS() {
		GridBFS bfs = new GridBFS(3, 3);
		bfs.set(0, 0, '.'); // スタート地点
		bfs.set(0, 1, '#'); // 障害物
		bfs.set(0, 2, '.');
		bfs.set(1, 0, '.');
		bfs.set(1, 1, '.');
		bfs.set(1, 2, '#');
		bfs.set(2, 0, '.');
		bfs.set(2, 1, '.');
		bfs.set(2, 2, 'G'); // ゴール地点
		int distance = bfs.searchGoal(0, 0, 'G', c -> c != '#');
		System.out.println("Distance: " + distance);
		distance = bfs.searchGoal(2, 2, 'G', c -> c != '#');
		System.out.println("Distance: " + distance);
		distance = bfs.searchGoal(List.of(0, 2, 4, 6), 'G', c -> c != '#');
		System.out.println("Distance: " + distance);
	}
}
