

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by woice on 17.16.1.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(queue.dequeue());
        }
    }
}