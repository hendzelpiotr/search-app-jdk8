package pl.hendzel.searchappjdk8;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.test.context.SpringBootTest;

class SearchAppJdk8ApplicationTests {

	@Test
	void contextLoads() throws RunnerException {
		Options options = new OptionsBuilder()
				.include(".*StringsBenchmark")
				//.jvmArgs("-server", "-Xmx10G", "-XX:BiasedLockingStartupDelay=0", "-XX:+UseG1GC")
				.addProfiler(GcProfiler.class)
				.addProfiler(ForcedGcMemoryProfiler.class)
				.result("result.csv")
				.resultFormat(ResultFormatType.CSV)
				.warmupIterations(3)
				.measurementIterations(5)
				.forks(1)
				.build();
		new Runner(options).run();
	}

}
