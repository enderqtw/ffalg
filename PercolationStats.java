package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/**
 * Created by lolnyan on 10.01.2017.
 */
public class PercolationStats {
    public Percolation[] perc;
    public int[] stats;
    public int trials;

    public PercolationStats(int n, int trials){
        if((n < 1) || (trials < 1))
            throw new java.lang.IllegalArgumentException("The number is incorrect!");
        this.trials = trials;
        perc = new Percolation[trials];
        stats = new int[trials];
        for(int i = 0; i < trials; i++)
            perc[i] = new Percolation(n);
    }

    public double mean(){
        return StdStats.mean(stats);
    }

    public double stddev(){
        return StdStats.stddev(stats);
    }

    public double confidenceLo(){
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }
    public double confidenceHi(){
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args){
        PercolationStats pcStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        for(int i = 0; i < pcStats.trials; i++){
            while(!pcStats.perc[i].percolates())
                pcStats.perc[i].open(StdRandom.uniform(Integer.parseInt(args[0])) + 1, StdRandom.uniform(Integer.parseInt(args[0])) + 1);
            pcStats.stats[i] = pcStats.perc[i].numberOfOpenSites();
            System.out.println(pcStats.stats[i]);
        }
        System.out.println("mean = " + pcStats.mean());
        System.out.println("stddev = " + pcStats.stddev());
        System.out.println("95% confidence interval = " + pcStats.confidenceLo() + ", " + pcStats.confidenceHi());
        System.out.println();

    }

}
