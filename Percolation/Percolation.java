

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by enderqtw on 10.01.2017.
 */
public class Percolation {

    private boolean[][] grid;
    private int top = 0;
    private int bottom;
    private int n;
    private WeightedQuickUnionUF quick;

    public Percolation(int n) {
        this.n = n;
        bottom = n * n + 1;
        quick = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
    }   // System perlocation

    private int viewParse(int row, int col) {
        return n * (row - 1) + col;
    }   // Parsing 2d massive to 1d

    public void open(int row, int col) {
        grid[row - 1][col - 1] = true;
        if (row == 1) {
            quick.union(viewParse(row, col), top);
        }
        if (row == n) {
            quick.union(viewParse(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            quick.union(viewParse(row, col), viewParse(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            quick.union(viewParse(row, col), viewParse(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            quick.union(viewParse(row, col), viewParse(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            quick.union(viewParse(row, col), viewParse(row + 1, col));
        }
    }   // Opens the (row, col) site


    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }   // Is the site open?


    public boolean isFull(int row, int col) {
        if (0 < row && row <= n && 0 < col && col <= n) {
            return quick.connected(top, viewParse(row, col));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }   // Is the site full?


    public boolean percolates() {
        return quick.connected(top, bottom);
    }   // Does the grid percolates?

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


    public static void main(String[] args) {

    }   // optional
}
