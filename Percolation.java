

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by enderqtw on 10.01.2017.
 */
public class Percolation {
    private WeightedQuickUnionUF quickUnion;
    private int[][] grid;
    private int n;


    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("The number n must be greater than 0!");
        }

        this.n = n;
        quickUnion = new WeightedQuickUnionUF(n * n + 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                quickUnion.union(i, j);
            }
        }

        grid = new int[n + 1][n + 1];
    }   // grid creation method

    public void open(int row, int col) {
        if ((row > n) || (row < 1) || (col > n) || (col < 1)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }

        if (grid[row][col] == 1) {
            return;
        }
        grid[row][col] = 1;
        if (col - 1 > 0) {
            if (isOpen(row, col - 1) && (!quickUnion.connected(viewParse(row, col) - 1, viewParse(row, col)))) {
                quickUnion.union(viewParse(row, col) - 1, viewParse(row, col));
            }
        }   // check of the left element
        if (col < n) {
            if (isOpen(row, col + 1) && (!quickUnion.connected(viewParse(row, col) + 1, viewParse(row, col)))) {
                quickUnion.union(viewParse(row, col) + 1, viewParse(row, col));
            }
        }   // check of the right element
        if (row - 1 > 0) {
            if (isOpen(row - 1, col) && (!quickUnion.connected(viewParse(row, col) - n, viewParse(row, col)))) {
                quickUnion.union(viewParse(row, col) - n, viewParse(row, col));
            }
        }   // check of the top element
        if (row < n) {
            if (isOpen(row + 1, col) && (!quickUnion.connected(viewParse(row, col) + n, viewParse(row, col)))) {
                quickUnion.union(viewParse(row, col) + n, viewParse(row, col));
            }
        }       // check of the bottom element
    }   // opens (row, col) site


    private int viewParse(int row, int col) {
        return (row - 1) * n + col;
    }

    public boolean isOpen(int row, int col) {
        if ((row > n) || (row < 1) || (col < 1) || (col > n)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }

        if (grid[row][col] == 1) {
            return true;
        }
        return false;
    }   // returns boolean value weather site is open

    public boolean isFull(int row, int col) {
        if ((row > n) || (row < 1) || (col < 1) || (col > n)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }

        if (isOpen(row, col)) {
            if (quickUnion.connected(viewParse(row, col), 1)) {
                return true;
            }
        }
        return false;
    }   // returns boolean value weather site is full

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isOpen(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }   // returns integer value of open sites

    public boolean percolates() {
        if (quickUnion.connected(1, n * n)) {
            if (n == 1) {
                if (!isOpen(1, 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }   // returns boolean value weather grid percolates

    public static void main(String[] args) {

    }   // optional
}
