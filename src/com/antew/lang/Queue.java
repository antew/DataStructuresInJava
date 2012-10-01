package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface Queue extends Container {
    Object getHead() throws ContainerEmptyException;
    void enqueue(Object object) throws ContainerFullException;
    Object dequeue() throws ContainerEmptyException;
}
