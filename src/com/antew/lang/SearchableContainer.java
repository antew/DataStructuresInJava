package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface SearchableContainer extends Container {
    Comparable find(Comparable object);
    void insert(Comparable object) throws ContainerFullException;
    void remove(Comparable object) throws ContainerEmptyException;
    boolean isMemeber(Comparable object) throws ContainerEmptyException;
}
