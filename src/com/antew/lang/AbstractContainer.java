package com.antew.lang;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.util.AbstractVisitor;

public abstract class AbstractContainer extends AbstractObject implements Container {

    protected int count;
    
    public int getCount() {
        return count;
    }
    
    public boolean isEmpty() {
        return getCount() == 0;
    }
    
    public boolean isFull() {
        return false;
    }
    
    @Override
    protected int compareTo(Comparable comp) {
        return 0;
    }

    @Override
    public int hashCode() {
        Visitor visitor = new AbstractVisitor() {
            private int value;
            @Override
            public void visit(Object object) {
                value += object.hashCode();
            }

            @Override
            public int hashCode() {
                return value;
            }

        };
        accept(visitor);
        return getClass().hashCode() + visitor.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        Visitor visitor = new AbstractVisitor() {
            private boolean comma;

            @Override
            public void visit(Object object) {
                if (comma)
                    buffer.append(", ");
                buffer.append(object);
                comma = true;
            }

        };

        accept(visitor);
        return getClass().getName()     + " {" + buffer + "}";
    }

}
