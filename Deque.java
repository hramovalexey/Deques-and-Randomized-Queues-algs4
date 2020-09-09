import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of items on the queue

    public Deque() {
        N = 0;
    }

    private class Node { // nested class to define nodes
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        N++;
    }


    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        N--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }


    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        N--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new DeqIterator();
    }

    private class DeqIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        try {
            q.removeFirst();

        }
        catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }

        try {
            q.removeLast();
        }
        catch (NoSuchElementException e) {
            StdOut.println(e.getMessage());
        }
        try {
            q.addFirst(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }

        try {
            q.addLast(null);
        }
        catch (IllegalArgumentException e) {
            StdOut.println(e.getMessage());
        }

        Iterator<String> myIterator = q.iterator();
        try {
            myIterator.remove();
        }
        catch (UnsupportedOperationException e) {
            StdOut.println(e.getMessage());
        }


    }


}





