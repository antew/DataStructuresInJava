package com.antew.lang;

public interface Matrix {
    double get(int i, int j);
    void put(int i, int j, double d);
    Matrix transpose();
    Matrix add(Matrix matrix);
    Matrix multiply(Matrix matrix);
}
