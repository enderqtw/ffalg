package week2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by woice on 17.16.1.
 */
public class Deque<Item> implements Iterable<Item> {

    private Deque first = null, last = null;
    private class Node{}
    public Deque() {

        Item item;
        Deque next;
    }   //  construct an empty deque

    public boolean isEmpty() {
        return first == null;
    }   //  return boolean value weather is empty

    public int size() {
        return first.size();
    }   //  return integer value of deque size

    public void addFirst(Item item) {
        Deque oldfirst = first;
        first = new Deque();
        first.item = item;
        first.next = oldfirst;
    }   //  add to deque the first element

    public void addLast(Item item) {

    }      //   add to deque the last element

    public Item removeFirst() {
        first = null;
        return null;
    }   //  remove from deque current first element

    public Item removeLast() {
        last = null;
        return null;
    }   //  remove from deque current last element

    public Iterator<Item> iterator(){}; //  iterator constructor

    private void resize(int capacity){

    }
    public static void main(String[] args) {

    }   //  optional
}
