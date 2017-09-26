package JacobiAlgorithm.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by alexey on 24.09.17.
 */
public class MatrixUtils {

    public static void print(double[][] matrix) {
        for (double[] row : matrix) {
            for (double i : row) {
                System.out.printf("%4f ",i);
            }
            System.out.println();
        }

        System.out.println();
    }

    public static double[][] transpose(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        int rows = a.length;
        int columns = a[0].length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        int aRows = A.length;
        int aColumns = A[0].length;
        int bColumns = B[0].length;

        double[][] C = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }


    public static double[][] multiplyToNum(double[][] A, double number) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] *= number;
            }
        }

        return A;
    }

    public static double[][] readMatrix(String fileName) {
        double[][] matrix = null;

        try {

            Scanner scanner = new Scanner(new File(fileName));

            int size = scanner.nextInt();
            matrix = new double[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public static double[][] deepCopy(double[][] original) {
        final double[][] result = new double[original.length][];

        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return result;
    }

    public static double[][] getColumn(double[][] matrix, int i) {
        double [][] column = new double[matrix[0].length][1];

        int c = 0;

        while (c < matrix.length) {
            column[c][0] = matrix[c][i];
            c++;
        }

        return column;
    }
}
