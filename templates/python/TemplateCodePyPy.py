import sys
from bisect import bisect_left, bisect_right, insort_left, insort_right
from collections import Counter, defaultdict, deque
from decimal import Decimal, getcontext
from fractions import Fraction
from functools import cache, lru_cache, reduce
from heapq import heapify, heappop, heappush, heappushpop, heapreplace
from itertools import (
	accumulate, chain, combinations, combinations_with_replacement, count, cycle,
	groupby, permutations, product, repeat,
)
from math import ceil, comb, factorial, floor, gcd, isqrt, lcm, perm, pi, prod, sqrt
from typing import Any, TypeVar

# region < Runtime Setup & Constants >
input = sys.stdin.readline
sys.setrecursionlimit(1_000_000)
sys.set_int_max_str_digits(0)
try:
	import pypyjit

	pypyjit.set_param("max_unroll_recursion=-1")
except ImportError:
	pass
T = TypeVar("T", int, float, str)

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
def chmin(a: list[T], i: int, v: T) -> bool:
	if a[i] <= v:
		return False
	a[i] = v
	return True

def chmax(a: list[T], i: int, v: T) -> bool:
	if a[i] >= v:
		return False
	a[i] = v
	return True
# endregion

# region < Entry Point & Debug >
def debug(*args: Any) -> None:
	if DEBUG:
		sys.stdout.flush()
		print(*args, sep=", ", file=sys.stderr)

def debugln(*args: Any) -> None:
	if DEBUG:
		sys.stdout.flush()
		print(file=sys.stderr)
		print(*args, sep="\n", file=sys.stderr)

if __name__ == "__main__":
	solve()
# endregion
