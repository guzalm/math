import java.util.function.Function;

public class SecantMethod {
    public static void main(String[] args) {
        Function<Double, Double> equation = x ->  Math.pow(x,3) - 3*x +1;

        // Find initial points [x0, x1] automatically
        double[] initialPoints = findInitialPoints(equation);

        // Extract initial points
        double x0 = initialPoints[0];
        double x1 = initialPoints[1];

        // Desired accuracy (tolerance)
        double epsilon = 0.001;

        // Maximum number of iterations
        int maxIterations = 100;

        // Find the root using the Secant Method
        double root = secantMethod(equation, x0, x1, epsilon, maxIterations);

        // Round the result to three digits after the decimal point
        root = Math.round(root * 1000.0) / 1000.0;

        // Output the result
        System.out.println("Approximate root: " + root);
    }

    // Secant method implementation
    private static double secantMethod(Function<Double, Double> equation, double x0, double x1, double epsilon, int maxIterations) {
        double root = 0.0;

        for (int i = 0; i < maxIterations; i++) {
            double fx0 = equation.apply(x0);
            double fx1 = equation.apply(x1);

            // Calculate the new approximation using the Secant Method
            double x2 = x1 - ((x1 - x0) / (fx1 - fx0)) * fx1;
            double fx2 = equation.apply(x2);

            // Check if the root is found within the desired tolerance
            if (Math.abs(fx2) < epsilon) {
                root = x2;
                break;
            }

            // Update the points [x0, x1]
            x0 = x1;
            x1 = x2;
        }

        return root;
    }

    // Find initial points [x0, x1] automatically
    private static double[] findInitialPoints(Function<Double, Double> equation) {
        double x0 = 0.0;
        double x1 = 1.0;

        double fx0 = equation.apply(x0);
        double fx1 = equation.apply(x1);

        while (Math.abs(fx1) > Math.abs(fx0)) {
            double temp = x1 - ((x1 - x0) / (fx1 - fx0)) * fx1;
            x0 = x1;
            x1 = temp;
            fx0 = fx1;
            fx1 = equation.apply(x1);
        }

        return new double[]{x0, x1};
    }
}
