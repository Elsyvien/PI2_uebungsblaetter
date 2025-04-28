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
		
		// TODO: fill me

		return 123456789;

	}

	//Approximates the integral of f(x) = 1/(1+x^2) from a to b using Simpson's Rule
	// Ensure n is even for Simpson's rule otherwise it should return -1

	public static double simpsonsRule(double a, double b, int n){

		//TODO: fill me

		return 123456789;
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

		
		
	



	
