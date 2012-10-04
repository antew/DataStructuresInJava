package com.antew.lang.util;


public class LinkedList {
    private Element head;
    private Element tail;

    public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void purge() {
        head = null;
        tail = null;
    }

    public void prepend(Object obj) {
        Element temp = new Element(obj, null);
        if (head == null)
            tail = temp;

        head = temp;

    }

    public void append(Object obj) {
        Element temp = new Element(obj, null);
        if (head == null)
            head = temp;
        else
            tail.next = temp;

        tail = temp;
    }

    public void assign(LinkedList list) {
        if (list != this) {
            purge();

            for (Element current = list.head; current != null; current = current.next)
                append(current.data);

        }
    }

    public void remove(Object item) {
        Element temp = head;
        Element previous = null;

        while (temp != null && temp.data != item) {
            previous = temp;
            temp = temp.next;
        }

        if (temp == null)
            throw new IllegalArgumentException("Item not found!");

        // If the found item is the head of the list, re-assign the head element
        if (temp == head)
            head = temp.next;
        else
            previous.next = temp.next;

        if (temp == tail)
            tail = previous;
    }

    public final class Element {
        private Object data;
        private Element next;

        public Element(Object data, Element next) {
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public Element getNext() {
            return next;
        }

        public void insertAfter(Object item) {
            next = new Element(item, next);
            if (tail == this)
                tail = next;
        }

        public void insertBefore(Object item) {
            // Set the new object to have the current object as its successor
            Element temp = new Element(item, this);

            // Make 'item' the new head of the list if necessary
            if (this == head)
                head = temp;
            else {
                Element previous = head;
                // Walk through the list until we find the element before this one
                while (previous != null && previous.next != this)
                    previous = previous.next;

                previous.next = temp;
            }
        }
    }
}
