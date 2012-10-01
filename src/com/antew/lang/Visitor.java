package com.antew.lang;

public interface Visitor {
    void visit(Object object);
    boolean isDone();
}
