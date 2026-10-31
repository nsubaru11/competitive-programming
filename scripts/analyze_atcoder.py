import os
import re
import time

import requests
from bs4 import BeautifulSoup

# 環境変数 ATCODER_SESSION に "REVEL_SESSION" の値を設定してください
SESSION_ID = os.environ.get("ATCODER_SESSION", "")

CONTEST_ID = "abc442"
PROBLEM_ID = "abc442_d"

# 検索したいキーワードと正規表現パターン
TARGET_PATTERNS = {
	"segmenttree": re.compile(r"segmenttree", re.IGNORECASE),
	"segtree": re.compile(r"segtree", re.IGNORECASE),
	"fenwick": re.compile(r"fenwick", re.IGNORECASE),  # fenwicktreeもこれでヒットします
	"bit": re.compile(r"\bbit\b", re.IGNORECASE),  # bits/stdc++.h や bitset を回避
	"binaryindexedtree": re.compile(r"binaryindexedtree", re.IGNORECASE)
}

# サーバー負荷対策（秒）
SLEEP_TIME = 1.5

HEADERS = {
	"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0 Safari/537.36",
	"Cookie": f"REVEL_SESSION={SESSION_ID}"
}


# ==========================================

def get_submission_time(row):
	"""提出時刻を取得 (例: 2026-01-24 21:00:05)"""
	time_td = row.find("td", class_="no-break")
	if time_td:
		return time_td.get_text(strip=True)
	return None


def analyze_code(contest_id, sub_id):
	"""
	コードを解析してキーワードが含まれるか判定
	※ #include 行は削除してからチェックを行う
	"""
	url = f"https://atcoder.jp/contests/{contest_id}/submissions/{sub_id}"
	try:
		resp = requests.get(url, headers=HEADERS)
		if resp.status_code != 200: return False, []

		soup = BeautifulSoup(resp.content, "html.parser")
		code_block = soup.find("pre", id="submission-code")

		if code_block:
			raw_text = code_block.get_text()

			# --- 前処理: #include 行の削除 ---
			lines = raw_text.splitlines()
			cleaned_lines = []
			# #include, # include などを検出する正規表現
			include_regex = re.compile(r'^\s*#\s*include', re.IGNORECASE)

			for line in lines:
				# include行ならスキップ
				if include_regex.match(line):
					continue
				cleaned_lines.append(line)

			# 再結合して検索対象テキストを作成
			code_text = "\n".join(cleaned_lines).lower()

			# --- キーワード検索 ---
			found_keywords = []
			for key, pattern in TARGET_PATTERNS.items():
				if pattern.search(code_text):
					found_keywords.append(key)

			return bool(found_keywords), found_keywords

	except Exception as e:
		# print(f"Error analyzing {sub_id}: {e}")
		pass
	return False, []


def main():
	if "YOUR_SESSION" in SESSION_ID:
		print("⚠️ エラー: REVEL_SESSION を設定してください。")
		return

	print(f"🚀 [{CONTEST_ID}] コンテスト本番中 (21:00:00 〜 22:40:00) のコード解析")
	print("   ※ #include行を除外し、単語境界を考慮して検索します")
	print("-" * 60)

	page = 1
	contest_date = None
	start_threshold = None
	end_threshold = None

	total_analyzed = 0
	matched_count = 0

	# キーワードごとのカウント用
	keyword_counts = {k: 0 for k in TARGET_PATTERNS.keys()}

	is_contest_over = False

	while not is_contest_over:
		# orderBy=created: 古い順（開始順）に取得
		params = {
			"f.Task": PROBLEM_ID,
			"f.Status": "AC",
			"orderBy": "created",
			"page": page
		}

		try:
			resp = requests.get(f"https://atcoder.jp/contests/{CONTEST_ID}/submissions", params=params, headers=HEADERS)
			resp.raise_for_status()
			soup = BeautifulSoup(resp.content, "html.parser")

			rows = soup.find("tbody").find_all("tr") if soup.find("tbody") else []

			if not rows:
				print("   これ以上ページがありません。終了します。")
				break

			print(f"📄 ページ {page} をロード中 ({len(rows)}件)...")

			for row in rows:
				time_str = get_submission_time(row)
				if not time_str: continue

				clean_time = time_str.split("+")[0].strip()

				# --- 1. コンテスト日付の確定 ---
				if contest_date is None:
					time_part = clean_time.split(" ")[1]  # HH:MM:SS
					hour = int(time_part.split(":")[0])

					# 21時以降の提出を検知したら、その日をコンテスト日とする
					if hour >= 21:
						contest_date = clean_time.split(" ")[0]
						start_threshold = f"{contest_date} 21:00:00"
						end_threshold = f"{contest_date} 22:40:00"
						print(f"🎯 コンテスト開始を検知: {start_threshold} から解析スタート")
					else:
						continue

				# --- 2. 時間範囲チェック ---
				if clean_time < start_threshold:
					continue

				if clean_time > end_threshold:
					print(f"\n🛑 提出時刻が {clean_time} になりました (終了時刻 {end_threshold} 到達)。")
					is_contest_over = True
					break

				# --- 3. リンク取得と解析 ---
				links = row.find_all("a", href=re.compile(r"submissions/\d+$"))
				valid_links = [l for l in links if l.text.strip() == "Detail"]
				if not valid_links:
					valid_links = [l for l in links if "submissions/" in l.get('href')]

				if not valid_links: continue
				sub_id = valid_links[0].get("href").split("/")[-1]

				# 解析実行
				is_hit, found_keywords = analyze_code(CONTEST_ID, sub_id)
				total_analyzed += 1

				if is_hit:
					matched_count += 1
					for kw in found_keywords:
						keyword_counts[kw] += 1
					print(f"✅ [{clean_time}] ID:{sub_id} -> Found: {found_keywords}")

				time.sleep(SLEEP_TIME)

			page += 1
			time.sleep(SLEEP_TIME)

		except Exception as e:
			print(f"❌ エラー発生: {e}")
			break

	# --- 結果表示 ---
	print("\n" + "=" * 50)
	print("📊 コンテスト期間内 (21:00-22:40) 解析結果")
	print(f"総チェック数: {total_analyzed}")
	print(f"ヒット数    : {matched_count}")

	if total_analyzed > 0:
		print(f"全体使用率  : {(matched_count / total_analyzed * 100):.1f}%")

		print("-" * 30)
		print("📈 キーワード別内訳 (重複あり)")
		sorted_counts = sorted(keyword_counts.items(), key=lambda x: x[1], reverse=True)

		for kw, count in sorted_counts:
			percent = (count / total_analyzed * 100)
			# バーチャート風表示
			bar = "█" * int(percent / 5)
			print(f" - {kw:<18}: {count:>3} 件 ({percent:>5.1f}%) {bar}")
	else:
		print("対象データがありませんでした。")


if __name__ == "__main__":
	main()
