package week3;

/**
 * Created by woice on 17.25.1.
 */

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;    //  x coordinate of current point
    private final int y;    //  y coordinate of current point

    //  Point constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //  draw point
    public void draw() {
        StdDraw.point(x, y);
    }

    //  returns a string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //  draw the line segment between current point and other
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    //  compares two points by y-coordinate, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that.y != this.y) {
            return this.y - that.y;
        }
        return this.x - that.x;
    }

    // compare points by slope
    public Comparator<Point> slopeOrder() {
        Comparator<Point> setSlopeOrder = new Slope();
        return setSlopeOrder;
    }



    private class Slope implements Comparator<Point> {
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

    //  main
    public static void main(String[] args) {

    }
}


