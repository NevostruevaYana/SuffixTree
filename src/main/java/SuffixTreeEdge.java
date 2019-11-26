class SuffixTreeEdge {

    private int start;
    private int end;
    private SuffixTreeNode nextNode;

    // конструктор для ребра-листа, не имеющего ответвлений (childNode)
    // end = -1 для неопределенного края (еще строим дерево)
    SuffixTreeEdge (int start) {
        this.start = start;
        this.end = -1;
        this.nextNode = null;
    }

    // конструктор для посроения ребра с ответвлениями
    SuffixTreeEdge (int start, int end, SuffixTreeNode childNode) {
        this.start = start;
        this.end   = end;
        this.nextNode  = childNode;
    }

    boolean isLeaf() {
        return (end == -1);
    }

    int getStart() {
        return start;
    }

    void setStartIndex( int start) {
        this.start = start;
    }

    int getEnd() {
        return end;
    }

    void setEnd( int end) {
        this.end = end;
    }

    SuffixTreeNode getNextNode () {
        return nextNode;
    }

    int getLength() {
        return end - start + 1;
    }

}
