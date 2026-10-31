def main():
	n = int(input())
	total, t, v = 0, 0, 0
	for i in range(n):
		t2, v = map(int, input().split())
		total = max(total - (t2 - t), 0) + v
		t = t2
	print(total)


if __name__ == '__main__':
	main()
