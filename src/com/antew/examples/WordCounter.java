package com.antew.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;

import com.antew.lang.Association;
import com.antew.lang.HashTable;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.primitive.Int;
import com.antew.lang.primitive.Str;
import com.antew.lang.util.ChainedHashTable;

public class WordCounter {
    private static final class Counter extends Int  {
        public Counter(int value) {
            super(value);
        }
        
        void increment() {
            value++;
        }
    }
    
    public static void wordCounter(Reader in, PrintWriter out) throws IOException, ContainerFullException {
        // Requires a prime size
        HashTable table = new ChainedHashTable(1031);
        StreamTokenizer st = new StreamTokenizer(in);
        
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            String word = st.sval;
            
            // Try to find the object in the hash table
            Object obj = table.find(new Association(new Str(word)));
            
            if (obj != null) {
                ((Counter) ((Association) obj).getValue()).increment();
            } else {
                table.insert(new Association(new Str(word), 1));
            }
        }
        
        out.println(table);
    }
}
