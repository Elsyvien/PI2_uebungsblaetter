package info2;

public class Loops {
	// Exercise Task
	// 2.2
	//Implementieren Sie die Funktion printNumberPattern, die einen ganzzahligen Parameter n > 0 entgegennimmt und ein rechtsbundiges Dreiecksmuster ausgibt. ¨
	//Ihre Funktion soll ausschließlich while-Schleifen anstelle von for-Schleifen verwenden.
	//Das Muster soll demjenigen entsprechen, das durch den folgenden Code (mit for-Schleifen) erzeugt wird:
	//for (int i = 1; i <= n; i++) {
	//for (int j = 1; j <= (n - i); j++) {
	//System.out.print(" ");
	//}
	//for (int k = 1; k <= i; k++) {
	//System.out.print(i);
	//}
	//System.out.println();
	//}
	public static void printNumberPattern(int n) {
	if (n <= 0) {
			System.out.println("n muss größer als 0 sein");
			return;
		}

		int i = 1;
		while(i <= n) {
			int j = 1;
			while (j <= (n - i)) {
				System.out.print(" ");
				j++;
			}

			int k = 1;
			while (k <= i) {
				System.out.print(i);
				k++;
			}

			System.out.println();
			i++;
		}
	}


	public static void main(String[] args) {
		System.out.println("2.2) Print Number Pattern ");
		printNumberPattern(3);
		printNumberPattern(5);
		printNumberPattern(10);
		
	}
}



	
