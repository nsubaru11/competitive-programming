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

	// 愚直解との照合: シードと操作番号を失敗メッセージに含める
	@RepeatedTest(100)
	void matchesNaive(final RepetitionInfo info) {
		final long seed = info.getCurrentRepetition();
		final Random random = new Random(seed);
		for (int op = 0; op < 1000; op++) {
			final int expected = 0;
			final int actual = 0;
			assertEquals(expected, actual, "seed=" + seed + ", op=" + op);
		}
	}
}
