package com.antew.examples;

import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;
import com.antew.lang.primitive.Int;
import com.antew.lang.util.OrderedListAsArray;
import com.antew.lang.Comparable;

public class Main {

    /**
     * @param args
     * @throws ContainerFullException
     * @throws ContainerEmptyException
     */
    public static void main(String[] args) throws ContainerFullException, ContainerEmptyException {
        OrderedListAsArray orderedListArray = new OrderedListAsArray(10);
        Int i = new Int(9);
        orderedListArray.insert(new Int(1));
        orderedListArray.insert(new Int(2));
        orderedListArray.insert(new Int(3));
        orderedListArray.insert(new Int(4));
        orderedListArray.insert(new Int(5));
        orderedListArray.insert(new Int(6));
        orderedListArray.insert(new Int(7));
        orderedListArray.insert(new Int(8));
        orderedListArray.insert(i);

        System.out.println("Finding " + i + " = " + orderedListArray.find(i));
        System.out.println("Finding Position of " + i + " = " + orderedListArray.findPosition(i));
        System.out.println("Full array = " + orderedListArray.toString());

        orderedListArray.remove(i);

        // Remove '9' from the list
        System.out.println("After removing " + i + " array = " + orderedListArray.toString());

    }

}
