package com.antew.lang.exception;

public class ContainerEmptyException extends Exception {
    public ContainerEmptyException() {
        super();
    }
    
    public ContainerEmptyException(String message) {
        super(message);
    }
}
