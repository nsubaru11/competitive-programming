import requests
from bs4 import BeautifulSoup

# --- 診断対象 ---
URL = "https://atcoder.jp/contests/abc350/submissions"
PARAMS = {"f.Task": "abc350_d", "f.Status": "AC"}

# 以前のスクリプトと同じヘッダー
HEADERS = {
	"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
}


def debug_connection():
	print(f"📡 接続テスト: {URL} ...")

	try:
		resp = requests.get(URL, params=PARAMS, headers=HEADERS)
		print(f"📊 HTTP Status Code: {resp.status_code}")

		soup = BeautifulSoup(resp.content, "html.parser")

		# 1. ページタイトルの確認
		page_title = soup.title.string.strip() if soup.title else "No Title Found"
		print(f"🏷️ Page Title: 【 {page_title} 】")

		# 2. ページ内に 'Sign In' や 'Login' があるか確認
		if "Sign In" in page_title or "ログイン" in page_title:
			print("🚨 ログインページにリダイレクトされています。")

		# 3. ページ内のリンクを少しだけ表示してみる
		print("\n🔍 検出されたリンク(最初の5件):")
		links = soup.find_all("a")
		for i, link in enumerate(links[:5]):
			print(f"   {i + 1}. Text='{link.get_text(strip=True)}' | Href='{link.get('href')}'")

		# 4. 提出テーブルの行があるか確認
		rows = soup.find_all("tr")
		print(f"\n🔢 テーブル行数(trタグ): {len(rows)} 行")

	except Exception as e:
		print(f"❌ Error: {e}")


if __name__ == "__main__":
	debug_connection()
