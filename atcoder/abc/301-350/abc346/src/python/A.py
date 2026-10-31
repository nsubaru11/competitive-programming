n = int(input())
a = list(map(int, input().split()))
ans = []
for i in range(1, n):
	ans.append(a[i] * a[i - 1])
print(*ans)
