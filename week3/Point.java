package week3;

/**
 * Created by woice on 17.25.1.
 */

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    private final int x;    //  x coordinate of current point
    private final int y;    //  y coordinate of current point

    private class BySlope implements Comparator<Point> {
        //  compares two points by the slope they make with current point
        public int compare(Point p1, Point p2) {
            if (slopeTo(p1) < slopeTo(p2)) {
                return -1;
            } else if (slopeTo(p1) > slopeTo(p2)) {
                return 1;
            }
            return 0;
        }
    }

    //  Initializes of new point
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    //  draw point
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    //  draw the line segment between current point and other
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    //  returns the slope between current point and *that* point
    public double slopeTo(Point that) {
        if (that.x == this.x) {
            if (that.y == this.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (that.y == this.y) {
            return 0.0;
        }
        return (double) (that.y - this.y) / (double) (that.x - this.x);
    }

    //  compares two points by y- coordinate, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that.y != this.y) {
            return this.y - that.y;
        }

        return this.x - that.x;
    }

    //  returns a string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    //  main
    public static void main(String[] args) {

    }
}


