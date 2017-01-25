

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
    }   //  randomizedQueue constructor

    public boolean isEmpty() {
        return n == 0;
    }   //  return boolean value weather the queue is empty

    public int size() {
        return queue.length;
    }   //  return integer value of queue size

    private void resize(int a) {
        if (a == 0) {
            throw new java.lang.NullPointerException();
        }
        Item[] el = (Item[]) new Object[a];
        for (int i = 0; i < n; i++) {
            el[i] = queue[i];
        }
        queue = el;
    }   //  queue resize

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        queue[n++] = item;
        if (n == queue.length) {
            resize(2 * queue.length);
        }
    }   //  add element

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        Item item = queue[index];

        n--;
        if (index < n) {
            queue[index] = queue[n];
        }
        queue[n] = null;

        if (n > 0 && n <= queue.length / 4) {
            resize(queue.length / 2);
        }

        return item;
    }   //  delete element

    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return queue[StdRandom.uniform(n)];
    }   //  return a random element

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }   //  Iterator constructor

    private class ArrayIterator implements Iterator<Item> {
        private int e = 0;
        private Item[] random;

        public ArrayIterator() {
            random = (Item[]) new Object[n];
            for (int i = 0; i < random.length; i++) {
                random[i] = queue[i];
            }
            StdRandom.shuffle(random);
        }

        @Override
        public boolean hasNext() {
            return e < n;
        }   //  does the queue has next element?

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            throw new UnsupportedOperationException();
        }   //  remove element method

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            random = (Item[]) new Object[e];
            return random[e++];
        }   //  return next queue's element
    }   //  iterator method inicialize

    public static void main(String[] args) {

    }   //  main
}
