package com.antew.lang;

public abstract class AbstractObject implements Comparable {
    //@formatter:off
    protected abstract int compareTo(Comparable comp);
    
    public boolean isLT(Comparable object) { return compare(object) <  0; }
    public boolean isLE(Comparable object) { return compare(object) <= 0; }
    public boolean isGT(Comparable object) { return compare(object) >  0; }
    public boolean isGE(Comparable object) { return compare(object) >= 0; }
    public boolean isEQ(Comparable object) { return compare(object) == 0; }
    public boolean isNE(Comparable object) { return compare(object) != 0; }
    
    public boolean equals(Comparable object) {  
        return object instanceof Comparable ? isEQ((Comparable) object) 
                                            : false; 
    }
    
    public int compare(Comparable comp) { 
        return getClass() == comp.getClass() ? compareTo(comp) 
                                             : getClass().getName().compareTo(comp.getClass().getName());
    }
    
    //@formatter:on
}
