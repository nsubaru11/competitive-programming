
public class Bit {
	public static void main(String[] args) {
		int bit, bit2, bit3, num, n;
		bit = bit2 = num = n = 0;

		bit3 = bit | bit2;// or
		bit3 = bit & bit2;// and
		bit3 = bit ^ bit2;// xor
		bit3 = ~bit;// not

		bit3 = bit >> num;// 右ビットシフト
		bit3 = bit << num;// 左ビットシフト

		Integer.bitCount(bit3);// 1の数
		Integer.toBinaryString(num);// 整数 -> 2進数表記文字列
		Integer.toString(num, n);// 整数 -> n進数表記文字列
		Integer.parseInt("111", n);// 整数 -> n進数
	}
}
