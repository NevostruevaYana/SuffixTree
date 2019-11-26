public class SuffixTreeTest {
    public static void main (String[] args) {
        SuffixTree suffixTree = new SuffixTree("abcabxabcdxad");
        System.out.println(suffixTree.search("bcabx"));
        System.out.println(suffixTree.search("abcx"));
        System.out.println(suffixTree.search("abcabxabcd"));
        System.out.println(suffixTree.search("cabxd"));
        System.out.println(suffixTree.search("xadg"));
    }
}