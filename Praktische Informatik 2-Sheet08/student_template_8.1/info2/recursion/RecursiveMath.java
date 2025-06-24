package info2.recursion;

public class RecursiveMath {

    
    public static int incr(int a) {
        return a + 1;
    }
    
    public static int decr(int a) {
        return a - 1;
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 a
    // ----------------------------------------------------------------
    public static int add(int a, int b) {
        if (b == 0) {
            return a; // Base case: if b is 0, return a
        }
        if (b > 0) {
            return add(incr(a), decr(b)); // Increment a and decrement b
        } else {
            return add(decr(a), incr(b)); // Decrement a and increment b
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 b
    // ----------------------------------------------------------------
    public static int sub(int a, int b) {
        if (b == 0) {
            return a; // Base case: if b is 0, return a
        }
        if (b > 0) {
            return sub(decr(a), decr(b)); // Decrement both a and b
        } else {
            return sub(incr(a), incr(b)); // Increment both a and b
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 c
    // ----------------------------------------------------------------
    public static int mul(int a, int b) {
        if( a == 0 || b == 0) {
            return 0; // Base case: if either a or b is 0, return 0
        }
        if (b > 0) {
            return add(a, mul(a, decr(b))); // Add a to the result of mul(a, b-1)
        } else {
            return sub(0, mul(a, sub(0, b))); // Handle negative b by decrementing -b
        }
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 d
    // ----------------------------------------------------------------
    public static int div(int a, int b) {
       if(a == 0 || b == 0) {
            return 0; // Base case: if a is 0, return 0; division by zero is not handled
        }
        int sign = 1;
        if (a < 0 ) {
            a = sub(0, a); // Make a positive if it is negative
            sign = sub(0, sign); // Change sign to negative
        }
        if (b < 0) {
            b = sub(0, b); // Make b positive if it is negative
            sign = sub(0, sign); // Change sign to negative
        }
        if (a < b) {
            return 0; // If a is less than b, return 0
        } else {
            int result = incr(div(sub(a, b), b));
            if (sign < 0) {
                return sub(0, result); // If the sign is negative, return the negative result
            } else {
                return result; // Otherwise, return the positive result
            }
        }
    }
    public static void main(String[] args) {
        
        System.out.println("Add(0, 0): " + add(0, 0));
        System.out.println("Add(0, 2): " + add(0, 2));
        System.out.println("Add(10, 20): " + add(10, 20));
        System.out.println("Add(-10, 20): " + add(-10, 20));
        System.out.println("Add(10, -20): " + add(10, -20));
        
        System.out.println("Sub(0, 0): " + sub(0, 0));
        System.out.println("Sub(0, 2): " + sub(0, 2));
        System.out.println("Sub(10, 20): " + sub(10, 20));
        System.out.println("Sub(-10, 20): " + sub(-10, 20));
        System.out.println("Sub(10, -20): " + sub(10, -20));

        System.out.println("Mul(0, 0): " + mul(0, 0));
        System.out.println("Mul(0, 2): " + mul(0, 2));
        System.out.println("Mul(10, 20): " + mul(10, 20));
        System.out.println("Mul(-10, 20): " + mul(-10, 20));
        System.out.println("Mul(10, -20): " + mul(10, -20));

        System.out.println("Div(0, 0): " + div(0, 0)); // should return 0 as per implementation
        System.out.println("Div(0, 2): " + div(0, 2)); // should return 0 as per implementation
        System.out.println("Div(100, 10): " + div(100, 10));
        System.out.println("Div(250, 5): " + div(250, 5));
        System.out.println("Div(25, 1): " + div(25, 1));
        System.out.println("Div(100 / -10): " + div(100, -10));
        System.out.println("Div(-100 / 10): " + div(-100, 10));
        System.out.println("Div(-100 / -10): " + div(-100, -10));
    }

}
