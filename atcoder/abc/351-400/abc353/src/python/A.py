n = int(input())
h = list(map(int, input().split()))
for i in range(n):
	if h[0] < h[i]:
		print(i + 1)
		exit()
print(-1)
