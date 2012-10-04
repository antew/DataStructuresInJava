package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface Cursor {
    Comparable getDatum();
    void insertAfter(Comparable object) throws ContainerEmptyException, ContainerFullException;
    void insertBefore(Comparable object) throws ContainerEmptyException, ContainerFullException;
    void remove() throws ContainerEmptyException;

}
