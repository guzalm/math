import java.util.Scanner;
import java.util.function.Function;

public class NewtonRaphsonMethod{
    public static void main(String[] args) {
        Function<Double, Double> equation = x -> Math.pow(x, 4)-x-10;

        // Get the initial approximation from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the initial approximation 'x1': ");
        double x1 = scanner.nextDouble();

        // Calculate the derivative function
        Function<Double, Double> derivative = derive(equation, x1);

        // Desired accuracy (tolerance)
        double epsilon = 0.001;

        // Maximum number of iterations
        int maxIterations = 100;

        // Find the root using the Newton-Raphson method
        double root = newtonRaphson(equation, derivative, x1, epsilon, maxIterations);

        // Round the result to three digits after the decimal point
        root = Math.round(root * 10000.0) / 10000.0;

        // Output the result
        System.out.println("Approximate root: " + root);

        // Close the scanner
        scanner.close();
    }

    // Newton-Raphson method implementation
    private static double newtonRaphson(Function<Double, Double> equation, Function<Double, Double> derivative, double x1, double epsilon, int maxIterations) {
        double root = 0.0;

        for (int i = 0; i < maxIterations; i++) {
            double fx1 = equation.apply(x1);
            double dfx1 = derivative.apply(x1);

            // Calculate the new approximation using the Newton-Raphson method
            double x2 = x1 - (fx1 / dfx1);

            // Check if the root is found within the desired tolerance
            if (Math.abs(x2 - x1) < epsilon) {
                root = x2;
                break;
            }

            // Update the approximation
            x1 = x2;
        }

        return root;
    }

    // Function to numerically calculate the derivative
    private static Function<Double, Double> derive(Function<Double, Double> equation, double x) {
        double h = 0.0001;
        return t -> (equation.apply(t + h) - equation.apply(t - h)) / (2 * h);
    }
}
