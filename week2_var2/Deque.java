import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
        first = new Node();
        last = first;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node node = new Node(item);
        node.item = item;
        if (isEmpty()) {
            last = node;
        } else {
            first.next.prev = node;
            node.next = first.next;
        }
        first.next = node;
        node.prev = first;
        n++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        Node node = new Node(item);
        last.next = node;
        node.prev = last;
        last = node;
        n++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node node = first.next;
        first.next = node.next;
        if (node.next != null)
            node.next.prev = first;
        n--;
        if (isEmpty())
            last = first;
        return node.item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Node node = last;
        last.prev.next = null;
        last = last.prev;
        n--;
        return node.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class Node {
        private Node prev;
        private Node next;
        private Item item;

        Node(Item item) {
            this.item = item;
        }

        Node() {
            this(null);
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node;

        DequeIterator(Node node) {
            this.node = node;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            node = node.next;
            return node.item;
        }

        public boolean hasNext() {
            return node.next != null;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("1");
        deque.addLast("2");
        deque.addFirst("3");
        for (String str : deque) {
            System.out.println(str);
        }
    }
}