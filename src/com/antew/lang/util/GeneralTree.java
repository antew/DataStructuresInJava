package com.antew.lang.util;

import com.antew.lang.AbstractTree;
import com.antew.lang.Tree;

public class GeneralTree extends AbstractTree {
    protected Object key;
    protected int degree;
    protected LinkedList list;
    
    public GeneralTree(Object key) {
        this.key = key;
        degree = 0;
        list = new LinkedList();
    }
    
    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Tree getSubtree(int i) {
        if (i < 0 || i >= degree)
            throw new IndexOutOfBoundsException();
        
        LinkedList.Element ptr = list.getHead();
        for (int j = 0; j < i; j++) {
            ptr = ptr.getNext();
        }
        
        return (GeneralTree) ptr.getData();
    }
    
    public void attachSubtree(GeneralTree t) {
        list.append(t);
        degree++;
    }
    
    public GeneralTree detachSubtree(GeneralTree t) {
        list.remove(t);
        degree--;
        return t;
    }

    @Override
    public boolean isLeaf() {
        return degree == 0;
    }

    @Override
    public int getDegree() {
        return degree;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void purge() {
        list.purge();
        degree = 0;
    }

}
