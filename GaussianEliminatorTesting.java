
public class GaussianEliminatorTesting {

    public static void start() {
        double[][] myMatrix= {
                {1, 3, 3},
                {1, 7, 8},
                {1, 6, 8}

        };
        GaussianEliminator eliminator = new GaussianEliminator(myMatrix);
        eliminator.ref();
        eliminator.printMatrix();

    }

    public static void main(String[] args) {
        start();
    }
}
