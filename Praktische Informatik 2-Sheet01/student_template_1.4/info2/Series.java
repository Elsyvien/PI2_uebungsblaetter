package info2;

public class Series {
	// Exercise Task
	// 1.1 a)
	public static int sumUp(int n) { // Function that counts all natural numbers from 1 to n including n
		int result = 0;
		for(int i = 1; i <= n; i++)
			result += i;
		return result;
	}

	// Exercise Task
	// 1.2 b)
	public static void multiplicationTable(int n) { // Function that prints the multiplication table of n
		int result = 0;
		for(int j = 1; j <= 10; j++) {
			System.out.println(n + " x " + j + " = " + (n * j));
		}
	}

	// Exercise Task
	// 1.3 c)
	public static void fizzBuzz() { // Function that returns the Fizzbuzz function
		for(int i = 1; i <= 100; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				System.out.println("FizzBuzz");
			else if (i % 3 == 0)
				System.out.println("Fizz");
			else if (i % 5 == 0)
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
	}
	// Exercise Task
	// 1.4 d)
	public static int factorial(int n) { // Function that returns the factorial of n
		int result = 1;
		if (n < 0)
			return -1; // Negative numbers do not have a factorial

		if (n == 0 || n == 1)
			return 1; // Basisfall

		for (int i = 2; i <= n; i++)
			result *= i;

		return result;
	}

	// Exercise Task
	// 1.5 e)
	public static boolean isPrime(int n) { // Function that checks if n is a prime number
		if (n <= 1)
			return false; // 0 and 1 are not prime numbers
		if (n == 2)
			return true; // 2 is a prime number
		if (n % 2 == 0)
			return false; // Even numbers greater than 2 are not prime numbers
		for (int i = 3; i <= Math.sqrt(n); i += 2) { // Check for odd factors
			if (n % i == 0)
				return false; // n is divisible by i, so it's not prime
		}
		return true; // n is prime
	}

	public static void main(String[] args) {

		System.out.println("1.1 a) ");
		System.out.println(sumUp(10));

		System.out.println("1.2 b) ");
		multiplicationTable(4);

		System.out.println("1.3 c) ");
		fizzBuzz();

		System.out.println("1.4 d) ");
		System.out.println(factorial(4));

		System.out.println("1.5 e) ");
		System.out.println(13 + " is " + (isPrime(13) ? "" : "not ") + "a prime number.");

	}
}
