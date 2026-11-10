#pragma GCC optimize("Ofast")
#pragma GCC target("avx2,popcnt,lzcnt,fma")
#include <bits/stdc++.h>

using namespace std;

class FastScanner {
	static constexpr int BUF_SIZE = 65536;
	alignas(64) unsigned char* buf;
	int pos = 0, cnt = 0;

	inline unsigned char read() {
		if (__builtin_expect(pos == cnt, 0)) {
			cnt = static_cast<int>(fread(buf, 1, BUF_SIZE, stdin));
			pos = 0;
		}
		return buf[pos++];
	}

public:
	inline explicit FastScanner(const int buf_size = BUF_SIZE) { buf = new unsigned char[buf_size]; }

	inline ~FastScanner() { delete[] buf; }

	inline unsigned char nextChar() {
		unsigned char c;
		while ((c = read()) < '!') {
		}
		return c;
	}

	inline string next() {
		string s;
		unsigned char c = nextChar();
		while ('!' <= c && c <= '~') {
			s += static_cast<char>(c);
			c = read();
		}
		return s;
	}

	inline string nextLine() {
		string s;
		unsigned char c = read();
		while (c != 0 && c != '\r' && c != '\n') {
			s += static_cast<char>(c);
			c = read();
		}
		if (c == '\r') read();
		return s;
	}

	inline int nextInt() {
		unsigned char c = nextChar();
		const bool neg = c == '-';
		if (neg) c = read();
		int n = 0;
		while ('0' <= c && c <= '9') {
			n = n * 10 + c - '0';
			c = read();
		}
		return neg ? -n : n;
	}

	inline long long nextLong() {
		unsigned char c = nextChar();
		const bool neg = c == '-';
		if (neg) c = read();
		long long n = 0;
		while ('0' <= c && c <= '9') {
			n = n * 10 + c - '0';
			c = read();
		}
		return neg ? -n : n;
	}

	inline double nextDouble() {
		unsigned char c = nextChar();
		const bool neg = c == '-';
		if (neg) c = read();
		double n = 0;
		while ('0' <= c && c <= '9') {
			n = n * 10 + c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double factor = 1;
			while ('0' <= c && c <= '9') {
				factor /= 10;
				n += (c - '0') * factor;
				c = read();
			}
		}
		return neg ? -n : n;
	}

	inline int* nextInt(const int n) {
		int* a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		return a;
	}
};

class FastPrinter {
	static constexpr int BUF_SIZE = 65536;
	alignas(64) unsigned char* buf;
	int pos = 0;

	inline void flush() {
		fwrite(buf, 1, pos, stdout);
		pos = 0;
	}

public:
	inline explicit FastPrinter(const int buf_size = BUF_SIZE) { buf = new unsigned char[buf_size]; }

	inline ~FastPrinter() {
		flush();
		delete[] buf;
	}

	inline FastPrinter& println() {
		if (pos + 1 >= BUF_SIZE) flush();
		buf[pos++] = '\n';
		return *this;
	}

	inline FastPrinter& println(const char c) { return print(c).println(); }

	inline FastPrinter& println(const string& s) { return print(s).println(); }

	inline FastPrinter& println(const int x) { return print(x).println(); }

	inline FastPrinter& println(const long long x) { return print(x).println(); }

	inline FastPrinter& print(const char c) {
		if (pos + 1 >= BUF_SIZE) flush();
		buf[pos++] = c;
		return *this;
	}

	inline FastPrinter& print(const string& s) {
		size_t len = s.length();
		if (pos + len >= BUF_SIZE) flush();
		for (size_t i = 0; i < len; ++i) {
			buf[pos++] = s[i];
		}
		return *this;
	}

	inline FastPrinter& print(int x) {
		if (pos + 11 >= BUF_SIZE) flush();
		unsigned char tmp[10];
		int i = 0;
		if (x < 0) buf[pos++] = '-', x = -x;
		do {
			tmp[i++] = '0' + (x % 10);
		} while (x /= 10);
		while (i--)
			buf[pos++] = tmp[i];
		return *this;
	}

	inline FastPrinter& print(long long x) {
		if (pos + 20 >= BUF_SIZE) flush();
		unsigned char tmp[19];
		int i = 0;
		if (x < 0) buf[pos++] = '-', x = -x;
		do {
			tmp[i++] = '0' + (x % 10);
		} while (x /= 10);
		while (i--)
			buf[pos++] = tmp[i];
		return *this;
	}
};

int main() {
	FastScanner* sc = new FastScanner();
	FastPrinter* out = new FastPrinter();
	int n = sc->nextInt();
	int ans = n * (n - 1) / 2;
	int* cnt = new int[n];
	for (int i = 0; i < n; i++) {
		int a = sc->nextInt();
		int b = sc->nextInt();
		int idx = (a + b) % n;
		ans -= cnt[idx];
		cnt[idx]++;
	}
	out->println(ans);
}
