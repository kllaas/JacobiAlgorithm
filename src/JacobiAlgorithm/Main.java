package JacobiAlgorithm;

/**
 * Created by alexey on 24.09.17.
 */
public class Main {

    private static final String FILENAME = "/home/alexey/Programming/Projects/Iteliji/JacobiMethod/src/JacobiAlgorithm/input.txt";
    private static final double e = 0.00001;

    private static double[][] I;

    public static void main(String[] args) {
        double[][] matrix = MatrixUtils.readMatrix(FILENAME);
        Algorithm algorithm = new Algorithm(e, matrix);

        double[][] result = algorithm.getEigenvalue();

        MatrixUtils.print(result);
    }

}
