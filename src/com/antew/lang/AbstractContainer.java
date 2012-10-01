package com.antew.lang;

public abstract class AbstractContainer extends AbstractObject implements Container {

    protected int count;
    
    public int getCount() {
        return count;
    }
    
    public boolean isEmpty() {
        return getCount() == 0;
    }
    
    public boolean isFull() {
        return false;
    }
    
    @Override
    protected int compareTo(Comparable comp) {
        return 0;
    }

}
