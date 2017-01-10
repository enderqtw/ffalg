package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * Created by lolnyan on 10.01.2017.
 */
public class Percolation {
    public WeightedQuickUnionUF quickUnion;
    public int[][] grid;
    public int n;
    public Percolation(int n){
        if(n <= 0)
            throw new java.lang.IllegalArgumentException("The number must be >0!");
        this.n = n;
        quickUnion = new WeidhtedQuickUnionUF(n * n + 2);
        for(int i = 1; i <= n; i++)
            quickUnion.union(0, i);
        for(int i = n * n; i > n * n - n; i++)
            quickUnion.union(n * n + 1, i);
        grid = new int[n][n];
    }   //grid creation method

    public void open(int row, int col){
        if((row > n) || (row < 1) || (col > n) || (col < 1))
            throw new java.lang.IllegalArgumentException("The number is incorrect!");
        if(grid[row-1][col-1] == 1)
            return;
        grid[row-1][col-1] = 1;
        if(col - 2 >= 0)
            if((isOpen(row, col-1)) && (!quickUnion.connected((row-1) * n + col, (row - 1) * n + (col - 1))))
                quickUnion.union((row - 1) * n + col, (row - 1) * n + (col - 1));
        //check of the first element
        if(col < n)
            if((isOpen(row, col+1)) && (!quickUnion.connected((row - 1) * n + col, (row - 1) * n + (col - 1))))
                quickUnion.union((row - 1) * n + col, (row - 1) * n + col + 1);
        //check of the last element
        if(row - 2 >= 0)
            if((isOpen(row - 1, col)) && (!quickUnion.connected((row - 1) * n + col, (row - 2) * n + col)))
                quickUnion.union((row - 1) * n + col, (row - 2) * n + col);
        if(row < n)
            if((isOpen(row + 1, col)) && (!quickUnion.connected((row - 1) * n + col, row * n + col)))
                quickUnion.union((row - 1) * n + col, row * n + col);
    }   //opens (row, col) site

    public boolean isOpen(int row, int col){
        if((row > n) || (row < 1) || (col < 1) || (col > n))
            throw new java.lang.IllegalArgumentException("The number is incorrect!");
        if(grid[row-1][col-1] == 1)
            return true;
        return false;
    }   //returns boolean value weather site is open

    public boolean isFull(int row, int col){
        if((row > n) || (row < 1) || (col < 1) || (col > n))
            throw new java.lang.IllegalArgumentException("The number is incorrect!");
        if(isOpen(row, col))
            if(quickUnion.connected((row - 1) * n + col, 0))
                return true;
        return false;
    }   //returns boolean value weather site if full

    public int numberOfOpenSites(){
        int count = 0;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++) //grid[0].
                if(isOpen(i, j))
                    count++;
        return count;
    }   //returns integer value of open sites

    public boolean percolates(){
        if(quickUnion.connected(0, n * n + 1)) {
            if (n == 1)
                if (!isOpen(1, 1))
                    return false;
            return true;
        }
        return false;
    }   //returns boolean value weather grid percolates

    public static void main(String[] args){
        Percolation perc = new Percolation(1);
        System.out.println(perc.percolates());
    }   //optional
}
