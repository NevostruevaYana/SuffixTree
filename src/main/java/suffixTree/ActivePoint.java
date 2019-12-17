package suffixTree;

public class ActivePoint {

    // первый индекс текущей активной точки
    int activePointIndex;
    // узел активной точки
    // указывает на узел, дети которого будут модифицированны
    SuffixTreeNode activeNode;

    ActivePoint (SuffixTreeNode activeNode, int activePointIndex) {
        this.activePointIndex = activePointIndex;
        this.activeNode = activeNode;
    }
}