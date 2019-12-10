package suffixTree;

import java.util.HashMap;
import java.util.Map;

public class SuffixTreeNode {

    private Map<Character, SuffixTreeEdge> childEdges;
    // ссылка на узел, в который впоследствии вставляем тех же потомков
    private SuffixTreeNode link;


    // конструктор для создания нового узла
    SuffixTreeNode() {
        childEdges = new HashMap<Character, SuffixTreeEdge>();
        link = null;
    }


    void addChild (char childCharacter, SuffixTreeEdge edge) {
        childEdges.put(childCharacter, edge);
    }

    SuffixTreeEdge getChild (char childChar) {
        return childEdges.get(childChar);
    }

    void setSuffixLink (SuffixTreeNode suffixLink) {
        this.link = suffixLink;
    }

    SuffixTreeNode getLink () {
        return link;
    }

}
