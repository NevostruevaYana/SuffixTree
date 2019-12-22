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
    @State(Scope.Benchmark)
    public static class BenchMarkState {
        private SuffixTree s = new SuffixTree("abcabxabcd");
        private SuffixTree s1 = new SuffixTree("aabcabxfabcdxadlabcabxabcdxad");
    }
    @Benchmark
    public void test1(BenchMarkState state) {
        state.s.search("abx");
    }
    @Benchmark
    public void test2(BenchMarkState state) { state.s1.search("abx"); }
    @Benchmark
    public void test3(BenchMarkState state) {
        state.s.search("xa");
    }
    @Benchmark
    public void test4(BenchMarkState state) {
        state.s1.search("xa");
    }
    @Benchmark
    public void test5(BenchMarkState state) { state.s.search("a"); }
    @Benchmark
    public void test6(BenchMarkState state) { state.s1.search("a"); }
    @Benchmark
    public void test7(BenchMarkState state) { state.s.search("xabcd"); }
    @Benchmark
    public void test8(BenchMarkState state) { state.s1.search("xabcd"); }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestBM.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}





