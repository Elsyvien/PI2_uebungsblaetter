package info2;

public class NumericalApproximation {
	// Exercise Task
	// 2.3
	//compute the value of f(x) = 1/(1+x^2) for a given x
	public static double f(double x){
		return 1.0 / (1.0 + x*x);
	}

	// Approximate the integral of f(x) = 1/(1+x^2) from a to b using the Trapezoidal Rule
	public static double trapezoidalRule(double a, double b, int n) {
		if(n < 1) {
			System.out.println("n must be greater than 0");
			return -1;
		}

		double height = (b - a) / n; // height of each trapezoid
		double sum = 0.0;
		double result = 0.0;

		// Calculate the area of each trapezoid and sum them up
		for (int i = 1; i < n; i++) {
			double xi = a + i * height;
			sum += f(xi);
		}

		sum = (height / 2.0) * (f(a) + 2.0 * sum + f(b));
		return sum;
	}
	//Approximates the integral of f(x) = 1/(1+x^2) from a to b using Simpson's Rule
	// Ensure n is even for Simpson's rule otherwise it should return -1

	public static double simpsonsRule(double a, double b, int n){
		if (n < 1 || n % 2 != 0) {
			System.out.println("n must be greater than 0 and even");
			return -1;
		}
		double height = (b - a) / n; // height of each segment
		double sumOdd = 0.0;
		double sumEven = 0.0;
		double result = 0.0;

		for (int i = 1; i < n; i++) { // loop from 1 to n-1
			double xi = a + i * height; // current x value

			if(i % 2 == 0) {
				sumEven += f(xi); // even index
			} else {
				sumOdd += f(xi); // odd index
			}
		}

		result = (height / 3.0) * (f(a) + 4.0 * sumOdd + 2.0 * sumEven + f(b));
		return result;
	}


	public static void main(String[] args) {
		
		double a = 0.0; 
		double b = 1.0; 
		int[] testNs = {2, 4, 5, 10}; // list of different values of n

		double exactValue = Math.PI / 4;

		System.out.println("2.3) Approximations of the integral on [0,1]");
		System.out.println("Exact value for this interval is pi/4: " + exactValue);
		System.out.println("----------------------------------------------------------");

		for (int n : testNs) {
			System.out.println("Number of intervals: " + n);

			// Trapezoidal Rule
			double trapezoidalApprox = trapezoidalRule(a, b, n);
			double trapError = Math.abs(exactValue - trapezoidalApprox);
			System.out.printf("Trapezoidal Rule:   %.10f | Error: %.10f\n", trapezoidalApprox, trapError);

			// Simpson's Rule
		
			double simpsonsApprox = simpsonsRule(a, b, n);
			double simpError = Math.abs(exactValue - simpsonsApprox);
			System.out.printf("Simpson's Rule:     %.10f | Error: %.10f\n", simpsonsApprox, simpError);
			

			System.out.println("----------------------------------------------------------");
		}
	}
}

		
		
	



	
