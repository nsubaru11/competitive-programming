import java.util.*;

import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int h = sc.nextInt();
		int w = sc.nextInt();
		ArrayList<TreeSet<Integer>> hw = new ArrayList<>();
		ArrayList<TreeSet<Integer>> wh = new ArrayList<>();
		for (int i = 0; i < h; i++) {
			hw.add(new TreeSet<>());
			for (int j = 0; j < w; j++) {
				hw.get(i).add(j);
				if (i == 0) {
					wh.add(new TreeSet<>());
				}
				wh.get(j).add(i);
			}
		}
		int q = sc.nextInt();
		int cnt = 0;
		while (--q >= 0) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			TreeSet<Integer> hh = hw.get(r), ww = wh.get(c);
			Integer hf = hh.floor(c);
			Integer hc, wf, wc;
			if (hf != null && hf == c) {
				hc = wf = wc = null;
			} else {
				hc = hh.ceiling(c);
				wf = ww.floor(r);
				wc = ww.ceiling(r);
			}
			if (hf != null) {
				hh.remove(hf);
				wh.get(hf).remove(r);
				cnt++;
			}
			if (hc != null) {
				hh.remove(hc);
				wh.get(hc).remove(r);
				cnt++;
			}
			if (wf != null) {
				ww.remove(wf);
				hw.get(wf).remove(c);
				cnt++;
			}
			if (wc != null) {
				ww.remove(wc);
				hw.get(wc).remove(c);
				cnt++;
			}
		}
		System.out.println(h * w - cnt);
	}
}
