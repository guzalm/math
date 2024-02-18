//i can add a and b by myself
import java.util.Scanner;
import java.util.function.Function;

public class BisectionMethod2 {
    public static void main(String[] args) {
        Function<Double, Double> equation = x -> Math.pow(x, 10) - 1;

        // Get the initial endpoints [a, b] from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the initial left endpoint 'a': ");
        double a = scanner.nextDouble();
        System.out.print("Enter the initial right endpoint 'b': ");
        double b = scanner.nextDouble();

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

        // Close the scanner
        scanner.close();
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
}
