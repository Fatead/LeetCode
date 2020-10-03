package listTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;
    private int pos;

    private void getElementFromList(List<NestedInteger> nestedList){
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                getElementFromList(nestedInteger.getList());
            }
        }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        pos = 0;
        getElementFromList(nestedList);
    }

    @Override
    public Integer next() {
        return list.get(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < list.size();
    }

}
