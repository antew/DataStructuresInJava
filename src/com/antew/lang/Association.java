package com.antew.lang;

public class Association extends AbstractObject {
    protected Comparable key;
    protected Object value;

    public Association(Comparable key, Object value) {
        this.key = key;
        this.value = value;
    }
    
    public Association(Comparable key) {
        this(key, null);
    }
    
    public Comparable getKey() {
        return key;
    }
    
    public Object getValue() {
        return value;
    }
    
    @Override
    protected int compareTo(Comparable comp) {
        return key.compare(((Association) comp).getKey());
    }
    
    @Override
    public String toString() {
        String result = "Association { " + key;
        if (value != null) {
            result += ", " + value;
        }
        
        return result + " }";
    }
}
