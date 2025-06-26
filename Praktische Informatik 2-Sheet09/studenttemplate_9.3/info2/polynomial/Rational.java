package info2.polynomial;

/**
 * Simple class for handling rational numbers (fractions).
 * A rational number x is represented represented by a fraction: 
 * 
 *    a   numerator (int)
 *    -
 *    b   denominator (int)
 *
 * @author Sebastian Otte
 */
public class Rational {
    
    public static final Rational ONE = new Rational(1, 1);
    public static final Rational MINUS_ONE = ONE.negate();
    
    private final int numerator;
    private final int denominator;
    
    /**
     * Returns the numerator.
     */
    public int getNumerator() {
        return this.numerator;
    }
    
    /**
     * Returns int the denominator. 
     */
    public int getDenominator() {
        return this.denominator;
    }
    
    /**
     * Initializes instance with given numerator and denominator.
     * @param numerator Numerator as int value.
     * @param denominator Denominator as int value.
     */
    public Rational(final int numerator, final int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    /**
     * Initializes instance with given numerator and 1 as denominator.
     * @param numerator Numerator as int value.
     */
    public Rational(final int numerator) {
        this(numerator, 1);
    }

    @Override
    public String toString() {
        if (this.denominator != 1) {
            return "" + this.numerator + "/" + this.denominator;
        }
        return "" + this.numerator;
    }
    
    /**
     * Recursive implementation of the greatest common divisor.
     * (used for fraction reduction). 
     */
    private static int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    /**
     * Reduces the fraction.  
     * @return Instance of reduced fraction.
     */
    public Rational reduce() {
        final int num = this.numerator;
        final int den = this.denominator;
        
        final int gcd = gcd(
            Math.abs(this.numerator), 
            Math.abs(this.denominator)
        );
        
        if (gcd == 0) return new Rational(0);

        final int sign = (den < 0)?(-1):(1);
        
        return new Rational(
            (num / gcd) * sign,
            (den / gcd) * sign
        );
    }

    // ----------------------------------------------------------------
    // Implementations of math operations without final reduction. 
    // ----------------------------------------------------------------

    private Rational mulFast(final int a) {
        return new Rational(this.numerator * a, this.denominator);
    }
    
    private Rational mulFast(final Rational other) {
        return new Rational(
            this.numerator * other.numerator,
            this.denominator * other.denominator
        );
    }

    private Rational divFast(final int a) {
        return new Rational(this.numerator, this.denominator * a);
    }
    
    private Rational divFast(final Rational other) {
        return new Rational(
            this.numerator * other.denominator,
            this.denominator * other.numerator
        );
    }
    
    private Rational addFast(final int a) {
        return new Rational(
            this.numerator +  (a * this.denominator),
            this.denominator
        );
    }
    
    private Rational addFast(final Rational other) {
        return new Rational(
            (this.numerator * other.denominator) + (other.numerator * this.denominator),
            this.denominator * other.denominator
        );
    }
    
    private Rational subFast(final int a) {
        return this.addFast(-a);
    }
    
    private Rational subFast(final Rational other) {
        return this.addFast(other.negate());
    }
    
    
    // ----------------------------------------------------------------
    
    /**
     * Returns the negation of the rational value.
     * @return Instance of Rational.
     */
    public Rational negate() {
        return new Rational(-this.numerator, this.denominator);
    }
    
    /**
     * Multiplies a rational value (this) with an int.
     * @param a An int value.
     * @return Result as a new Rational instance.
     */
    public Rational mul(final int a) {
        return this.mulFast(a).reduce();
    }
    
    /**
     * Multiplies a rational value (this) with another rational.
     * @param other Another rational.
     * @return Result as a new Rational instance.
     */
    public Rational mul(final Rational other) {
        return this.mulFast(other).reduce();
    }
    
    /**
     * Divides a rational value (this) by an int.
     * @param other An int value.
     * @return Result as a new Rational instance.
     */
    public Rational div(final int a) {
        return this.divFast(a).reduce();
    }
    
    /**
     * Divides a rational value (this) by another rational.
     * @param other Another rational.
     * @return Result as a new Rational instance.
     */
    public Rational div(final Rational other) {
        return this.divFast(other).reduce();
    }

    /**
     * Adds a rational value (this) with an int.
     * @param other An int value.
     * @return Result as a new Rational instance.
     */
    public Rational add(final int a) {
        return this.addFast(a).reduce();
    }
    
    /**
     * Adds a rational value (this) with another rational.
     * @param other Another rational.
     * @return Result as a new Rational instance.
     */
    public Rational add(final Rational other) {
        return this.addFast(other).reduce();
    }
    
    /**
     * Returns the rational value.
     * @return
     */
    public double asDouble() {
        return (double)(this.numerator) / (double)(this.denominator);
    }
    
    /**
     * Subtracts an int value (a) from a rational value (this).
     * @param other An int value.
     * @return Result as a new Rational instance.
     */
    public Rational sub(final int a) {
        return this.subFast(a).reduce();
    }
    
    /**
     * Subtracts a rational value (a) from a rational value (this).
     * @param other Another rational.
     * @return Result as a new Rational instance.
     */
    public Rational sub(final Rational other) {
        return this.subFast(other).reduce();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Rational) {
            final Rational other = (Rational)obj;
            return (
                (this.numerator == other.numerator) &&
                (this.denominator == other.denominator)
            );
        }
        return false;
    }

    public static void main(String[] args) {
        
        final Rational a = new Rational(4, 8);
        System.out.println(a);
        System.out.println(a.reduce());
        System.out.println();
        
        final Rational b = new Rational(5, 11);
        System.out.println(b);
        
        System.out.println(new Rational(0, 2).mul(new Rational(0, 5)));  
        
        System.out.println(a.mulFast(b).reduce());
        System.out.println(a.mul(b).asDouble());
        System.out.println(a.asDouble() * b.asDouble());
        
        System.out.println(a.div(b).asDouble());
        System.out.println(a.asDouble() / b.asDouble());

        System.out.println(a.add(b).asDouble());
        System.out.println(a.asDouble() + b.asDouble());
        
        System.out.println(a.sub(b).asDouble());
        System.out.println(a.asDouble() - b.asDouble());
    }
}
