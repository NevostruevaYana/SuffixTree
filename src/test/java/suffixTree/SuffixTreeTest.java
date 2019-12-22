package suffixTree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SuffixTreeTest {
    @Test
    public void test() {
        SuffixTree suffixTree = new SuffixTree("abcabxfabcdxadlabcabxabcdxad");
        assertTrue(suffixTree.search("bcabx"));
        assertFalse(suffixTree.search("abcx"));
        assertTrue(suffixTree.search("abcabxfabcd"));
        assertTrue(suffixTree.search(""));
        assertFalse(suffixTree.search("xabcdxadf"));
        assertFalse(suffixTree.search("i"));
    }
    @Test
    public void test1() {
        SuffixTree suffixTree = new SuffixTree("fddfaaaaaaaaaa");
        assertTrue(suffixTree.search("f"));
        assertFalse(suffixTree.search("ddd"));
        assertTrue(suffixTree.search("aaaaaaaaaa"));
        assertFalse(suffixTree.search("fdf"));
    }
}



