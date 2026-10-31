import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // input
        	int d = Integer.parseInt(sc.next());
            int w = Integer.parseInt(sc.next());
            if (d == 0) {
                sc.close();
                return;
            }
            int[][] e = new int[d][w];
            for (int i = 0; i < d; i++) {
                for (int j = 0; j < w; j++) {
                    e[i][j] = Integer.parseInt(sc.next());
                }
            }

            // 池の四つ角を定める。
            int ans = 0;
            for (int wi = 0; wi < w - 2; wi++) {
                for (int wj = wi + 2; wj < w; wj++) {
                    for (int di = 0; di < d - 2; di++) {
                        for (int dj = di + 2; dj < d; dj++) {
                            ans = Math.max(ans, pondSize(wi, wj, di, dj, e));
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }

    private static int pondSize(int wi, int wj, int di, int dj, int[][] e) {
        int min = Integer.MAX_VALUE;
        // 池の外周の高さの最小値を調べる。
        for (int k = wi + 1; k < wj; k++) {
            min = Math.min(min, Math.min(e[di][k], e[dj][k]));
        }
        for (int k = di; k <= dj; k++) {
            min = Math.min(min, Math.min(e[k][wi], e[k][wj]));
        }

        // 池の深さを調べ、サイズを足していく。水が漏れるならreturn。
        int size = 0;
        for (int d = di + 1; d < dj; d++) {
            for (int w = wi + 1; w < wj; w++) {
                int deep = min - e[d][w];
                size += deep;
                if (deep <= 0) return 0;
            }
        }
        return size;
    }
}
