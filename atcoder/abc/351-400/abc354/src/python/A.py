h = int(input())
H = 1
n = 1
days = 1
while True:
	if h < H:
		print(days)
		break
	days += 1
	n *= 2
	H += n
