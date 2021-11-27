package it.unibo.oop.lab.workers02;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class MultiThreadedSumMatrixWithStream implements SumMatrix {

    private final int nthread;

    public MultiThreadedSumMatrixWithStream(int nthread) {
        this.nthread = nthread;
    }

    private static class Worker implements Runnable {

        private final List<Integer> list;
        private final int starPos;
        private final int nelements;
        private Optional<Integer> res;

        public Worker(List<Integer> list, int starPos, int nelements) {
            this.list = list;
            this.starPos = starPos;
            this.nelements = nelements;
        }

        public void run() {
            res = this.list.stream().skip(starPos).limit(nelements).reduce(Integer::sum);
        }

        public Optional<Integer> getRes() {
            return res;
        }

        private static void joinAllWorkers(final Thread worker) {
            boolean joined = false;
            while (!joined) {
                try {
                    worker.join();
                    joined = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public double sum(double[][] matrix) {
            
            return 0;
        }

    }

}
