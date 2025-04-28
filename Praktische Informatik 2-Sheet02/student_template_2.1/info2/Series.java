package info2;


public class Series {
	// Exercise Task
	// 1.1 a)
	public static void chessBoard(int n) {
		for(int i = 0; i < n; i++) { // Reihen
			for(int j = 0; j < n; j++) { // Spalte
				if((i + j) % 2 == 0) { // gerade Summe
					System.out.print("1" + "\n");
				} else { // ungerade Summe
					System.out.print("0" + "\n");
				}
			}
		}
	}

	// Exercise Task
	// 1.2 b)
	// Schreiben Sie nun eine Schleife, um die Eulersche Zahl e zu approximieren. Verwenden Sie
	// hierfur die folgende Reihe:
	public static double factorial(int n) {
		double result = 1;
		if(n == 0 || n == 1) {
			return result;
		}
		for(int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static double e(int n) {
		double result = 1;
		for(int i = 0; i < n; i++) {
			result += 1.0 / factorial(i);
		}
		return result;
	}

	// Exercise Task
	// 1.3 c)
	//Ziffern extrahieren: Fullen Sie eine Methode, welche f ¨ ur eine gegebene nat ¨ urliche Zahl ¨ n > 0
	//die Ziffern ruckw ¨ ¨arts ausgibt. Jede Ziffer soll hierbei in einer eigenen Zeile stehen. Fur ¨ n = 1239
	//sollte die Ausgabe wie folgt aussehen:
	public static void reverseDigits(int n) {
		while(n > 0) {
			int lastDigit = n % 10; // letzte Ziffer
			System.out.print(lastDigit + "\n");
			n = n / 10; // letzte Ziffer entfernen
		}
	}

	// Exercise Task
	// 1.4 d)
	public static double leibnizSeries(int n) {
		double res = 0;
		for(int i = 0; i < n; i++) {
			if(i % 2 == 0) { // gerade Zahl
				res += 1.0 / (2 * i + 1); // gerade Zähler
			} else { // ungerade Zahl
				res -= 1.0 / (2 * i + 1); // ungerade Zähler
			}
		}
		return 4 * res;
	}

	// Exercise Task
	// 1.5 e)
	public static String primeFactorization(int n) {

		String result = "";
		int i = 2;
		while(n > 1) {
			if(n % i == 0) { // i ist ein Teiler von n
				result += i + " "; // Teiler zu Ergebnis hinzufügen
				n /= i;
			} else {
				i++; // nächster Teiler
			}
		}

		return result;
	}


	public static void main(String[] args) {

		System.out.println("1.1 a) ");
		chessBoard(4);

		System.out.println("1.2 b) ");
		System.out.println(e(100));

		System.out.println("1.3 c) ");
		reverseDigits(1239);

		System.out.println("1.4 d) ");
		System.out.println(leibnizSeries(1000));

		System.out.println("1.5 e) ");
		System.out.println("Prime factors of " + 12 + ": " + primeFactorization(12));

	}
}
