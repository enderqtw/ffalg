
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.01.2017.
 */
public class Deque<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n;
    private int first = 0, last = 0;

    public Deque() {
        queue = (Item[]) new Object[0];
    }   // constructor of Deque

    public boolean isEmpty() {
        return queue.length == 0;
    }   //  is the queue empty?

    public int size() {
        return queue.length;
    }   //  return the number of items on the deque

    private void resize(int a) {
        Item[] el = (Item[]) new Object[a];
        for (int i = 0; i < queue.length; i++) {
            el[i] = queue[(first + i) % queue.length];
        }
        queue = el;
        first = 0;
        last = n;
    }   //  queue resize

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null element");
        }
        Item[] deque = (Item[]) new Object[queue.length + 1];
        for (int i = 0; i < queue.length; i++) {
            deque[i] = queue[i];
        }
        queue = deque;
        last = queue.length - 1;
        queue[last] = item;

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

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }   //  return an iterator over items in order from front to end


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
    }   //  optional
}