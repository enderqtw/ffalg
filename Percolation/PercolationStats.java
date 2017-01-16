

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * Created by enderqtw on 10.01.2017.
 */
public class PercolationStats {

    private int count;
    private Percolation perc;
    private double[] stats;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Given must be greater then 0!");
        }
        count = t;
        stats = new double[count];
        for (int i = 0; i < count; i++) {
            perc = new Percolation(n);
            int openedSites = 0;
            while (!perc.percolates()) {
                int a = StdRandom.uniform(1, n + 1);
                int b = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(a, b)) {
                    perc.open(a, b);
                    openedSites++;
                }
            }
            double stat = (double) openedSites / (n * n);
            stats[i] = stat;
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(count));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(count));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats pcStats = new PercolationStats(n, t);

        System.out.println("mean = " + pcStats.mean());
        System.out.println("stddev = " + pcStats.stddev());
        System.out.println("95% confidence interval = " + pcStats.confidenceLo() +
                ", " + pcStats.confidenceHi());
    }
}




