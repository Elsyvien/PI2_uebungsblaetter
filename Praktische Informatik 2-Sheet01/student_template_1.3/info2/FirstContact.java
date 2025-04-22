package info2;

public class FirstContact {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        else {
            return (double) a / b;
        }
    }

    public static void main(String[] args) {
        // Concatenating something to a string: "Hello" + 42 => "Hello42"
        System.out.println("1 + 1 = " + add(1, 1));
        System.out.println("1 - 1 = " + subtract(1, 1));
        System.out.println("1 * 1 = " + multiply(1, 1));
        System.out.println("1 / 1 = " + divide(1, 1));
    }
}
