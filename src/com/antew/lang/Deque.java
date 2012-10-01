package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface Deque extends Container {
    Object getHead() throws ContainerEmptyException;
    Object getTail() throws ContainerEmptyException;
    void enqueueHead(Object object) throws ContainerFullException;
    void enqueueTail(Object object) throws ContainerFullException;
    Object dequeueHead() throws ContainerEmptyException;
    Object dequeueTail() throws ContainerEmptyException;
}
