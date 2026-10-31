n, x, y, z = map(int, input().split())
print("Yes" if (x <= z <= y or y <= z <= x) else "No")
