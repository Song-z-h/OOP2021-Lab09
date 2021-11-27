package it.unibo.oop.lab.workers02;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a standard implementation of the calculation.
 * 
 */
public class MultiThreadedSumMatrix implements SumMatrix {

    private final int nthread;

    /**
     * 
     * @param nthread
     *                    no. of thread performing the sum.
     */
    public MultiThreadedSumMatrix(final int nthread) {
        this.nthread = nthread;
    }

    private static class Workers extends Thread {

        public double sum(final double[][] matrix) {
            return 0;
            //final List<Workers> workers = new ArrayList<>(nthread);
        }

    }

    @Override
    public double sum(double[][] matrix) {
        // TODO Auto-generated method stub
        return 0;
    }

}
