package JacobiAlgorithm.core;

import java.util.Arrays;

/**
 * Created by alexey on 24.09.17.
 */
public class Algorithm {

    private double e;
    private double[][] A;
    private double[][] I;

    public Algorithm(double e, double[][] A) {
        this.e = e;
        this.A = A;
        generateI(A);
    }

    public double[][] getEigenvalue() {
        double[][] M = A;
        double[][] eigenVectors = MatrixUtils.deepCopy(I);
        MatrixElement max = getMaxTopRight(M);

        while (max.val > e) {
            double phi = getPhi(M, max.i, max.j);

            System.out.printf("Max = %f, i = %d, j = %d\n", max.val, max.i, max.j);
            System.out.println("Phi = " + phi);

            double sin = Math.sin(phi);
            double cos = Math.cos(phi);

            System.out.printf("sin = %f, cos = %f\n", sin, cos);

            double[][] H = generateH(sin, cos, max.i, max.j);
            eigenVectors = MatrixUtils.multiply(eigenVectors, H);

            System.out.printf("H = \n");
            MatrixUtils.print(H);

            M = MatrixUtils.multiply(MatrixUtils.multiply(MatrixUtils.transpose(H), M), H);

            MatrixUtils.print(M);

            max = getMaxTopRight(M);
        }

        printResidual(eigenVectors, M);

        return M;
    }

    private void printResidual(double[][] eigenVectors, double[][] M) {
        for (int i = 0; i < eigenVectors.length; i++) {
            double eigenvalue = M[i][i];
            System.out.printf("Eigenvalue = %f\n", eigenvalue);
            System.out.println("Eigenvectors = " + Arrays.deepToString(MatrixUtils.getColumn(eigenVectors, i)));
            double[][] x = getResid(MatrixUtils.getColumn(eigenVectors, i), eigenvalue);
            System.out.println("Resid = " + Arrays.deepToString(x));
            System.out.println();
        }
    }

    private double[][] getResid(double[][] eigenVector, double eigenvalue) {
        return MatrixUtils.subtract(MatrixUtils.multiply(A, eigenVector), MatrixUtils.multiplyToNum(eigenVector, eigenvalue));
    }

    private double[][] generateH(double sin, double cos, int i, int j) {
        double[][] res = MatrixUtils.deepCopy(I);

        res[i][i] = cos;
        res[i][j] = -sin;
        res[j][i] = sin;
        res[j][j] = cos;

        return res;
    }

    private double getPhi(double[][] m, int i, int j) {
        double atanArgs = (double) 2 * m[i][j] / (m[i][i] - m[j][j]);
        return Math.atan(atanArgs) / 2;
    }

    private MatrixElement getMaxTopRight(double[][] m) {
        MatrixElement max = new MatrixElement(m[0][1], 0, 1);

        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (Math.abs(max.val) < Math.abs(m[i][j])) {
                    max = new MatrixElement(Math.abs(m[i][j]), i, j);
                }
            }
        }

        return max;
    }

    private void generateI(double[][] matrix) {
        I = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            I[i][i] = 1;
        }
    }


}
