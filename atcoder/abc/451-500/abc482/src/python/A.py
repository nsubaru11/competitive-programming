def main():
	s = input()
	cnt = 0
	for c in s:
		if c == 'o':
			cnt += 1
	print("odd" if cnt % 2 == 1 else "even")

if __name__ == "__main__":
	main()
