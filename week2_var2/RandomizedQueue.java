

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] queue;
    private int min = 10;


    public RandomizedQueue() {
        n = 0;
        queue = (Item[]) new Object[min];
    }    // constructor


    public boolean isEmpty() {
        return n == 0;
    }    // is empty?

    private void resize() {
        if (n == queue.length) {
            Item[] previousItem = queue;
            queue = (Item[]) new Object[queue.length * 2];
            System.arraycopy(previousItem, 0, queue, 0, n);
        } else if (n < queue.length / 2 && n >= min) {
            Item[] previousItem = queue;
            queue = (Item[]) new Object[queue.length / 2];
            System.arraycopy(previousItem, 0, queue, 0, n);
        }
    }


    public int size() {
        return n;
    }    // return the number of elements


    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        queue[n] = item;
        n++;
        resize();
    }    // add an item


    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int i = StdRandom.uniform(n);
        Item item = queue[i];
        queue[i] = queue[n - 1];
        queue[n - 1] = null;
        n--;
        resize();
        return item;
    }    // delete and return a random element


    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return queue[StdRandom.uniform(n)];
    }    // return a random item


    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }    // return an iterator in random order

    private class RandomizedIterator implements Iterator<Item> {
        private int[] el;
        private int element;

        RandomizedIterator() {
            el = new int[n];
            for (int i = 0; i < n; i++)
                el[i] = i;
            StdRandom.shuffle(el);
            element = -1;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            element++;
            return items[el[element]];
        }

        public boolean hasNext() {
            return element != n - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}

