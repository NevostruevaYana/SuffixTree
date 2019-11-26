import java.util.HashMap;

class SuffixTreeNode {

    private HashMap<Character, SuffixTreeEdge> childNodes;
    // ссылка на узел, в который впоследствии вставляем тех же потомков
    private SuffixTreeNode link;


    // конструктор для создания нового узла
    SuffixTreeNode() {
        childNodes = new HashMap<Character, SuffixTreeEdge>();
        link = null;
    }


    void addChild (char childCharacter, SuffixTreeEdge edge) {
        childNodes.put(childCharacter, edge);
    }

    SuffixTreeEdge getChild (char childChar) {
        return childNodes.get(childChar);
    }

    void setSuffixLink (SuffixTreeNode suffixLink) {
        this.link = suffixLink;
    }

    SuffixTreeNode getLink () {
        return link;
    }

}
