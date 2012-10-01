package com.antew.lang;

public interface Comparable {
    boolean isLT(Comparable object);
    boolean isLE(Comparable object);
    boolean isGT(Comparable object);
    boolean isGE(Comparable object);
    boolean isEQ(Comparable object);
    boolean isNE(Comparable object);
    int compare (Comparable object);
}
