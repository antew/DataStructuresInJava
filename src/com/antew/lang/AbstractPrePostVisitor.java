package com.antew.lang;

public class AbstractPrePostVisitor implements PrePostVisitor {

    @Override
    public void preVisit(Object object) {
    }

    @Override
    public void inVisit(Object object) {
    }

    @Override
    public void postVisit(Object object) {
    }

    @Override
    public boolean isDone() {
        return false;
    }
    
}
