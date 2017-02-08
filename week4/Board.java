package week4;

import java.util.Arrays;

/**
 * Created by enderWoice on 03.02.2017.
 */
public class Board {
    private int N;
    private int rowSize;
    private int columnSize;
    private int indexOfEmpty;
    private int[][] currentBoard;

    private int manVal;
    private boolean manFunction;

    private int hamVal;
    private boolean hamFunction;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        manFunction = true;
        hamFunction = true;
        if (blocks == null)
            throw new RuntimeException();

        N = blocks[0].length * blocks[0].length;
        columnSize = blocks[0].length;
        rowSize = N / columnSize;

        currentBoard = dupArray(blocks);
        indexOfEmpty = findEmptyIndex(currentBoard);
    }

    private Board() {
    }

    private void setUpBoard() {
        manFunction = true;
        hamFunction = true;
        if (currentBoard == null)
            throw new RuntimeException();

        N = currentBoard[0].length * currentBoard[0].length;
        columnSize = currentBoard[0].length;
        rowSize = N / columnSize;
        indexOfEmpty = findEmptyIndex(currentBoard);
    }

    private int findEmptyIndex(int[][] board) {
        for (int i = 0; i < N; ++i)
            if (board[i / columnSize][i % columnSize] == 0)
                return i;
        throw new RuntimeException("Board have no empty index");
    }

    // board dimension n
    public int dimension() {
        return rowSize;
    }

    // number of blocks out of place
    public int hamming() {
        if (!hamFunction)
            return hamVal;
        int outOfOrder = 0;
        for (int i = 0; i < N - 1; ++i) {
            int v = currentBoard[i / columnSize][i % columnSize];
            // if (v == i + 1 /* && v != 0 */)
            if (v != i + 1)
                ++outOfOrder;
        }
        hamFunction = false;
        hamVal = outOfOrder;
        return outOfOrder;
    }

    private static int abs(int n) {
        if (n < 0)
            return -n;
        else
            return n;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (!manFunction)
            return manVal;
        int sumDistance = 0;

        for (int i = 0; i < N; ++i) {
            int row, column, val;
            row = i / rowSize;
            column = i % rowSize;
            val = currentBoard[row][column];
            if (val == 0)
                continue;
            --val;
            sumDistance += (abs(row - (val / rowSize))
                    + abs(column - (val % rowSize)));
        }
        manVal = sumDistance;
        manFunction = false;
        return sumDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (indexOfEmpty == N - 1 && hamming() == 0)
            return true;
        return false;
    }

    // duplicate the current array
    private int[][] dupArray(int[][] old) {
        int[][] board = new int[rowSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++)
                board[i][j] = old[i][j];
        }
        return board;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinBoard = dupArray(currentBoard);
        for (int i = 0; i < rowSize; i++) {
            if (twinBoard[i][0] != 0 && twinBoard[i][1] != 0) {
                int t = twinBoard[i][0];
                twinBoard[i][0] = twinBoard[i][1];
                twinBoard[i][1] = t;
                break;
            }
        }

        return new Board(twinBoard);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        if (that.rowSize != this.rowSize) return false;
        for (int i = 0; i < rowSize; i++) {
            if (!Arrays.equals(this.currentBoard[i], that.currentBoard[i]))
                return false;
        }
        return true;
    }

    private void swapIndexOfArray(int[][] tiles, int width, int index1, int index2) {
        int t = tiles[index1 / width][index1 % width];
        tiles[index1 / width][index1 % width] =
                tiles[index2 / width][index2 % width];
        tiles[index2 / width][index2 % width] = t;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue result = new Queue<Board>();
        int verticalEdge = 0;
        int horizontalEdge = 0;

        if (indexOfEmpty % columnSize == 0)
            horizontalEdge = 1;
        else if (indexOfEmpty % columnSize == (columnSize - 1))
            horizontalEdge = 2;

        if (indexOfEmpty / rowSize == 0)
            verticalEdge = 1;
        else if (indexOfEmpty / rowSize == (rowSize - 1))
            verticalEdge = 2;

        if (horizontalEdge != 1) {
            int[][] tboard = dupArray(currentBoard);
            swapIndexOfArray(tboard, rowSize, indexOfEmpty, indexOfEmpty - 1);
            Board t = new Board();
            t.currentBoard = tboard;
            t.setUpBoard();
            result.enqueue(t);
        }

        if (horizontalEdge != 2) {
            int[][] tboard = dupArray(currentBoard);
            swapIndexOfArray(tboard, rowSize, indexOfEmpty, indexOfEmpty + 1);
            Board t = new Board();
            t.currentBoard = tboard;
            t.setUpBoard();
            result.enqueue(t);
        }

        if (verticalEdge != 1) {
            int[][] tboard = dupArray(currentBoard);
            swapIndexOfArray(tboard, rowSize,
                    indexOfEmpty, indexOfEmpty - rowSize);

            Board t = new Board();
            t.currentBoard = tboard;
            t.setUpBoard();
            result.enqueue(t);
        }

        if (verticalEdge != 2) {
            int[][] tboard = dupArray(currentBoard);
            swapIndexOfArray(tboard, rowSize,
                    indexOfEmpty, indexOfEmpty + rowSize);

            Board t = new Board();
            t.currentBoard = tboard;
            t.setUpBoard();
            result.enqueue(t);
        }
        return result;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(rowSize + "\n");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                s.append(String.format("%2d ", currentBoard[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}