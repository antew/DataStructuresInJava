package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractHashTable;
import com.antew.lang.Comparable;
import com.antew.lang.Enumeration;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class ChainedHashTable extends AbstractHashTable {

    protected LinkedList[] hashTable;
    
    public ChainedHashTable(int count) {
        hashTable = new LinkedList[count];
        
        for (int i = 0; i < count; i++)
            hashTable[i] = new LinkedList();
    }
    @Override
    public double getLoadFactor() {
        return 0;
    }

    @Override
    public Comparable find(Comparable object) {
        LinkedList temp = hashTable[h(object)];
        for (LinkedList.Element e = temp.getHead(); e != null; e.getNext()) {
            Comparable c = (Comparable) e.getData();
            if (c.isEQ(object))
                return c;
        }
        
        return null;
    }

    @Override
    public void insert(Comparable object) throws ContainerFullException {
        hashTable[h(object)].append(object);
        count++;
    }

    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        hashTable[h(object)].remove(object);
        count--;
    }

    @Override
    public boolean isMemeber(Comparable object) throws ContainerEmptyException {
        LinkedList temp = hashTable[h(object)];
        for (LinkedList.Element e = temp.getHead(); e != null; e.getNext()) {
            Comparable c = (Comparable) e.getData();
            if (c.isEQ(object))
                return true;
        }
        
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
        
        for (int i = 0; i < getLength(); i++) {
            LinkedList temp = hashTable[i];
            for (LinkedList.Element e = temp.getHead(); e != null; e.getNext()) {
                visitor.visit(e.getData());
            }
            
        }
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            int visited = 0;
            int currentIndex = 0;
            LinkedList.Element ptr;
            
            @Override
            public Object nextElement() throws NoSuchElementException {
                // If we're already working through a linked list get the next element (if one exists)
                if (ptr != null && ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    return ptr;
                }
                else {
                    while (hashTable[currentIndex].getHead() == null)
                    {
                        currentIndex++;
                    }
                    
                    ptr = hashTable[currentIndex].getHead();
                    return ptr;
                }
            }
            
            @Override
            public boolean hasMoreElements() {
                return (visited < count);
            }
        };
    }

    @Override
    public int getLength() {
        return hashTable.length;
    }

}
