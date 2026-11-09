## @formatter:off
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
#end
#parse("File Header.java")

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

final class ${NAME} {

	// 境界値: 1行が1ケース。列は引数の順に並べる
	@ParameterizedTest
	@CsvSource({
		"0, 0",
	})
	void boundary(final int input, final int expected) {
		fail("未実装");
	}

	// 愚直解との照合: 配列などの単純な実装に同じ操作を適用し、結果を比べる
	// 失敗メッセージのシードと操作番号から、同じ操作列を再現できる
	@RepeatedTest(200)
	void matchesNaive(final RepetitionInfo info) {
		final long seed = info.getCurrentRepetition();
		final Random random = new Random(seed);
		// n を小さくして端の添字を踏みやすくする
		final int n = 1 + random.nextInt(32);
		final long[] naive = new long[n];
		fail("未実装: 対象を naive と同じ内容で構築する");
		for (int op = 0; op < 500; op++) {
			final String at = "seed=" + seed + ", op=" + op;
			final int i = random.nextInt(n);
			switch (random.nextInt(2)) {
				case 0 -> {
					// 更新: naive と対象の両方に適用し、戻り値も比べる
					final long v = random.nextLong(1000);
					naive[i] = v;
				}
				default -> {
					// 取得: 0L を対象の取得結果に置き換える
					assertEquals(naive[i], 0L, at);
				}
			}
		}
	}

	// TLE検出: 最大制約の入力で計算量の退行を見つける。./gradlew slowTest だけが実行する
	@Test
	@Tag("slow")
	@Timeout(2)
	void maxConstraint() {
		final Random random = new Random(1);
		fail("未実装");
	}
}
