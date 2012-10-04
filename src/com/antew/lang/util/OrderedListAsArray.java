package com.antew.lang.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

import com.antew.lang.AbstractSearchableContainer;
import com.antew.lang.Comparable;
import com.antew.lang.Cursor;
import com.antew.lang.Enumeration;
import com.antew.lang.OrderedList;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.exception.InvalidOperationException;

public class OrderedListAsArray extends AbstractSearchableContainer implements
        OrderedList {

    protected Comparable array[];

    public OrderedListAsArray(int size) {
        this.array = new Comparable[size];
    }

    @Override
    public Comparable find(Comparable object) {
        for (int i = 0; i < count; i++) {
            if (array[i].isEQ(object))
                return array[i];
        }

        return null;
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        if (count == array.length)
            throw new ContainerFullException();

        array[count] = object;
        count++;
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();

        int i = 0;
        while (i < count && array[i] != object)
            i++;

        if (i == count)
            throw new IllegalArgumentException("Item not found");

        for (; i < count - 1; i++)
            array[i] = array[i+1];

        array[i] = null;

        count--;
    }

    @Override
    public boolean isMemeber(Comparable object) {
        for (int i = 0; i < count; i++) {
            if (array[i] == object)
                return true;
        }

        return false;
    }

    @Override
    public void purge() {
        for (int i = 0; i < count; i++)
            array[i] = null;

        count = 0;
    }

    @Override
    public void accept(Visitor visitor) throws ContainerEmptyException {
        for (int i = 0; i < count && !visitor.isDone(); i++)
            visitor.visit(array[i]);
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            protected int position = 0;

            @Override
            public boolean hasMoreElements() {
                return position < getCount();
            }

            @Override
            public Object nextElement() throws NoSuchElementException {
                if (position >= getCount())
                    throw new NoSuchElementException();

                return array[position++];
            }

        };
    }

    @Override
    public Comparable get(int i) throws ContainerEmptyException {
        if (i < 0 || i >= count)
            throw new IndexOutOfBoundsException();

        if (count == 0)
            throw new ContainerEmptyException();

        return array[i];
    }

    @Override
    public Cursor findPosition(Comparable object) {
        int i = 0;
        while (i < count && array[i].isNE(object))
            i++;

        return new ArrayCursor(i);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(array);
    }

    protected class ArrayCursor implements Cursor {
        int position;

        public ArrayCursor(int position) {
            this.position = position;
        }

        @Override
        public Comparable getDatum() {
            if (position < 0 || position >= count)
                throw new IndexOutOfBoundsException();

            return array[position];
        }

        @Override
        public void insertAfter(Comparable object) throws ContainerFullException, InvalidOperationException {
            if (count == array.length)
                throw new ContainerFullException();

            if (position < 0 || position >= count)
                throw new IndexOutOfBoundsException();

            int newPos = position + 1;

            for (int i = count; i > newPos; i--)
                array[i] = array[i - 1];

            array[newPos] = object;
            count++;
        }

        @Override
        public void insertBefore(Comparable object) throws ContainerFullException, InvalidOperationException {
            if (count == array.length)
                throw new ContainerFullException();

            if (position < 0 || position >= count)
                throw new IndexOutOfBoundsException();

            int newPos = position;

            for (int i = count; i > newPos; i--)
                array[i] = array[i - 1];

            array[newPos] = object;
            count++;
        }

        @Override
        public void remove() throws ContainerEmptyException {
            if (position < 0 || position >= count)
                throw new IndexOutOfBoundsException();

            if (count == 0)
                throw new ContainerEmptyException();

            // Reposition the elements
            int i = position;
            while (i < count - 1) {
                array[i] = array[i + 1];
            }

            count--;
        }

        @Override
        public String toString() {
            return Integer.toString(position);
        }

    }

}
