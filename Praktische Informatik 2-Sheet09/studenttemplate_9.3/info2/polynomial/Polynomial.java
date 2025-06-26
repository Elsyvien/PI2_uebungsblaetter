package info2.polynomial;

import info2.polynomial.tools.PolynomialParser;

/**
 * A simple list implementation representing polynomials 
 * a recursive structures (linked list).
 * @author Sebastian Otte
 */
public class Polynomial {

    private Monomial head;
    private Polynomial next;    
    
    /**
     * Return this head value of the list.
     * @return Head value as Monomial.
     */
    public Monomial getHead() {
        return this.head;
    }
    
    /**
     * Returns the remaining list. 
     * @return Remaining polynomial.
     */
    public Polynomial getNext() {
        return this.next;
    }
    
    /**
     * Initializes a polynomial with just one Monomial. The remaining
     * list null.
     * @param head Instance of Monomial.
     */
    public Polynomial(final Monomial head) {
        this(head, null);
    }
    
    /**
     * Initializes a polynomial with with a given monomial and
     * another poylnomial (as remaining list). 
     * @param head Instance of Monomial.
     * @param next Instance of Polynomial.
     */
    public Polynomial(final Monomial head, final Polynomial next) {
        this.head = head;
        this.next = next;
    }
    
    /**
     * Returns true if the current polynomial node has a next polynomial node. 
     */
    public boolean hasNext() {
        return this.next != null;
    }
    
    /**
     * Returns the degree of the polynomial, which is the highest degree value
     * of all contained monomials.
     * @return Degree of the polynomial.
     */
    public int degree() {
        if (!this.hasNext()) {
            return this.head.degree;
        }
        return Math.max(this.head.degree, this.next.degree());
    }
    
    /**
     * Creates a copy of the polynomial recursively.
     * @return New polynomial list.
     */
    public Polynomial copy() {
        if (!this.hasNext()) {
            return new Polynomial(this.head);
        }
        return new Polynomial(this.head, this.next.copy());
    }
    
    /**
     * Negates the polynomial, which effectively toogles the signs
     * of the coefficients of all contained monomials. 
     * @return New polynomial list.
     */
    public Polynomial negate() {
        if (!this.hasNext()) {
            return new Polynomial(this.head.negate());
        } else {
            return new Polynomial(this.head.negate(), this.next.negate());
        }
    }
    
    /**
     * Appends all monomials of the given polynomial list (other)
     * at the end of this polynomial. This is a plain list append
     * operation.
     * @param Another polynomial.
     * @return New polynomial list.
     */
    public Polynomial append(final Polynomial other) {
        if (other == null) {
            return this;
            //
            // Alternative:
            //
            //return this.copy();
        } else if (!this.hasNext()) {
            return new Polynomial(this.head, other.copy());
            //
            // Alternative:
            //
            //this.next = other.copy();
            //return this;
        } else {
            return new Polynomial(this.head, this.next.append(other.copy()));
            //
            // Alternative;
            //
            //this.next = this.next.append(other);
            //return this;
        }
    }
    
    private Polynomial dropZerosCore() {
        if (this.head.coefficient.getNumerator() == 0) {
            if (!this.hasNext()) {
                return null;
            } else {
                return this.next.dropZerosCore();
            }
        } else {
            if (!this.hasNext()) {
                return new Polynomial(this.head);
                //
                // Alternative: 
                //
                //return this;
            } else {
                return new Polynomial(
                    this.head,
                    this.next.dropZerosCore()
                );
                //
                // Alternative:
                //
                //this.next = this.next.dropZerosCore();
                //return this;
            }
        }
    }
    
    /**
     * This methods removes all monomials from the polynomial
     * which have a zero coefficient. 
     * @return New polynomial list.
     */
    public Polynomial dropZeros() {
        final Polynomial result = this.dropZerosCore();
        if (result == null) {
            return new Polynomial(new Monomial(0, 0));
        }
        return result;
    }
    
    @Override
    public String toString() {
        if (!this.hasNext()) {
            return this.head.toString();
        } else {
            return this.head.toString() + " + " + this.next.toString();
        }
    }
    
    private boolean equalsHelper(final Polynomial other) {
        if (other == null) {
            return false;
        } else if (this.hasNext() != other.hasNext()) {
            return false;
        } else if (!this.hasNext()) {
            return this.head.equals(other.head);
        } else {
            return this.head.equals(other.head) && this.next.equalsHelper(other.next);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Polynomial) {
            return equalsHelper((Polynomial)obj);
        }
        return false;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (a)
    // ----------------------------------------------------------------
    
    public boolean isSorted() {
        //
        // TODO: Implement me.
        //        
        return false;
    }

    // ----------------------------------------------------------------
    // Exercise 2 (b)
    // ----------------------------------------------------------------

    public Polynomial insertSorted(final Monomial m) {
        //
        // TODO: Implement me.
        //        
        return null;
    }

    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    
    public Polynomial sort() {
        //
        // TODO: Implement me.
        //        
        return null;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
    public Polynomial simplify() {
        //
        // TODO: Implement me.
        //        
        return null;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

    public Polynomial sub(final Polynomial other) {
        return this.add(other.negate());
    }

    public Polynomial add(final Polynomial other) {
        //
        // TODO: Implement me.
        //        
        return null;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
    public Polynomial mul(final Polynomial other) {
        //
        // TODO: Implement me.
        //        
        return null;
    }
    
    
    // ----------------------------------------------------------------
    // Exercise 3
    // ----------------------------------------------------------------
    
    public Polynomial[] div(final Polynomial other) {
        //
        // TODO: Implement me.
        //        
        return null;
    }
    

    private static Polynomial cons(final int... degrees) {
        if (degrees.length == 0) return null;
        
        Polynomial p = null;
        for (int i = (degrees.length - 1); i >= 0; i--) {
            p = new Polynomial(new Monomial(1, degrees[i]), p);
        }
        return p;
    }
    
    // Helper method to create monomials
    private static Monomial m(final int degree) {
        return new Monomial(1, degree);
    }
    
    // Helper method to create monomials with coefficient and degree
    private static Monomial m(final int coefficient, int degree) {
        return new Monomial(coefficient, degree);
    }
    
    // Helper method to create polynomials with monomials 
    private static Polynomial cons(final Monomial... monomials) {
        if (monomials.length == 0) return null;
        
        Polynomial p = null;
        for (int i = (monomials.length - 1); i >= 0; i--) {
            p = new Polynomial(monomials[i], p);
        }
        return p;
    }
    
    // Helper method to create polynomial from string
    private static Polynomial poly(final String str) {
        return PolynomialParser.parse(str);
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Assertion failed");
        }

    }
    
    private static Polynomial[] pair(final Polynomial p, final Polynomial r) {
        return new Polynomial[] {p, r};
    }

    private static Polynomial notNull(final Polynomial p) {
        if (p == null) {
            return new Polynomial(Monomial.ZERO);
        }
        return p;
    }

    private static boolean equals(final Polynomial[] p, final Polynomial[] q) {
        p[0] = notNull(p[0]);
        p[1] = notNull(p[1]);
        q[0] = notNull(q[0]);
        q[1] = notNull(q[1]);
        //
        return p[0].equals(q[0]) && p[1].equals(q[1]);
    }
    
    public static void main(String[] args) {
        System.out.println("f. Testing div() method:");
        try {
            assertTrue(equals(
                poly("3x^3 + 2x^2 + x").div(poly("1")), 
                pair(poly("3x^3 + 2x^2 + x"), null)
            ));
            System.out.println("    div test 1 passed");

            
            assertTrue(equals(
                poly("-6x^3 + 5x^2 + 14x - 12").div(poly("-2x + 3")),
                pair(poly("3x^2 + 2x - 4"), null)
            ));
            System.out.println("    div test 2 passed");

            assertTrue(
            equals(
                poly("x^3 + 3x^2 - 6x - 8").div(poly("x - 2")),
                pair(poly("x^2 + 5x + 4"), null)
            ));
            System.out.println("    div test 3 passed");
            
            assertTrue(
            equals(
                poly("x^3 + 3x^2 - 6x - 8").div(poly("x - 1")),
                pair(poly("x^2 + 4x - 2"), poly("-10"))
            ));
            System.out.println("    div test 4 passed");
            
            assertTrue(
            equals(
                poly("x^3 - x^2 - 5x + 6").div(poly("x - 2")),
                pair(poly("x^2 + x - 3"), null)
            ));
            System.out.println("    div test 5 passed");
            
            assertTrue(
            equals(
                poly("3x^5 + 9x^4 + 8x^3 + 2x^2 + 8").div(poly("3x^3 + 3x^2 - 4x + 4")),
                pair(poly("x^2 + 2x + 2"), null)
            ));
            System.out.println("    div test 6 passed");

            assertTrue(
            equals(
                poly("2x^5 - 2x^4 + 7x^3 - 14x^2 + 3x - 9").div(poly("x^3 - x^2 + 3x - 4")),
                pair(poly("2x^2 + 1"), poly("-5x^2 - 5"))
            ));
            System.out.println("    div test 7 passed");
            
            System.out.println("   All div tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    div test failed: " + e.getMessage() + "\n");
        }
    }


    


}
