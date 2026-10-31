import java.util.*;

public class A2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		TreeSet<Integer> plates = new TreeSet<Integer>();
		TreeSet<Integer> tempPlates = new TreeSet<Integer>();
		//Mahiro
		while (true) {
			plates.clear();
			//入力
			for (int i = 0; i < 4; i++) {
				int a = sc.nextInt();
				plates.add(a);
			}
			if (plates.size() == 1 && plates.contains(0)) {
				sc.close();
				return;
			}
			//操作
			while(plates.size() > 1) {
				int b = plates.first();
				tempPlates.clear();
				for(int plate : plates) {
					tempPlates.add(plate - b);
				}
				plates.clear();
				plates.add(b);
				plates.addAll(tempPlates);
				plates.remove(0);
			}
			System.out.println(plates.first());
		}
	}
}
