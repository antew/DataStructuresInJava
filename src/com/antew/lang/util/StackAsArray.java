package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractContainer;
import com.antew.lang.Enumeration;
import com.antew.lang.Stack;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class StackAsArray extends AbstractContainer implements Stack {

    protected Object[] stack;

    public StackAsArray(int size) {
        stack = new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return count == stack.length;
    }

    @Override
    public void purge() {
        while (count > 0)
            stack[count--] = null;
    }

    @Override
    public void accept(Visitor visitor) {
        for (int i = 0; i < stack.length && !visitor.isDone(); i++)
            visitor.visit(stack[i]);
    }

    @Override
    public Enumeration getEnumeration() {
        return new Enumeration() {

            protected int position = 0;

            @Override
            public Object nextElement() throws NoSuchElementException {
                if (position >= getCount())
                    throw new NoSuchElementException();

                return stack[position++];
            }

            @Override
            public boolean hasMoreElements() {
                return position < getCount();
            }
        };
    }

    @Override
    public Object getTop() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException("Stack is empty!");

        return stack[count -1];
    }

    @Override
    public void push(Object object) throws ContainerFullException {
        if (count == stack.length)
            throw new ContainerFullException("Stack is full!");

        stack[count++] = object;


    }

    @Override
    public Object pop() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException("Stack is empty!");

        Object retVal = stack[--count];
        stack[count] = null;

        return retVal;
    }

}
