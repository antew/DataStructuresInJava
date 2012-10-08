package com.antew.lang.util;

import com.antew.lang.AbstractHashTable;
import com.antew.lang.Comparable;
import com.antew.lang.Enumeration;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class OpenScatterTable extends AbstractHashTable {
    protected Entry[] hashTable;
    protected static final int EMPTY = 0;
    protected static final int OCCUPIED = 1;
    protected static final int DELETED = 2;
    
    public OpenScatterTable(int length) {
        hashTable = new Entry[length];
        
        for (int i = 0; i < length; i++)
            hashTable[i] = new Entry();
    }
    
    @Override
    public Comparable find(Comparable object) {
        int result = findMatch(object);
        if (result >= 0)
            return hashTable[result].object;
        
        return null;
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        if (count == getLength())
            throw new ContainerFullException();
        
        int hash = findUnoccupied(object);
        hashTable[hash].object = object;
        hashTable[hash].state = OCCUPIED;
        count++;
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        int hash = findInstance(object);
        if (hash < 0)
            throw new IllegalArgumentException("Object does not exist in the hash table");
        else {
            hashTable[hash].object = null;
            hashTable[hash].state = DELETED;
            count--;
        }
        
        
        
    }

    @Override
    public boolean isMemeber(Comparable object) throws ContainerEmptyException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void purge() {
        for (int i = 0; i < getLength(); i++)
            hashTable[i].purge();
        
        count = 0;
    }

    @Override
    public void accept(Visitor visitor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        // TODO Auto-generated method stub
        return null;
    }

    protected static int c(int i) {
        return i;
    }
    
    protected int findUnoccupied(Object object) throws ContainerFullException {
        int hash = h(object);
        for (int i = 0; i < count + 1; i++) {
            int probe = (hash + c(i)) % getLength();
            if (hashTable[probe].state != OCCUPIED)
                return probe;
        }
        
        throw new ContainerFullException();
    }
    
    protected int findMatch(Comparable object) {
        int hash = h(object);
        
        for (int i = 0; i < getLength(); i++) {
            int probe = (hash + c(i)) % getLength();
            if (hashTable[probe].state == EMPTY)
                break;
            if (hashTable[probe].state == OCCUPIED && object.isEQ(hashTable[probe].object))
                return probe;
        }
        
        return -1;
    }
    
    protected int findInstance(Comparable object) {
        int hash = h(object);
        
        for (int i = 0; i < getLength(); i++) {
            int probe = (hash + c(i)) % getLength();
            if (hashTable[probe].state == EMPTY)
                break;
            if (hashTable[probe].state == OCCUPIED && object == hashTable[probe].object)
                return probe;
        }
        
        return -1;
    }
    

    
    @Override
    public int getLength() {
        return hashTable.length;
    }

    protected static final class Entry {
        int state = EMPTY;
        Comparable object = null;;
        
        void purge() {
            state = EMPTY;
            object = null;
        }
    }
}
