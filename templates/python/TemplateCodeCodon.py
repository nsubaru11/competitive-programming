import sys
from bisect import bisect_left, bisect_right, insort_left, insort_right
from collections import Counter, defaultdict, deque
from heapq import heapify, heappop, heappush, heappushpop, heapreplace
from itertools import (
	accumulate, chain, combinations, combinations_with_replacement, count, cycle,
	groupby, permutations, product, repeat,
)
from math import ceil, factorial, floor, gcd, lcm, pi, prod, sqrt

# region < Runtime Setup & Constants >
DEBUG: bool = True
MOD: int = 998244353
INF: int = 10 ** 18
OP = ("L", "U", "R", "D")
DI = (0, -1, 0, 1, -1, -1, 1, 1)
DJ = (-1, 0, 1, 0, -1, 1, 1, -1)
# endregion

def solve() -> None:
	pass

# region < Utilities >
def chmin(a, i: int, v) -> bool:
	if a[i] <= v:
		return False
	a[i] = v
	return True

def chmax(a, i: int, v) -> bool:
	if a[i] >= v:
		return False
	a[i] = v
	return True
# endregion

# region < Entry Point & Debug >
def debug(*args) -> None:
	if DEBUG:
		sys.stdout.flush()
		print(*args, sep=", ", file=sys.stderr)

def debugln(*args) -> None:
	if DEBUG:
		sys.stdout.flush()
		print(file=sys.stderr)
		print(*args, sep="\n", file=sys.stderr)

if __name__ == "__main__":
	solve()
# endregion
