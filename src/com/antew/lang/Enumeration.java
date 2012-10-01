package com.antew.lang;

import java.util.NoSuchElementException;

public interface Enumeration {
    boolean hasMoreElements();
    Object nextElement() throws NoSuchElementException;
}
