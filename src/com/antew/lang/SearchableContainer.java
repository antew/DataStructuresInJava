package com.antew.lang;

public interface SearchableContainer extends Container {
    Comparable find(Comparable object);
    void insert(Comparable object);
    void withdraw(Comparable object);
    boolean isMemeber(Comparable object);
}
