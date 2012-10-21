package com.antew.lang;

public class InOrder extends AbstractPrePostVisitor {
    protected Visitor visitor;
    
    public InOrder(Visitor visitor) {
        this.visitor = visitor;
    }
    
    @Override
    public void inVisit(Object object) {
        visitor.visit(object);
    }
    
    @Override
    public boolean isDone() {
        return visitor.isDone();
    }
}
