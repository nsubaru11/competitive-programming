def main():
	s = input().strip()
	cnt = [0] * 26
	sum_idx = [0] * 26
	ans = 0
	for i in range(len(s)):
		idx = ord(s[i]) - ord('A')
		ans += cnt[idx] * (i - 1) - sum_idx[idx]
		cnt[idx] += 1
		sum_idx[idx] += i
	print(ans)


if __name__ == '__main__':
	main()
