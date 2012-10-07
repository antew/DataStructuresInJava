package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractSearchableContainer;
import com.antew.lang.Comparable;
import com.antew.lang.Cursor;
import com.antew.lang.Enumeration;
import com.antew.lang.OrderedList;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class OrderedListAsLinkedList extends AbstractSearchableContainer implements OrderedList {
    protected LinkedList list;

    public OrderedListAsLinkedList() {
        this.list = new LinkedList();
    }

    @Override
    public Comparable find(Comparable object) {
        for (LinkedList.Element e = list.getHead(); e!= null; e = e.getNext()) {
            Comparable c = (Comparable) e.getData();
            if (c.isEQ(object))
                return c;
        }

        return null;
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        list.append(object);
        count++;
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();

        list.remove(object);
        count--;
    }

    @Override
    public boolean isMemeber(Comparable object) {
        for (LinkedList.Element e = list.getHead(); e != null; e = e.getNext())
            if (((Comparable) e.getData()) == object)
                return true;

        return false;
    }

    @Override
    public void purge() {
        list.purge();
        count = 0;
    }

    @Override
    public void accept(Visitor visitor) {
        for (LinkedList.Element e = list.getHead(); e != null && !visitor.isDone(); e = e.getNext())
            visitor.visit(e);
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            protected LinkedList.Element current = list.getHead();

            @Override
            public boolean hasMoreElements() {
                return current != null;
            }

            @Override
            public Object nextElement() throws NoSuchElementException {
                if (current == null)
                    throw new NoSuchElementException();

                Object data = current.getData();
                current = current.getNext();

                return data;
            }

        };
    }

    @Override
    public Comparable get(int i) throws ContainerEmptyException {
        if (i < 0 || i >= count)
            throw new IndexOutOfBoundsException();

        LinkedList.Element element = list.getHead();
        for (int j = 0; j < i && element != null; j++)
            element = element.getNext();

        return (Comparable) element.getData();
    }

    @Override
    public Cursor findPosition(Comparable object) {
        return null;
    }

    protected class LinkedListCursor implements Cursor {
        LinkedList.Element element;

        public LinkedListCursor(LinkedList.Element element) {
            this.element = element;
        }

        @Override
        public Comparable getDatum() {
            return (Comparable) element.getData();
        }

        @Override
        public void insertAfter(Comparable object)
                throws ContainerEmptyException, ContainerFullException {
            element.insertAfter(object);
            count++;
        }

        @Override
        public void insertBefore(Comparable object)
                throws ContainerEmptyException, ContainerFullException {
            element.insertBefore(object);
            count++;
        }

        @Override
        public void remove() throws ContainerEmptyException {
            list.remove(element.getData());
            count--;
        }

    }

}
