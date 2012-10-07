package com.antew.lang;

public abstract class AbstractHashTable extends AbstractSearchableContainer implements HashTable {
    public abstract int getLength();

    protected final int f(Object object) {
        return object.hashCode();
    }

    protected final int g(int x) {
        return Math.abs(x) % getLength();
    }

    protected final int h(Object object) {
        return g(f(object));
    }
}
