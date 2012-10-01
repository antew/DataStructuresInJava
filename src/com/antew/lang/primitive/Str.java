package com.antew.lang.primitive;

import com.antew.lang.AbstractObject;
import com.antew.lang.Comparable;

public class Str extends AbstractObject {

    protected String value;

    public Str(String value) {
        this.value = value;
    }
    
    public String stringValue() {
        return value;
    }
    
    @Override
    protected int compareTo(Comparable comp) {
        return value.compareTo(((Str) comp).value);
    }

}
