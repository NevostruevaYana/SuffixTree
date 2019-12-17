package suffixTree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class TestBM {

    private static final int ITEM_COUNT = 100_000;

    @State(Scope.Benchmark)
    public static class BenchMarkState {

        private SuffixTree s = new SuffixTree("abcabxabcd");

    }

    @Benchmark
    public void testTreeMap(BenchMarkState state) {
        state.s.search("abx");
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestBM.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}


