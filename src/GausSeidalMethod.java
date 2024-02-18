public class GausSeidalMethod {

    public static void main(String[] args) {
        // Example system of linear equations:
        // 7x + 52y + 13z = 104
        // 83x + 11y - 4z = 95
        // 3x + 8y + 29z = 71
        double[][] coefficients = {
                {20, 1, -2},
                {3, 20, -1},
                {2, -3, -20}
        };
        double[] constants = {17, -18,25};

        // Initial guess
        double[] initialGuess = {0, 0, 0};

        // Desired accuracy (tolerance)
        double epsilon = 0.001;

        // Maximum number of iterations
        int maxIterations = 100;

        // Solve the system using the Gauss-Seidel method
        double[] solution = gaussSeidel(coefficients, constants, initialGuess, epsilon, maxIterations);

        // Output the result
        System.out.println("Approximate solution:");
        for (int i = 0; i < solution.length; i++) {
            System.out.println("x" + (i + 1) + ": " + solution[i]);
        }
    }

    private static double[] gaussSeidel(double[][] coefficients, double[] constants, double[] initialGuess, double epsilon, int maxIterations) {
        int n = coefficients.length;
        double[] currentSolution = initialGuess.clone();
        double[] previousSolution = new double[n];

        for (int iteration = 1; iteration <= maxIterations; iteration++) {
            System.arraycopy(currentSolution, 0, previousSolution, 0, n);

            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= coefficients[i][j] * currentSolution[j];
                    }
                }
                currentSolution[i] = sum / coefficients[i][i];
            }


            // Check for convergence
            if (converged(currentSolution, previousSolution, epsilon)) {
                System.out.println("Converged after " + iteration + " iterations.");
                break;
            }
        }

        return currentSolution;
    }

    private static boolean converged(double[] current, double[] previous, double epsilon) {
        for (int i = 0; i < current.length; i++) {
            if (Math.abs(current[i] - previous[i]) >= epsilon) {
                return false;
            }
        }
        return true;
    }
}
