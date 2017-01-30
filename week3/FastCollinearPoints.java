package week3;

import java.util.Arrays;

/**
 * Created by enderWoice on 30.01.2017.
 */
public class FastCollinearPoints {

    private int lCount;     //  number of lines
    private LineSegment[] lines;    //  array of linear segments
    private Point[] slopePoints;    //  points in slope

    public FastCollinearPoints(Point[] points) {
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

        slopePoints = null;
        int [] slope = new int[n - 1];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for ( ; j < n; j++){

            }
        }
//        double firstSlope, secondSLope, thirdSlope, forthSlope, fifthSlope;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                firstSlope = pointsCopy[i].slopeTo(pointsCopy[j]);
//                if (firstSlope == Double.NEGATIVE_INFINITY) {
//                    throw new java.lang.IllegalArgumentException("Repeated point");
//                }
//                for (int k = j + 1; k < n; k++) {
//                    secondSLope = pointsCopy[j].slopeTo(pointsCopy[k]);
//                    if (firstSlope == secondSLope) {
//                        for (int l = k + 1; l < n; l++) {
//                            thirdSlope = pointsCopy[k].slopeTo(pointsCopy[l]);
//                            for (int m = l + 1; m < n; m++) {
//                                forthSlope = pointsCopy[l].slopeTo(pointsCopy[m]);
//                                if (firstSlope == forthSlope && secondSLope == forthSlope && thirdSlope == forthSlope) {
//                                    if (lCount == lines.length) {
//                                        resize(2 * lines.length);
//                                    }
//                                    lines[lCount] = new LineSegment(pointsCopy[i], pointsCopy[m]);
//                                    lCount++;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        resize(lCount);
    }

    //  array resize
    private void arrResize(){
        Point[] slopePointsCopy = new Point[slopePoints.length + 1];
        for (int i = 0; i < slopePoints.length; i++) {
            slopePointsCopy[i] = slopePoints[i];
        }
        slopePoints = slopePointsCopy;
    }

    //  resize of array
    private void resize(int capacity) {
        LineSegment[] resizeArr = new LineSegment[capacity];
        for (int i = 0; i < lCount; i++) {
            resizeArr[i] = lines[i];
        }
        lines = resizeArr;
    }

    //  returns a number of segments
    public int numberOfSegments() {
        return lCount;
    }

    //  the lines segment
    public LineSegment[] segments() {
        LineSegment[] lSegment = new LineSegment[lines.length];

        for (int i = 0; i < lines.length; i++) {
            lSegment[i] = lines[i];
        }
        return lSegment;
    }
}