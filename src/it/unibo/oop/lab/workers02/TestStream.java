package it.unibo.oop.lab.workers02;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
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
        return IntStream.iterate(1, row -> row + NUM).limit(5).parallel()
                .mapToObj(row -> IntStream.range(0, NUM).map(c -> c + row).toArray()).toArray(int[][]::new);
    }

    public static List<List<int[]>> matrixToList(final int[][] mat) {
        return Arrays.stream(mat).map(Arrays::asList).collect(Collectors.toList());
    }

    /**
     * main.
     * 
     * @param args
     *                 param
     */
    public static void main(final String[] args) {

        final List<Integer> li = List.of(10, 20, 30, 5, 6, 7, 10, 20, 100);
        System.out.println(li);
        li.stream()
        .flatMap(i -> List.of(i, i + 1)
                .stream()
                .map(String::valueOf)
                .map(","::concat))
        .forEach(System.out::print);

    }

}
