import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq = (Item[]) new Object[1]; // make private
    private int N; // number of items
    private int lastDel; // last deleted item id

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }


    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] doubleRq = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) doubleRq[i] = rq[i];
        rq = doubleRq;
    }


    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        if (N == rq.length) resize(rq.length * 2);
        rq[N] = item;
        N++;


    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");
        }
        if (N <= rq.length / 4) resize(rq.length / 2);
        sample();
        Item deleted = rq[lastDel];
        rq[lastDel] = rq[N - 1];
        rq[N - 1] = null;
        N--;
        return deleted;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");
        }
        lastDel = StdRandom.uniform(N);
        return rq[lastDel];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new rqIterator();
    }

    private class rqIterator implements Iterator<Item> {
        private RandomizedQueue<Item> rqTemp = new RandomizedQueue<Item>();

        public rqIterator() {
            rqTemp.rq = (Item[]) new Object[N];
            System.arraycopy(rq, 0, rqTemp.rq, 0, N);
            rqTemp.N = N;
        }

        public boolean hasNext() {
            return rqTemp.N != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            return rqTemp.dequeue();
        }


    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randQ.enqueue(item);
            StdOut.print
                    (item);
        }


        StdOut.println(
                "random sample: " + randQ.sample()
        );
        StdOut.println(
                "random dequeue: " + randQ.dequeue()
        );
        StdOut.println(
                "array size " + randQ.size()
        );
        for (String myItem : randQ) {
            StdOut.print(myItem);
        }
    }
}
