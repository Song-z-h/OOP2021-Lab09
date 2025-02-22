package it.unibo.oop.lab.workers02;

import java.util.stream.IntStream;

/**
 * sum matrix with multi thread.
 */
public final class MultiThreadedSumMatrixWithStream implements SumMatrix {

    private final int nthread;

    /**
     * constructor.
     * 
     * @param nthread
     *                    number of thread
     */
    public MultiThreadedSumMatrixWithStream(final int nthread) {
        this.nthread = nthread;
    }

    /*
     * private List<Double> matToList(final double[][] mat) { final List<Double>
     * list = new ArrayList<>(); for (int i = 0; i < mat.length; i++) { for (final
     * double d : mat[i]) { list.add(d); } } return list; }
     */

    /*
     * @Override public double sum(final double[][] matrix) {
     * 
     * final List<Double> list = matToList(matrix);
     * 
     * final int size = list.size() / nthread + list.size() % nthread;
     * 
     * return IntStream.iterate(0, startPos -> startPos + size) .limit(nthread)
     * .mapToObj(startPos -> new Worker(list, startPos, size)) .peek(Thread::start)
     * .peek(MultiThreadedSumMatrixWithStream::joinAllWorkers) .map(Worker::getRes)
     * .mapToDouble(x -> x.get()) .sum();}
     */

    @Override
    public double sum(final double[][] matrix) {

        final int size = matrix.length / nthread + matrix.length % nthread;
        return IntStream.iterate(0, startPos -> startPos + size).limit(nthread).parallel().mapToDouble(start -> {
            double res = 0;
            for (int i = start; i < matrix.length && i < start + size; i++) {
                for (final double d : matrix[i]) {
                    res += d;
                }
            }
            return res;
        }).sum();
    }

    /*
     * private static void joinAllWorkers(final Thread worker) { boolean joined =
     * false; while (!joined) { try { worker.join(); joined = true; } catch
     * (InterruptedException e) { e.printStackTrace(); }
     * 
     * } }
     * 
     * private static class Worker extends Thread {
     * 
     * private final List<Double> list; private final int starPos; private final int
     * nelements; private Optional<Double> res;
     * 
     * Worker(final List<Double> list, final int starPos, final int nelements) {
     * this.list = list; this.starPos = starPos; this.nelements = nelements; }
     * 
     * @Override public void run() { res =
     * this.list.stream().skip(starPos).limit(nelements).reduce(Double::sum); }
     * 
     * public Optional<Double> getRes() { return res; }
     * 
     * }
     */

}
