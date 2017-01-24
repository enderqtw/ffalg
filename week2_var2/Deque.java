
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;


    public Deque() {
        first = new Node();
        last = first;
        n = 0;
    }   //  Deque constructor

    public boolean isEmpty() {
        return n == 0;
    }   //  is the deque empty?

    public int size() {
        return n;
    }   //  return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
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
    }   //  add first element to Node


    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node node = new Node(item);
        last.next = node;
        node.prev = last;
        last = node;
        n++;
    }   //  add last element to Node

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = first.next;
        first.next = node.next;
        if (node.next != null) {
            node.next.prev = first;
        }
        n--;
        first.prev = null;  //  for memory
        if (isEmpty()) {
            last = first;
        }
        return node.item;
    }   //  delete first element of the Node and return new first element

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = last;
        last.prev.next = null;
        last = last.prev;
        n--;
        return node.item;
    }   //  delete the last element and return new last

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }   //  iterator constructor

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
    }   //  Node constructor

    private class DequeIterator implements Iterator<Item> {
        private Node node;

        DequeIterator(Node node) {
            this.node = node;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            node = node.next;
            return node.item;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }
    }

    public static void main(String[] args) {
//        Deque<String> deque = new Deque<String>();
//        deque.addFirst("1");
//        deque.addLast("2");
//        deque.addFirst("3");
//        for (String str : deque) {
//            System.out.println(str);
//        }
    }
}