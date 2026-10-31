
public class DFS {
	private int boardState; // 0が空白, 1が自分, 2がCPUとする盤の状態を3進数を用いて表す。
	private final int h, w;

	public DFS(int h, int w) {
		this.h = h;
		this.w = w;
		boardState = 0;
	}

	public boolean set(int i, int j) {
		String board = Integer.toString(boardState, 3);
		int k = i * w + j;
		if (board.length() <= k || board.charAt(k) == '0') {
			boardState += (int) Math.pow(3, k);
			return true;
		} else {
			System.out.println("マスが埋まっています！");
			return false;
		}
	}

	public void judge() {
		
	}

	public void printBoard() {
		
	}
}
