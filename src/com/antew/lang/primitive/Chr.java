package com.antew.lang.primitive;

import com.antew.lang.AbstractObject;
import com.antew.lang.Comparable;

public class Chr extends AbstractObject {
    protected char value;
    
    public Chr(char value) {
        this.value = value;
    }
    
    public char charValue() {
        return value;
    }
    
    @Override
    protected int compareTo(Comparable comp) {
        Chr chr = (Chr) comp;
        return (int) value - (int) chr.value;
    }

}
