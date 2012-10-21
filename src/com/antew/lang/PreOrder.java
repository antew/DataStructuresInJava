package com.antew.lang;

public class PreOrder extends AbstractPrePostVisitor {
    protected Visitor visitor;
    
    public PreOrder(Visitor visitor) {
        this.visitor = visitor;
    }
    
    @Override
    public void preVisit(Object object) {
        visitor.visit(object);
    }
    
    @Override
    public boolean isDone() {
        return visitor.isDone();
    }
}
