s = set(map(int, input().split()))
print(-1 if len(s) == 1 else 6 - sum(s))
