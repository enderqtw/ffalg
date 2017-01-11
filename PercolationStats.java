package algsTaks1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


    /**
    *   Created by enderqtw on 10.01.2017.
    */
public class PercolationStats {
    private Percolation[] perc;
    private int[] stats;
    private int trials;

    public PercolationStats(int n, int trials) {
        if ((n < 1) || (trials < 1)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        this.trials = trials;
        perc = new Percolation[trials + 1];
        stats = new int[trials + 1];
        for (int i = 1; i <= trials; i++) {
            perc[i] = new Percolation(n);
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        PercolationStats pcStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        for (int i = 1; i <= Integer.parseInt(args[1]); i++) {
            while (!pcStats.perc[i].percolates()) {
                pcStats.perc[i].open(StdRandom.uniform(Integer.parseInt(args[0])) + 1, StdRandom.uniform(Integer.parseInt(args[0])) + 1);
            }
            pcStats.stats[i] = pcStats.perc[i].numberOfOpenSites();
            System.out.println(pcStats.stats[i]);
        }

        System.out.println("mean = " + pcStats.mean());
        System.out.println("stddev = " + pcStats.stddev());
        System.out.println("95% confidence interval = " + pcStats.confidenceLo() + ", " + pcStats.confidenceHi());
    }

}
