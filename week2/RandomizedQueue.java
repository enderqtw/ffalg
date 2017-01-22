

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;
    private int first = 0, last = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[0];
    }   //  randomizedQueue constructor

    public boolean isEmpty() {
        return queue.length == 0;
    }   //  return boolean value weather the queue is empty

    public int size() {
        return queue.length;
    }   //  return integer value of queue size

    private void resize(int a) {
        Item[] el = (Item[]) new Object[a];
        for (int i = 0; i < queue.length; i++) {
            el[i] = queue[(first + i) % queue.length];
        }
        queue = el;
        first = 0;
        last = n;
    }   //  queue resize

    public void enqueue(Item item) {
        if (n == queue.length) {
            resize(2 * n);
        }
        queue[last++] = item;
        if (last == n) {
            last = 0;
        }
        n++;
    }   //  add element

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = queue[first];
        queue[first] = null;
        n--;
        if (++first == n) {
            first = 0;
        }
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }   //  delete element

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return queue[StdRandom.uniform(n)];
    }   //  return a random element

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }   //  Iterator constructor

    private class ArrayIterator implements Iterator<Item> {
        private int element = 0;

        @Override
        public boolean hasNext() {
            return element < n;
        }

        @Override
        public void remove() {
            throw new NoSuchElementException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = queue[(element + first) % queue.length];
            return item;
        }
    }   //  iterator method inicialize

    public static void main(String[] args) {

    }   //  main
}
