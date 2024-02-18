import java.util.function.Function;

public class BisectionMethod {
    public static void main(String[] args) {
        Function<Double, Double> equation = x -> Math.pow(x,3)-4*x-9;

        // Find initial endpoints [a, b]
        double[] endpoints = findInitialEndpoints(equation);

        // Define the interval [a, b] where the root lies
        double a = endpoints[0];
        double b = endpoints[1];

        // Desired accuracy (tolerance)
        double epsilon = 0.001;

        // Maximum number of iterations
        int maxIterations = 100;

        // Find the root using the bisection method
        double root = bisection(equation, a, b, epsilon, maxIterations);

        // Round the result to three digits after the decimal point
        root = Math.round(root * 1000.0) / 1000.0;

        // Output the result
        System.out.println("Approximate root: " + root);
    }

    // Bisection method implementation
    private static double bisection(Function<Double, Double> equation, double a, double b, double epsilon, int maxIterations) {
        double fa = equation.apply(a);
        double fb = equation.apply(b);

        if (fa * fb > 0) {
            throw new IllegalArgumentException("The function values at the endpoints must have opposite signs.");
        }

        double root = 0.0;

        for (int i = 0; i < maxIterations; i++) {
            // Calculate the midpoint
            double c = (a + b) / 2;
            double fc = equation.apply(c);

            // Check if the root is found within the desired tolerance
            if (Math.abs(fc) < epsilon) {
                root = c;
                break;
            }

            // Update the interval [a, b]
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        return root;
    }

    // Find initial endpoints [a, b] based on the sign change of the function values
    private static double[] findInitialEndpoints(Function<Double, Double> equation) {
        double x = 0.0;
        double step = 0.1; // Initial step size

        while (equation.apply(x) * equation.apply(x + step) > 0) {
            x += step;
        }

        return new double[]{x, x + step};
    }
}
