package com.antew.lang.util;

public class MultiDimensionalArray {
    int[] dimensions;
    int[] factors;
    Object[] data;
    
    public MultiDimensionalArray(int[] array) {
        dimensions = new int[array.length];
        factors = new int[array.length];
        int product = 1;
        
        for (int i = array.length - 1; i >= 0; i--) {
            dimensions[i] = array[i];
            factors[i] = product;
            product *= dimensions[i];
        }
        
        data = new Object[product];
    }
    
    protected int getOffset(int[] indices) {
        if (indices.length != dimensions.length)
            throw new IllegalArgumentException("Wrong number of indices!");
        
        int offset = 0;
        for (int i = 0; i < dimensions.length; ++i) {
            if (indices[i] < 0 || indices[i] >= dimensions[i])
                throw new IndexOutOfBoundsException("Index " + indices[i] + " is out of bounds! Current dimension = " + dimensions[i]);
            
            offset += factors[i] * indices[i];
        }
        
        return offset;
    }
}
