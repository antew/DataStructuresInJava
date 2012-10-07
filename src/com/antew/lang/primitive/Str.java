package com.antew.lang.primitive;

import com.antew.lang.AbstractObject;
import com.antew.lang.Comparable;

public class Str extends AbstractObject {

    protected String value;
    private static final int shift = 6;
    private static final int mask = ~0 << (32 - shift);

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

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < value.length(); i++)
            result = (result & mask) ^
                (result << shift) ^ value.charAt(i);

        return result;
    }

}
