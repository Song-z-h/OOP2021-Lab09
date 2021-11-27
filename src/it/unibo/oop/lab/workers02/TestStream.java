package it.unibo.oop.lab.workers02;

import java.util.Arrays;
import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * test.
 */
public final class TestStream {
    static final int N = 10;

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
    public static int[][] fillMatrix() {
        return IntStream.range(0, N).mapToObj(r -> IntStream.range(0, N).map(c -> c + 1).toArray()).toArray(int[][]::new);
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

        int[][] mat;

        mat = fillMatrix();
        printMatrix(mat);

    }

}
