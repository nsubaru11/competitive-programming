#include <bits/stdc++.h>

using namespace std;

// region < Runtime Setup & Constants >
using ll = long long;
using ld = long double;

#ifdef LOCAL
constexpr bool DEBUG = true;
#else
constexpr bool DEBUG = false;
#endif
constexpr int MOD = 998244353;
constexpr ll INF = 1LL << 60;
constexpr array<char, 4> OP = {'L', 'U', 'R', 'D'};
constexpr array<int, 8> DI = {0, -1, 0, 1, -1, -1, 1, 1};
constexpr array<int, 8> DJ = {-1, 0, 1, 0, -1, 1, 1, -1};

void setup_io() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout << setprecision(numeric_limits<ld>::max_digits10);
}
// endregion

// region < Forward Declarations >
template <class T, class U>
constexpr bool chmin(T& a, const U& v);

template <class T, class U>
constexpr bool chmax(T& a, const U& v);

template <class... Args>
void debug(const Args&... args);

template <class... Args>
void debugln(const Args&... args);
// endregion

void solve() {
	int n;
	cin >> n;
	vector<int> a1(n), a2(n), a3(n);
	for (int& ai : a1) cin >> ai;
	for (int& ai : a2) cin >> ai;
	for (int& ai : a3) cin >> ai;

	priority_queue<ll, vector<ll>, greater<ll>> mn;
	mn.push_range(a1);
	ll sum = 0;
	for (int ai : a1) sum += ai;
	vector<ll> ans(n + 1);
	ans[0] = sum;
	for (int i = 0; i < n; i++) {
		int ai = a2[i];
		ans[i + 1] = ans[i];
		if (mn.top() < ai) {
			ans[i + 1] += ai - mn.top();
			mn.pop();
			mn.push(ai);
		}
	}

	sum = 0;
	for (int ai : a3) sum += ai;
	ans[n] -= sum;
	priority_queue<ll> mx;
	mx.push_range(a3);
	for (int i = n - 1; i >= 0; i--) {
		int ai = a2[i];
		if (mx.top() > ai) {
			sum += ai - mx.top();
			mx.pop();
			mx.push(ai);
		}
		ans[i] -= sum;
	}
	cout << *max_element(ans.begin(), ans.end()) << '\n';
}

// region < Utilities >
template <class T, class U>
constexpr bool chmin(T& a, const U& v) {
	if (a <= v) return false;
	a = v;
	return true;
}

template <class T, class U>
constexpr bool chmax(T& a, const U& v) {
	if (a >= v) return false;
	a = v;
	return true;
}
// endregion

// region < Entry Point & Debug >
template <class T>
concept pair_like = requires(const T& v) {
	v.first;
	v.second;
};

template <class T>
concept iterable = requires(const T& v) {
	begin(v);
	end(v);
};

template <class T>
void write_debug(const T& value) {
	if constexpr (is_same_v<T, bool>) {
		cerr << (value ? "true" : "false");
	} else if constexpr (pair_like<T>) {
		cerr << '(';
		write_debug(value.first);
		cerr << ", ";
		write_debug(value.second);
		cerr << ')';
	} else if constexpr (is_convertible_v<const T&, string_view>) {
		cerr << value;
	} else if constexpr (iterable<T>) {
		cerr << '[';
		bool first = true;
		for (const auto& item : value) {
			if (!exchange(first, false)) cerr << ", ";
			write_debug(item);
		}
		cerr << ']';
	} else if constexpr (requires { tuple_size<T>::value; }) {
		cerr << '(';
		apply([](const auto&... items) {
			size_t index = 0;
			((cerr << (index++ == 0 ? "" : ", "), write_debug(items)), ...);
		}, value);
		cerr << ')';
	} else {
		cerr << value;
	}
}

template <class... Args>
void debug(const Args&... args) {
	if constexpr (DEBUG) {
		cout.flush();
		if constexpr (sizeof...(args) > 0) {
			size_t index = 0;
			((cerr << (index++ == 0 ? "" : ", "), write_debug(args)), ...);
		}
		cerr << '\n';
	}
}

template <class... Args>
void debugln(const Args&... args) {
	if constexpr (DEBUG) {
		cout.flush();
		cerr << '\n';
		((write_debug(args), cerr << '\n'), ...);
		if constexpr (sizeof...(args) == 0) cerr << '\n';
	}
}

int main() {
	setup_io();
	solve();
	return 0;
}
// endregion
