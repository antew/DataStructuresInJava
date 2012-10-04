package com.antew.lang.primitive;

import com.antew.lang.AbstractObject;
import com.antew.lang.Comparable;

public class Int extends AbstractObject {

    protected int value;

    public Int(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    protected int compareTo(Comparable comp) {
        Int integer = (Int) comp;
        long difference = (long) value - (long) integer.value;

        if (difference < 0)
            return -1;
        else if (difference > 0)
            return 1;

        return 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
