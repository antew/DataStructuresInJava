package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface Stack extends Container {
    Object getTop() throws ContainerEmptyException;
    Object pop() throws ContainerEmptyException;
    void push(Object object) throws ContainerFullException;
}
