package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public interface Tree extends Container {
    Object getKey();
    Tree getSubtree(int i);
    boolean isEmpty();
    boolean isLeaf();
    int getDegree();
    int getHeight();
    void depthFirstTraversal(PrePostVisitor visitor);
    void breadthFirstTraversal(Visitor visitor) throws ContainerFullException, ContainerEmptyException;
    
    
}
