package com.antew.lang.primitive;

import com.antew.lang.AbstractObject;
import com.antew.lang.Comparable;

public class Dbl extends AbstractObject {
    protected double value;

    public Dbl(double value) {
        this.value = value;
    }

    public double doubleValue() {
        return value;
    }

    @Override
    protected int compareTo(Comparable comp) {
        Dbl dbl = (Dbl) comp;

        if (value < dbl.value)
            return -1;
        else if (value > dbl.value)
            return 1;

        return 0;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}
