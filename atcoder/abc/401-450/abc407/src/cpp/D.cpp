#include <iostream>
#include <map>

using namespace std;

int main() {
	int n;
	cin >> n;
	// i 番目の部品を頭もしくは体に取り付けたときの最大の嬉しさを求める的な
	// 頭の重さが k の時の最大の嬉しさ？
	// 途中は負でも良いから、頭の重さが h, 体の重さが w の時の最大の嬉しさ
	// 上なら 500 * 500 * 500 = 125 * 10 ^ 6 = 1.25 * 10 ^ 8 ぎり間に合う
	// HashMapで高速化は可能。
	int whb[n][3];
	int remain = 0;
	for (int i = 0; i < n; i++) {
		cin >> whb[i][0] >> whb[i][1] >> whb[i][2];
		remain += whb[i][0];
	}
	map<pair<int, int>, long long> m;
	m[{0, 0}] = 0;
	for (int i = 0; i < n; i++) {
		int w = whb[i][0];
		int h = whb[i][1];
		int b = whb[i][2];
		map<pair<int, int>, long long> temp;
		for (const auto& [key, value] : m) {
			int hw = key.first;
			int bw = key.second;
			if (hw + w <= bw + remain) {
				pair ph = {hw + w, bw};
				auto thv = temp[ph];
				if (thv < value + h) temp[ph] = value + h;
			}
			pair pb = {hw, bw + w};
			auto tbv = temp[pb];
			if (tbv < value + b) temp[pb] = value + b;
		}
		m = temp;
		remain -= w;
	}
	long long ans = 0;
	for (const auto& [key, value] : m) {
		if (key.first <= key.second && ans < value) ans = value;
	}
	cout << ans << endl;
	return 0;
}
