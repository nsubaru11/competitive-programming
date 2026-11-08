import sys, math, heapq, bisect, random
import itertools as it
from collections import Counter, defaultdict, deque
from decimal import Decimal, getcontext
from fractions import Fraction
from functools import cache, lru_cache, reduce
from itertools import accumulate, chain, combinations, combinations_with_replacement, count, cycle, groupby, pairwise, permutations, product

input = sys.stdin.readline
sys.setrecursionlimit(10**7)
sys.set_int_max_str_digits(0)
getcontext().prec = 50
INF = float('inf')
def ii(): return int(input())
def mi(): return map(int, input().split())
def li(): return list(mi())
def ls(): return input().split()
def show(x): print(list(x))
def grids(g): print(*(' '.join(map(str, r)) for r in g), sep='\n')
def grid(g): print(*map(''.join, g), sep='\n')
