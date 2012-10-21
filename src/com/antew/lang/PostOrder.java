package com.antew.lang;

public class PostOrder extends AbstractPrePostVisitor {
    protected Visitor visitor;
    
    public PostOrder(Visitor visitor) {
        this.visitor = visitor;
    }
    
    @Override
    public void postVisit(Object object) {
        visitor.visit(object);
    }
    
    @Override
    public boolean isDone() {
        return visitor.isDone();
    }
}
