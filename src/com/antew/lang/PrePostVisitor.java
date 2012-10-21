package com.antew.lang;

public interface PrePostVisitor {
    void preVisit(Object object);
    void inVisit(Object object);
    void postVisit(Object object);
    boolean isDone();
}
