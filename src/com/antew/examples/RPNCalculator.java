package com.antew.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import com.antew.lang.Stack;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.primitive.Int;
import com.antew.lang.util.StackAsLinkedList;

public class RPNCalculator {
    enum Operations {
        ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/"), EQUALS("=");
        
        private String operation;
        
        Operations(String operation) {
            this.operation = operation;
        }
    }
    public static void calculator(Reader in, PrintWriter out) throws ContainerFullException, IOException, ContainerEmptyException {
        Stack stack = new StackAsLinkedList();
        int i;
        while ((i = in.read()) > 0) {
            char c = (char) i;
            
            if (Character.isDigit(c))
                stack.push(new Int(Character.digit(c, 10)));
            else {

                switch (Operations.valueOf(Character.toString(c))) {
                    case ADDITION:
                        Int operand1 = (Int) stack.pop();
                        Int operand2 = (Int) stack.pop();
                        stack.push(new Int(operand1.intValue() + operand2.intValue()));
                        break;
                        
                    case SUBTRACTION:
                        operand1 = (Int) stack.pop();
                        operand2 = (Int) stack.pop();
                        stack.push(new Int(operand1.intValue() - operand2.intValue()));
                        
                        break;
                        
                    case DIVISION:
                        operand1 = (Int) stack.pop();
                        operand2 = (Int) stack.pop();
                        stack.push(new Int(operand1.intValue() / operand2.intValue()));
                        break;
                        
                    case MULTIPLICATION:
                        operand1 = (Int) stack.pop();
                        operand2 = (Int) stack.pop();
                        stack.push(new Int(operand1.intValue() * operand2.intValue()));
                        break;
                        
                    case EQUALS:
                        operand1 = (Int) stack.pop();
                        out.println(operand1);
                        break;
                }
            }
            
        }
    }
}
