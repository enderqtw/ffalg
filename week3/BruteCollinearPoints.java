package week3;

import java.util.Arrays;

/**
 * Created by woice on 17.27.1.
 */

public class BruteCollinearPoints {

    private int lCount;     //  number of lines
    private LineSegment[] lines;    //  array of linear segments

    //  constructor
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Empty array");
        }
        lCount = 0;
        lines = new LineSegment[1];
        int n = points.length;
        Point[] copy = new Point[points.length];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Null point");
            }
            copy[i] = points[i];
        }
        Arrays.sort(copy);
        double firstSlope, secondSLope, thirdSlope;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                firstSlope = copy[i].slopeTo(copy[j]);
                if (firstSlope == Double.NEGATIVE_INFINITY) {
                    throw new java.lang.IllegalArgumentException("Repeated point.");
                }
                for (int k = j + 1; k < n; k++) {
                    secondSLope = copy[j].slopeTo(copy[k]);
                    if (firstSlope == secondSLope) {
                        for (int l = k + 1; l < n; l++) {
                            thirdSlope = copy[k].slopeTo(copy[l]);
                            if (firstSlope == thirdSlope && secondSLope == thirdSlope) {
                                if (lCount == lines.length) {
                                    resize(2 * lines.length);
                                }
                                lines[lCount] = new LineSegment(copy[i], copy[l]);
                                lCount++;
                            }
                        }
                    }
                }
            }
        }
        resize(lCount);
    }

    //  resize of array
    private void resize(int capacity) {
        LineSegment[] resizeArr = new LineSegment[capacity];
        for (int i = 0; i < lCount; i++) {
            resizeArr[i] = lines[i];
        }
        lines = resizeArr;
    }

    //  the number of line segments
    public int numberOfSegments() {
        return lCount;
    }

    //  the line segments
    public LineSegment[] segments() {
        LineSegment[] lSegment = new LineSegment[lines.length];

        for (int i = 0; i < lines.length; i++) {
            lSegment[i] = lines[i];
        }
        return lSegment;
    }

}