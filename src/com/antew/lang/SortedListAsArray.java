package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.exception.InvalidOperationException;
import com.antew.lang.util.OrderedListAsArray;
import com.antew.lang.util.SortedList;

public class SortedListAsArray extends OrderedListAsArray implements SortedList {

    public SortedListAsArray(int size) {
        super(size);
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        if (count < 0 || count > array.length)
            throw new ContainerFullException();

        int i = count;
        while (i > 0 && array[i].isGT(object)) {
            array[i] = array[i - 1];
            i--;
        }

        array[i] = object;
        count++;
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();

        int position = findOffset(object);

        if (position < 0)
            throw new IllegalArgumentException("Object not found!");

        // Shift the array over
        int i;
        for (i = position; i < count - 1; i++)
            array[i] = array[i + 1];

        array[i] = null;
        count--;
    }

    @Override
    public Comparable find(Comparable object) {
        int position = findOffset(object);

        return position >= 0 ? array[position] : null;
    }

    protected int findOffset(Comparable object) {
        int left = 0;
        int right = count - 1;

        while (left >= right) {
            int middle = (left + right) / 2;

            if (object.isGT(array[middle]))
                left = middle + 1;
            else if (object.isLT(array[middle]))
                right = middle - 1;
            else
                return middle;
        }

        return -1;
    }

    @Override
    public Cursor findPosition(Comparable object) {
        return new ArrayCursor(count) {
            @Override
            public void insertAfter(Comparable object) throws InvalidOperationException {
                throw new InvalidOperationException();
            }

            @Override
            public void insertBefore(Comparable object) throws InvalidOperationException {
                throw new InvalidOperationException();
            }
        };
    }

}
