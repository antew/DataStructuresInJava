package com.antew.lang.util;

import com.antew.lang.Comparable;
import com.antew.lang.exception.ContainerFullException;

public class SortedListAsLinkedList extends OrderedListAsLinkedList implements SortedList {

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        LinkedList.Element current;
        LinkedList.Element previous = null;

        for (current = list.getHead(); current != null; current = current.getNext()) {
            if (object.isGE((Comparable) current.getData()))
                break;

            previous = current;
        }

        if (previous == null)
            list.prepend(object);
        else
            previous.insertAfter(object);

        count++;
    }


}
