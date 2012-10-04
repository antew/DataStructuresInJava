package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.exception.InvalidOperationException;

public interface Cursor {
    Comparable getDatum();
    void insertAfter(Comparable object) throws ContainerEmptyException, ContainerFullException, InvalidOperationException;
    void insertBefore(Comparable object) throws ContainerEmptyException, ContainerFullException, InvalidOperationException;
    void remove() throws ContainerEmptyException;

}
