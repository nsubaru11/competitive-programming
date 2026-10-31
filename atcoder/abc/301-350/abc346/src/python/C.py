n, k = map(int, input().split())
a = set(map(int, input().split()))
summation = 0
for i in a:
	if i <= k:
		summation += i
print(k * (k + 1) // 2 - summation)
