package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;

public interface Container extends Comparable{
    boolean isEmpty();
    boolean isFull();
    void purge();
    void accept(Visitor visitor);
    Enumeration getEnumeration() throws ContainerEmptyException;
}
