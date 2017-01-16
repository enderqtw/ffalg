package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.01.2017.
 */
public class Deque<Item> implements Iterable<Item> {
    private Item[] queue;

    public Deque() {
        queue = (Item[]) new Object[0];
    }   // constructor of Deque

    public boolean isEmpty() {
        if (queue.length != 0) {
            return false;
        }
        return true;
    }   //  is the deque empty?

    public int size() {
        return queue.length;
    }   //  return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null element");
        }
        Item[] deque = (Item[]) new Object[queue.length + 1];
        for (int i = 0; i < queue.length; i++) {
            deque[i] = queue[i];
        }
        queue = deque;
        queue[queue.length - 1] = item;

    }   //  add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null element");
        }
        Item[] deque = (Item[]) new Object[queue.length + 1];
        for (int i = 1; i < deque.length; i++) {
            deque[i] = queue[i - 1];
        }
        queue = deque;
        queue[0] = item;
    }   //  add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Is empty");
        }
        Item removeNumber = queue[queue.length - 1];
        Item[] deque = (Item[]) new Object[queue.length - 1];
        for (int i = 0; i < deque.length; i++) {
            deque[i] = queue[i];
        }
        queue = deque;
        return removeNumber;
    }   //  remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Is empty");
        }
        Item removeNumber = queue[0];
        Item[] deque = (Item[]) new Object[queue.length - 1];
        for (int i = 0; i < deque.length; i++) {
            deque[i] = queue[i + 1];
        }
        queue = deque;
        return removeNumber;
    }   //  remove and return the item from the end

    private class ArrayIterator implements Iterator<Item> {
        private int k = queue.length - 1;

        @Override
        public boolean hasNext() {
            if (k >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Doesn't have a next element");
            }
            Item nextElement = queue[k--];
            return nextElement;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove");
        }
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }   //  return an iterator over items in order from front to end

    public static void main(String[] args) {
    }   //  optional
}