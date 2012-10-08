package com.antew.lang.util;

import com.antew.lang.AbstractHashTable;
import com.antew.lang.Comparable;
import com.antew.lang.Enumeration;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class ChainedScatterTable extends AbstractHashTable {

    private static final int nil = -1; 
    protected Entry[] hashTable;
    
    public ChainedScatterTable(int length) {
        hashTable = new Entry[length];
        for (int i = 0; i < length; i++)
            hashTable[i] = new Entry();
    }
    @Override
    public Comparable find(Comparable object) {
        for (int hashCode = h(object); hashCode != nil; hashCode = hashTable[hashCode].next) {
            if (object.isEQ(hashTable[hashCode].object))
                return hashTable[hashCode].object;
        }
        
        return null;
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        if (count == getLength())
            throw new ContainerFullException();
        
        int hashCode = h(object);
        if (hashTable[hashCode].object != null) {
            // Find the end of the chain
            while (hashTable[hashCode].next != nil)
                hashCode = hashTable[hashCode].next;
            
            // Find an unused array position
            int chainTail = hashCode;
            hashCode = (hashCode + 1) % getLength();
            while (hashTable[hashCode].object != null)
                hashCode = (hashCode + 1) % getLength();
            
            // Link the last element of the chain to our new element
            hashTable[chainTail].next = hashCode;
        }
        
        hashTable[hashCode].object = object;
        hashTable[hashCode].next = nil;
        count++;
        
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        int i = h(object);
        
        // Walk through the chain until we find the object to remove
        while (i != nil && object != hashTable[i].object)
            i = hashTable[i].next;
        
        // If we reached the end of the chain but didn't find the object, it doesn't exist
        if (i == nil)
            throw new IllegalArgumentException("Object is not in the hash table!");
        
        // We need to fill the empty space in the array we will have once we remove this item
        for (;;) {
            int j = hashTable[i].next;
            
            while (j != nil) {
                int h = h(hashTable[j].object);
                boolean found = false;
                for (int k = hashTable[i].next; k != hashTable[j].next && !found; k = hashTable[k].next) {
                    if (k == h)
                        found = true;
                }
                if (!found)
                    break;
                
                j = hashTable[j].next;
            }
            
            if (j == nil)
                break;
            
            // Move the item down the list
            hashTable[i].object = hashTable[j].object;
            i = j;
        }
        // Remove the item from the list
        hashTable[i].object = null;
        hashTable[i].next = nil;
        
        // Remove the 'next' pointer to the item we removed
        for (int j = (i + getLength() - 1) % getLength(); j != i; j = (j + getLength() - 1) % getLength()) {
            if (hashTable[j].next == i) {
                hashTable[j].next = nil;
                break;
            }
        }
        count--;
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

    @Override
    public int getLength() {
        return hashTable.length;
    }

    protected static final class Entry {
        Comparable object;
        int next = nil;
        
        void purge() {
            object = null;
            next = nil;
        }
    }
}
