package com.antew.lang;

import java.util.NoSuchElementException;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.util.QueueAsLinkedList;
import com.antew.lang.util.StackAsLinkedList;

public abstract class AbstractTree extends AbstractContainer implements Tree {

    public void depthFirstTraversal(PrePostVisitor visitor) {
        if (visitor.isDone())
            return;
        if (!isEmpty()) {
            visitor.preVisit(getKey());
            for (int i = 0; i < getDegree(); i++)
                getSubtree(i).depthFirstTraversal(visitor);
            
            visitor.postVisit(getKey());
        }
    }
    
    @Override
    public void breadthFirstTraversal(Visitor visitor) throws ContainerFullException, ContainerEmptyException {
        Queue queue = new QueueAsLinkedList();
        if (!isEmpty())
            queue.enqueue(this);
        
        while (!queue.isEmpty() && !visitor.isDone()) {
            Tree head = (Tree) queue.dequeue();
            visitor.visit(head.getKey());
            
            // Visit all of the siblings before moving on to the next level
            for (int i = 0; i < head.getDegree(); i++) {
                Tree child = head.getSubtree(i);
                if (!child.isEmpty())
                    queue.enqueue(child);
            }
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        depthFirstTraversal(new PreOrder(visitor));
    }
    
    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new TreeEnumeration();
    }
    
    protected class TreeEnumeration implements Enumeration {
        protected Stack stack;
        
        public TreeEnumeration() {
            stack = new StackAsLinkedList();
            if (!isEmpty())
                try {
                    stack.push(AbstractTree.this);
                } catch (ContainerFullException e) {
                    e.printStackTrace();
                }
        }
        
        @Override
        public boolean hasMoreElements() {
            return false;
        }

        @Override
        public Object nextElement() throws NoSuchElementException {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
}
