package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;

public interface OrderedList extends SearchableContainer {
    Comparable get(int i) throws ContainerEmptyException;
    Cursor findPosition(Comparable object);
}
