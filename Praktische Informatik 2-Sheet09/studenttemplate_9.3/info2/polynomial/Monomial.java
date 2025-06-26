
package info2.polynomial;

/**
 * This class represents rational monomials. A monomial has
 * the following form:
 * 
 *     a * x ^ n
 *     
 *  where a is a rational coefficient (fraction), a variable
 *  (here we always assume 'x') and an integer degree. 
 *  
 * @author Sebastian Otte
 */
public class Monomial {
    
    public static Monomial ZERO = new Monomial(0, 0);
    public static Monomial ONE = new Monomial(1, 0);
    
    public final Rational coefficient;
    public final int degree;

    public Monomial() {
        this(1, 0);
    }
    
    /**
     * Returns true when the monomial equals 1 
     * (degree is 0 and coefficient is 1).
     */
    public boolean isOne() {
        return this.equals(ONE);
    }
    
    /**
     * Returns true when the monomial equals 0
     * (coefficient is 0 and degree is 0 (or something else))
     */
    public boolean isZero() {
        return this.coefficient.getNumerator() == 0;    
    }
    
    /**
     * Initializes a monomial with given coefficient (here int) and degree. 
     * @param coefficient Coefficient as int (will result in fraction a/1).
     * @param degree Degree as int.
     */
    public Monomial(final int coefficient, final int degree) {
        this(new Rational(coefficient), degree);
    }
    
    /**
     * Initializes a monomial with given coefficient (here Rational) and degree. 
     * @param coefficient Coefficient as Rational.
     * @param degree Degree as int.
     */
    public Monomial(final Rational coefficient, final int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }
    
    /**
     * Multiplies two monomials.
     * @param other Another monomial.
     * @return Result as a new Monomial instance.
     */
    public Monomial mul(final Monomial other) {
        if (other == null) {
            return null;
        } else {
            return new Monomial(
                this.coefficient.mul(other.coefficient),
                this.degree + other.degree
            );
        }
    }
    
    /**
     * Divides a monomial (this) by another monomial.
     * @param other Another monomial.
     * @return Result as a new Monomial instance.
     */
    public Monomial div(final Monomial other) {
        if (other == null) {
            return null;
        } else {
            return new Monomial(
                this.coefficient.div(other.coefficient),
                this.degree - other.degree
            );
        }
    }
    
    /**
     * Negates the monomial (toggles the sign of coefficient).
     * @return Result as a new Monomial instance.
     */
    public Monomial negate() {
        return new Monomial(this.coefficient.negate(), this.degree);
    }

    @Override
    public String toString() {
        
        String variableString = "";
        if (this.degree != 0) {
            variableString = "x";
        }
        
        String coefficientString = "";
        if (this.coefficient.reduce().equals(Rational.ONE)) {
            if (this.degree == 0) {
                coefficientString = "1";
            }
        } else if (this.coefficient.reduce().equals(Rational.MINUS_ONE)) {
            if (this.degree == 0) {
                coefficientString = "-1";
            } else {
                coefficientString = "-";
            }
        } else {
            coefficientString = this.coefficient.toString();
        }
        
        String degreeString = "";
        
        if ((this.degree > 1) || (this.degree < 0)) {
            degreeString = "^" + String.valueOf(this.degree);
        }
        
        return coefficientString + variableString + degreeString;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Monomial) {
            final Monomial other = (Monomial)obj;
            return (
                (this.degree == other.degree) &&
                (this.coefficient.equals(other.coefficient))
            );
        }
        return false;
    }
    
}
