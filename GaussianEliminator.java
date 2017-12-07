/**
 * This class implements the Gaussian Elimination algorithm to find the row-echelon form of a matrix.
 *
 * @author Praveen David Harris
 */
public class GaussianEliminator {
    // declare private variables
    private int mRowSize, mColSize;
    private double[][] matrix;

    /**
     * Initializes the matrix that we are working with
     * @param m The matrix
     */
    public GaussianEliminator(double[][] m) {
        mRowSize = m.length;
        mColSize = m[0].length;
        matrix = m;
    }

    /**
     * Prints the contents of the matrix in row-column form
     */
    public void printMatrix() {
        for (int i=0; i<mRowSize; i++) {
            for (int j=0; j<mColSize; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Computes the row-echelon form of a matrix and display it in row-column form
     */
    public void ref() {
        simplifyMatrix();   // [2, 4, 8] = [1, 2, 4]

        if (matrix[0][0] != 1 && matrix[0][0] == 0)
            tryAdjustingMatrix();  // index[0][0] = 1 or some integer not 0

        for (int j=1; j<mRowSize; j++) {
            double leadingCoefficient = matrix[j-1][j-1];
            int tray = j-1;
            for (int k=j; k<mRowSize; k++) {
                if (matrix[k][j-1] != 0) {
                    makeEntryZero(k, j-1, leadingCoefficient, tray);
                }
            }
            simplifyMatrix();
        }
        addTheLeadingOnes();
    }

    // ensure leading ones are there
    private void addTheLeadingOnes() {
        for (int i=0; i<mRowSize; i++) {
            if (matrix[i][i] != 1) {
                double factor = matrix[i][i];
                for (int j=0; j<mColSize; j++) {
                    matrix[i][j] = matrix[i][j] / factor;
                }
            }
        }
    }

    // make entries zero
    private void makeEntryZero(int j, int k, double leading, int tray) {
        double factor = matrix[j][k] / leading;
        for (int i=0; i<mColSize; i++) {
            matrix[j][i] = matrix[j][i] - factor * matrix[tray][i];
        }
    }

    // bring to index[0][0] an 1 or an integer
    private void tryAdjustingMatrix() {
        int indexOfLeadingOne= -1;
        for (int i=0; i<mRowSize; i++) {
            if (matrix[0][i] == 1)
                indexOfLeadingOne = i;
        }
        if (indexOfLeadingOne == -1) {
            int indexOfLeadingInteger = -1;
            for (int i=0; i<mRowSize; i++) {
                if (matrix[0][i] != 0)
                    indexOfLeadingInteger = i;
            }
            if (indexOfLeadingInteger != -1) {
                double[] temp = matrix[0];
                matrix[0] = matrix[indexOfLeadingInteger];
                matrix[indexOfLeadingOne] = temp;
            }
        }else {
            double[] temp = matrix[0];
            matrix[0] = matrix[indexOfLeadingOne];
            matrix[indexOfLeadingOne] = temp;
        }


    }

    // simplify each row of the matrix
    private void simplifyMatrix() {
        for (int i=0; i<mRowSize; i++) {
            // find the smallest number before performing division
            double smallestDivisor = 999999999;
            for (int j=0; j<mColSize; j++) {
                if (matrix[i][j] < smallestDivisor && matrix[i][j] != 0)
                    smallestDivisor = matrix[i][j];
            }
            boolean divisible = true;
            for (int j=0; j<mColSize; j++) {
                if (matrix[i][j] % smallestDivisor != 0)
                    divisible = false;
            }
            if (divisible) {
                for (int j=0; j<mColSize; j++)
                    matrix[i][j] = matrix[i][j] / smallestDivisor;
            }
        }
    }

}
