package week3;

import java.util.Arrays;

/**
 * Created by enderWoice on 30.01.2017.
 */
public class FastCollinearPoints {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); //# of points
        Point[] set = new Point[n];
        for (int i = 0; i < n; i++) {
            int index = 2*i+1;
            int xPos = Integer.parseInt(args[index]);
            int yPos = Integer.parseInt(args[index+1]);
            set[i] = new Point(xPos, yPos);
        }
        Arrays.sort(set);
        for (int i = 0; i < n; i++) {
            int inRow = 0;
            Point[] sortedSlope = new Point[n];
            for (int j = 0; j < n; j++) {
                sortedSlope[j] = set[j];
            }

            Point op = set[i];
            String points = op.toString();
            Arrays.sort(sortedSlope, op.slopeOrder);
            for (int j = 0; j < n-1; j++) {
                if (op.slopeTo(sortedSlope[j]) == op.slopeTo(sortedSlope[j+1])) {
                    inRow++;
                    continue;
                }
                if (inRow >= 2) {
                    for (int k = j - inRow; k < j + 1; k++) {
                        if (op.compareTo(sortedSlope[k]) != 0) {
                            points = points + ": " + sortedSlope[k].toString();
                        }
                    }
                    System.out.println (points);
                    points = op.toString();
                }
                inRow = 0;
            }
        }
    }
}