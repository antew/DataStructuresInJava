package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractContainer;
import com.antew.lang.Enumeration;
import com.antew.lang.Stack;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class StackAsLinkedList extends AbstractContainer implements Stack {

    protected LinkedList stack;
    
    public StackAsLinkedList() {
        stack = new LinkedList();
    }
    
    @Override
    public void purge() {
        stack.purge();
        count = 0;
    }

    @Override
    public void accept(Visitor visitor) throws ContainerEmptyException {
        for (LinkedList.Element element = stack.getHead(); element != null && !visitor.isDone(); element = element.getNext())
            visitor.visit(element);
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            protected LinkedList.Element curElement = stack.getHead();
            
            @Override
            public Object nextElement() throws NoSuchElementException {
                if (curElement == null)
                    throw new NoSuchElementException();
                
                Object data = curElement.getData();
                curElement = curElement.getNext();
                
                return data;
            }
            
            @Override
            public boolean hasMoreElements() {
                return curElement != null;
            }
        };
        
    }

    @Override
    public Object getTop() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        return stack.getHead();
    }

    @Override
    public Object pop() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        Object result = stack.getTail();
        stack.remove(result);
        count--;
        
        return result;
    }

    @Override
    public void push(Object object) throws ContainerFullException {
        stack.prepend(object);
        count++;
    }

}
