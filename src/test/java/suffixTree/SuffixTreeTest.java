package suffixTree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SuffixTreeTest {

    @Test
    public void test() {
        SuffixTree suffixTree = new SuffixTree("abcabxabcdxadlabcabxabcdxad");
        assertTrue(suffixTree.search("bcabx"));
        assertFalse(suffixTree.search("abcx"));
        assertTrue(suffixTree.search("abcabxabcd"));
        assertTrue(suffixTree.search(""));
        assertFalse(suffixTree.search("xabcdxadf"));
        assertFalse(suffixTree.search("f"));
    }

}