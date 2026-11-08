## @formatter:off
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};
#end
#parse("File Header.java")

import java.util.*;
import java.util.concurrent.*;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

// JMHが継承して使うため、public かつ final にしない
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class ${NAME} {

	@Param({"100000"})
	private int n;

	private int[] input;

	// 入力は計測の外で、シードを固定して作る
	@Setup(Level.Trial)
	public void setup() {
		final Random random = new Random(1);
		input = new int[n];
		for (int i = 0; i < n; i++) input[i] = random.nextInt();
	}

	// 結果は Blackhole で消費し、計算が最適化で消えないようにする
	@Benchmark
	public void library(final Blackhole bh) {
		bh.consume(input.length);
	}

	@Benchmark
	public void baseline(final Blackhole bh) {
		bh.consume(input.length);
	}
}
