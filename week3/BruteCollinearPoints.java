package week3;

import java.util.Arrays;

/**
 * Created by woice on 17.27.1.
 */

public class BruteCollinearPoints {

    private int lCount;     //  number of lines
    private LineSegment[] lines;    //  array of linear segments

    //  finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Empty array");
        }
        lCount = 0;
        lines = new LineSegment[1];
        int n = points.length;
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Null point");
            }
            pointsCopy[i] = points[i];
        }
        Arrays.sort(pointsCopy);
        double firstSlope, secondSlope, thirdSlope;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                firstSlope = pointsCopy[i].slopeTo(pointsCopy[j]);
                if (firstSlope == Double.NEGATIVE_INFINITY) {
                    throw new java.lang.IllegalArgumentException("Repeated point");
                }
                for (int k = j + 1; k < n; k++) {
                    secondSlope = pointsCopy[j].slopeTo(pointsCopy[k]);
                    if (firstSlope == secondSlope) {
                        for (int l = k + 1; l < n; l++) {
                            thirdSlope = pointsCopy[k].slopeTo(pointsCopy[l]);
                            if (firstSlope == secondSlope && firstSlope == thirdSlope && secondSlope == thirdSlope) {
                                if (lCount == lines.length) {
                                    resize(2 * lines.length);
                                }
                                lines[lCount] = new LineSegment(pointsCopy[i], pointsCopy[l]);
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
        return lCount;  //  <?>lines.length
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