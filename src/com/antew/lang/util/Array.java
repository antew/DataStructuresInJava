package com.antew.lang.util;

import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;

public class Array {
    protected Object[] data;
    protected int base;
    
    public Array(int length, int base) {
        data = new Object[length];
        this.base = base;
    }
    
    public Array() {
        this(0,0);
    }
    
    public Array(int length) {
        this(length, 0);
    }
    
    public Object[] getData() {
        return data;
    }
    
    public int getBase() {
        return base;
    }
    
    public int getLength() {
        return data.length;
    }
    
    public void setBase(int base) {
        this.base = base;
    }
    
    public void setLength(int newLength) {
        if (data.length != newLength) {
            Object[] newData = new Object[newLength];
            int min = data.length < newLength ? data.length : newLength;
            
            for (int i = 0; i < min; i++)
                newData[i] = data[i];
            
            data = newData;
        }
    }
    
    public Object get(int index) {
        return data[index - base];
    }
    
    public void put(int index, Object object) {
        data[index - base] = object;
    }
    
    public void assign(Array array) {
        if (array != this) {
            if (data.length != array.data.length) {
                data = new Object[array.data.length];
                
                for (int i = 0; i < data.length; i++)
                    data[i]  = array.data[i];
                
                base = array.base;
            }
        }
    }
    
}
