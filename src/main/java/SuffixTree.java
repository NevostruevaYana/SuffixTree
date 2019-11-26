import java.util.LinkedList;

class SuffixTree {

    private SuffixTreeNode root;
    private String text;
    private LinkedList<SuffixTreeEdge> leafEdges;

    // конструктор для создания дерева
    SuffixTree(String text) {
        root = new SuffixTreeNode();
        this.text = text;
        leafEdges = new LinkedList<SuffixTreeEdge>();

        build();
    }

    boolean search(String search) {

        SuffixTreeNode parentNode = root;
        SuffixTreeEdge currentEdge = null;
        int searchLength = search.length();
        boolean contain = true;
        int searchIndex = 0;
        int edgeIndex = 0;

        while (searchIndex < searchLength) {
            //узел равен null, но конец поискового текста еще не достигнут
            if (parentNode == null) {
                contain = false;
                break;
            }

            // edge = null в начале поиска и при переходе в новый edge
            // проверяем есть у searchNode исходящее
            // ребро, начинающееся с char в searchIndex
            if (currentEdge == null) {
                currentEdge = parentNode.getChild(search.charAt(searchIndex));
                if (currentEdge == null) {
                    contain = false;
                    break;
                }
                edgeIndex = currentEdge.getStart();
            } else {
                // проверка следующего символа
                if (search.charAt(searchIndex) != text.charAt(edgeIndex)) {
                    contain = false;
                    break;
                }
            }
            // если индекс ребра достиг конца, переходим к следующему узлу
            if (edgeIndex == currentEdge.getEnd()) {
                parentNode = currentEdge.getNextNode();
                currentEdge = null;
            }

            // если символ соответствует инкрементируем searchIndex и edgeIndex
            // и проверяем дальше
            searchIndex++;
            edgeIndex++;
        }

        return contain;
    }

    private void build() {
        ActivePoint activePoint = new ActivePoint(root, 0);
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            update(activePoint, i);
            canonize(activePoint, i);
        }

        // после окончания построения дерева концы всех ребер равны -1,
        // присваиваем им значение, равное длине строки - 1
        for (SuffixTreeEdge leafEdge : leafEdges)
            leafEdge.setEnd(textLength - 1);
    }

    // обновление дерева с символом на i-й позиции
    // при найденных повторениях обновление активной точки
    private void update(ActivePoint activePoint, int i) {
        SuffixTreeNode link = root;
        Split split;

        split = testAndSplit(activePoint, i);

        while (!split.endPointReached) {
            // создание нового узла для текущего состояния
            SuffixTreeEdge newEdge = new SuffixTreeEdge(i);
            leafEdges.add(newEdge);
            split.state.addChild(text.charAt(i), newEdge);

            // если link != root добавляем ссылку
            if (link != root) {
                link.setSuffixLink(split.state);
            }

            link = split.state;

            if (activePoint.activeNode == root) {
                // если имеем дело с активной точкой и еще не добавили всю подстроку активный точки,
                // инкрементируем индекс буквы активной точки и продолжаем добавление
                if (activePoint.activePointIndex < i) {
                    activePoint.activePointIndex++;
                }
                //инкрементируем индекс активной точки и выходи из цикл while
                else {
                    activePoint.activePointIndex++;
                    break;
                }
            } else {
                activePoint.activeNode = activePoint.activeNode.getLink();
                canonize(activePoint, i);
            }
            split = testAndSplit(activePoint, i);
        }
        if (link != root) {
            link.setSuffixLink(activePoint.activeNode);
        }
    }

    private Split testAndSplit(ActivePoint activePoint, int currentIndex) {
        // ребро, исходящее из активного узла, начинающееся на букву в тексте, индекс которой хранит активная точка
        SuffixTreeEdge currentEdge = activePoint.activeNode.getChild(text.charAt(activePoint.activePointIndex));

        // проверка на то, наткнулись ли мы на повторения в тексте длиной более 1 символа (работа с активной точкой)
        if (activePoint.activePointIndex < currentIndex) {
            // проверяем не равена ли текущая буква букве в текущем ребре на нужной позиции
            if (text.charAt(currentIndex) == text.charAt(currentEdge.getStart() + currentIndex - activePoint.activePointIndex)) {
                // если совпадают, то проверям дальше (true - не добавляем новое ребро)
                return new Split(true, activePoint.activeNode);
            } else {
                // если не совпадают, создаем новое состояние, с помощью которого проще оперировать добавлением детей
                SuffixTreeNode newState = new SuffixTreeNode();
                int index = currentEdge.getStart() + currentIndex - activePoint.activePointIndex;

                // добавляем конец ребра, и создаем новый узел для реализации разветвления
                activePoint.activeNode.addChild(text.charAt(activePoint.activePointIndex),
                        new SuffixTreeEdge(currentEdge.getStart(), index - 1, newState));

                // заменяем текущей узел для дальнейшего добавления
                currentEdge.setStartIndex(index);
                // добавляем ребенка, являющегося до этого продолжением ребра
                newState.addChild(text.charAt(currentEdge.getStart()), currentEdge);

                return new Split(false, newState);
            }
        } else {

            boolean edgeExist = currentEdge != null;
            return new Split(edgeExist, activePoint.activeNode);
        }
    }

    // находит ближайший явный узел
    private void canonize(ActivePoint activePoint, int pIndex) {
        // обновление для активной точки, еслив дереве уще существует подстрока длины, большей чем 1
        if (pIndex - activePoint.activePointIndex > 0) {
            // текущее ребро, его длина
            SuffixTreeEdge edge = activePoint.activeNode.getChild(text.charAt(activePoint.activePointIndex));
            int edgeLength = edge.getLength();
            // пока ребро не является листом и длина строки активной точки >= длине строки на текущем ребре
            // индекс активной точки
            while (!edge.isLeaf() &&
                    edgeLength <= pIndex - activePoint.activePointIndex + 1) {

                activePoint.activePointIndex = activePoint.activePointIndex + edgeLength;
                activePoint.activeNode = edge.getNextNode();

                if (pIndex < activePoint.activePointIndex) {
                    break;
                }

                // находим и присваиваем новое ребро, так как прошли все текущее ребро
                edge = activePoint.activeNode.getChild(text.charAt(activePoint.activePointIndex));
            }
        }
    }
}
