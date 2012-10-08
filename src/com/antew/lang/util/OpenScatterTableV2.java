package com.antew.lang.util;

import com.antew.lang.Comparable;
import com.antew.lang.exception.ContainerEmptyException;

public class OpenScatterTableV2 extends OpenScatterTable {

    public OpenScatterTableV2(int length) {
        super(length);
    }
    
    @Override
    public void remove(Comparable object) throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        int i = findInstance(object);
        if (i < 0)
            throw new IllegalArgumentException("Object not in table");

        // Shift items in the container so that we avoid the negative impact of just marking items 'DELETED'
        for (;;) {
            int j = (i + 1) % getLength();
            while (hashTable[i].state == OCCUPIED) {
                int h = h(hashTable[j].object);
                if ((h <= i && i < j) || (i < j && j < h) || (j < h && h <= i))
                    break;
                
                j = (j + 1) % getLength();
            }
            
            if (hashTable[j].state == EMPTY)
                break;
            
            // Do the actual shift
            hashTable[i].object = hashTable[j].object;
            hashTable[i].state = hashTable[j].state;
            i = j;
        }
        
        hashTable[i].object = null;
        hashTable[i].state = EMPTY;
        count--;
    }

}
