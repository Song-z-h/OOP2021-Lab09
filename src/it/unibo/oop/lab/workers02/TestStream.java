package it.unibo.oop.lab.workers02;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * test.
 */
public final class TestStream {

    private TestStream() {
    }

    /**
     * print matrix.
     * 
     * @param mat
     *                matrix to print
     */
    public static void printMatrix(final int[][] mat) {
        Arrays.stream(mat).forEach(subarr -> iterateRow(subarr));
    }

    private static void iterateRow(final int[] arr) {
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
        System.out.println();
    }

    /**
     * fill mat.
     * 
     * @return matrix
     */
    public static int[][] fillMatrixParallel(final int NUM) {
        return IntStream.iterate(1, row -> row + NUM).limit(NUM).parallel()
                .mapToObj(row -> IntStream.range(0, NUM).map(c -> c + row).toArray()).toArray(int[][]::new);
    }

    public static int[][] fillMatrix(final int NUM) {
        return IntStream.iterate(1, row -> row + NUM).limit(NUM).parallel()
                .mapToObj(row -> IntStream.range(0, NUM).map(c -> c + row).toArray()).toArray(int[][]::new);
    }

    /**
     * main.
     * 
     * @param args
     *                 param
     */
    public static void main(final String[] args) {

        /*
         * long time1 = System.currentTimeMillis(); Stream.iterate(0, i -> i +
         * 1).limit(N).map(i -> i * 2).reduce(Integer::sum);
         * 
         * time1 = System.currentTimeMillis() - time1;
         * 
         * long time2 = System.currentTimeMillis();
         * 
         * Stream.iterate(0, i -> i + 1).parallel().limit(N).map(i -> i *
         * 3).reduce(Integer::sum);
         * 
         * time2 = System.currentTimeMillis() - time2;
         * 
         * System.out.println("performance : time1 " + time1 + "ms time2 " + time2 +
         * "ms");
         */
        final int N = 40000;
         long time = System.currentTimeMillis();

         int[][] mat = fillMatrix(N);
         mat = null;

        System.out.println("no parallel : time" + (System.currentTimeMillis() - time) + " ms");
        
        time = System.currentTimeMillis();
        mat = fillMatrixParallel(N);
        
        System.out.println("parallel : time" + (System.currentTimeMillis() - time) + " ms");
        // printMatrix(mat);
    }

}
