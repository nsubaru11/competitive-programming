w, b = map(int, input().split())
s = 'wbwbwwbwbwbw'
for i in range(12):
	cnt = 0
	for j in range(w + b):
		if s[(i + j) % 12] == 'w':
			cnt += 1
	if cnt == w:
		print("Yes")
		exit()
else:
	print("No")
