package com.antew.lang.util;

import com.antew.lang.Comparable;
import com.antew.lang.Cursor;
import com.antew.lang.SearchableContainer;
import com.antew.lang.exception.ContainerEmptyException;

public interface SortedList extends SearchableContainer {
    Comparable get(int i) throws ContainerEmptyException;
    Cursor findPosition(Comparable object);
}
