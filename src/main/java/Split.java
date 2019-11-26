class Split {

    boolean endPointReached;
    SuffixTreeNode state;

    Split (boolean endPointReached, SuffixTreeNode  state) {
        this.endPointReached = endPointReached;
        this.state = state;
    }
}