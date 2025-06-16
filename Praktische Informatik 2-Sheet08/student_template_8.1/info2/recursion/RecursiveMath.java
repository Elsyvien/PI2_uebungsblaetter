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
        // TODO: Implement me.
        return 0;
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 b
    // ----------------------------------------------------------------
    public static int sub(int a, int b) {
        // TODO: Implement me.
        return 0;
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 c
    // ----------------------------------------------------------------
    public static int mul(int a, int b) {
        // TODO: Implement me.
        return 0;
    }
    
    // ----------------------------------------------------------------
    // Exercise 8.1 d
    // ----------------------------------------------------------------
    public static int div(int a, int b) {
        // TODO: Implement me.
        return 0;
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
