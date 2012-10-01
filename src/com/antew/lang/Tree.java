package com.antew.lang;

public interface Tree {
    Object getKey();
    int getDegree();
    Tree getSubtree(int i);
}
