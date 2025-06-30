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
     * Initializes a polynomial with a given monomial and
     * another polynomial (as remaining list). 
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
     * @param other polynomial.
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
        if (!this.hasNext()) {
            return true; // A single monomial is sorted by definition.
        }
        if (this.head.degree < this.next.head.degree) {
            return false; // Current degree is less than next degree.
        } else if (this.head.degree == this.next.head.degree) {
            // If degrees are equal, check the next monomial.
            return this.next.isSorted();
        }     
        return this.next.isSorted(); // Ensure recursion continues for all cases.
    }

    // ----------------------------------------------------------------
    // Exercise 2 (b)
    // ----------------------------------------------------------------

    public Polynomial insertSorted(final Monomial m) {
        if (m == null) {
            return this.copy();
        }
        // Insert at front if degree is greater
        if (m.degree > this.head.degree) {
            return new Polynomial(m, this.copy());
        }
        // Tail of list, append at end
        if (this.next == null) {
            return new Polynomial(this.head, new Polynomial(m));
        }
        // Insert before first node with degree less than m.degree
        if (this.next.head.degree < m.degree) {
            return new Polynomial(this.head, new Polynomial(m, this.next));
        }
        // Otherwise recurse
        return new Polynomial(this.head, this.next.insertSorted(m));
    }

    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    public Polynomial sort() {
        // Build sorted list using insertSorted
        Polynomial sorted = new Polynomial(this.head);
        Polynomial current = this.next;

        while (current != null) {
            sorted = sorted.insertSorted(current.head); // assign the result
            current = current.next;
        }

        return sorted;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    public Polynomial simplify() {
        // Sort the polynomial first
        Polynomial sorted = this.sort();
        // Combine like terms
        Polynomial result = null;
        Polynomial tail = null;
        Polynomial current = sorted;
        while (current != null) {
            Monomial m = current.head;
            // Sum coefficients for same degree
            while (current.next != null && current.next.head.degree == m.degree) {
                m = new Monomial(m.coefficient.add(current.next.head.coefficient), m.degree);
                current = current.next;
            }
            if (result == null) {
                result = new Polynomial(m);
                tail = result;
            } else {
                tail.next = new Polynomial(m);
                tail = tail.next;
            }
            current = current.next;
        }
        if (result == null) {
            return new Polynomial(new Monomial(0, 0));
        }
        result = result.dropZeros();
        if (result == null) {
            return new Polynomial(new Monomial(0, 0));
        }
        return result;
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

    public Polynomial sub(final Polynomial other) {
        return this.add(other.negate());
    }

    public Polynomial add(final Polynomial other) {
        if (other == null) {
            return this.copy();
        }
        Polynomial a = this;
        Polynomial b = other;
        Polynomial result = null;
        Polynomial tail = null;
        while (a != null && b != null) {
            Monomial m;
            if (a.head.degree > b.head.degree) {
                m = a.head;
                a = a.next;
            } else if (a.head.degree < b.head.degree) {
                m = b.head;
                b = b.next;
            } else {
                m = new Monomial(a.head.coefficient.add(b.head.coefficient), a.head.degree);
                a = a.next;
                b = b.next;
            }
            if (result == null) {
                result = new Polynomial(m);
                tail = result;
            } else {
                tail.next = new Polynomial(m);
                tail = tail.next;
            }
        }
        while (a != null) {
            Monomial m = a.head;
            if (result == null) {
                result = new Polynomial(m);
                tail = result;
            } else {
                tail.next = new Polynomial(m);
                tail = tail.next;
            }
            a = a.next;
        }
        while (b != null) {
            Monomial m = b.head;
            if (result == null) {
                result = new Polynomial(m);
                tail = result;
            } else {
                tail.next = new Polynomial(m);
                tail = tail.next;
            }
            b = b.next;
        }

        // Combine like terms and remove zeros
        return (result == null)
            ? new Polynomial(new Monomial(0, 0))
            : result.sort().simplify();
    }
    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
    public Polynomial mul(final Polynomial other) {
        if (other == null) {
            return null; // If the other polynomial is null, return null.
        }
        Polynomial result = null;
        Polynomial current = other;
        while (current != null) {
            Monomial m = current.head;
            Polynomial temp = this.mulWithMonomial(m);
            if (result == null) {
                result = temp;
            } else {
                result = result.add(temp);
            }
            current = current.next;
        }
        if (result == null) {
            return new Polynomial(new Monomial(0, 0));
        }
        // Sort the result to ensure correct order
        result = result.sort();
        result = result.dropZeros();
        if (result == null) {
            return new Polynomial(new Monomial(0, 0));
        }
        return result;
    }

    // Hilfsmethode: Multipliziert das Polynom mit einem Monom
    private Polynomial mulWithMonomial(Monomial m) {
        if (m == null) return null;
        if (!this.hasNext()) {
            return new Polynomial(this.head.mul(m));
        } else {
            return new Polynomial(this.head.mul(m), this.next.mulWithMonomial(m));
        }
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

    public static void main(String[] args) {
        System.out.println("=== Testing Polynomial Class  ===\n");
        

        System.out.println("Testing dropZeros() method:");
        try {
            assertTrue( cons(m(0, 10)).dropZeros().equals(cons(m(0,0))));
            System.out.println(" Test 1 passed");
            
            assertTrue( cons(m(1, 1)).dropZeros().equals(cons(m(1,1))));
            System.out.println(" Test 2 passed");
            
            assertTrue( cons(m(1, 1), m(0, 2)).dropZeros().equals(cons(m(1,1))));
            System.out.println(" Test 3 passed");
            
            assertTrue( cons(m(0, 3), m(0, 0), m(1, 1), m(0, 2)).dropZeros().equals(cons(m(1,1))));
            System.out.println(" Test 4 passed");
            
            System.out.println("   All dropZeros tests passed!\n");
        } catch (AssertionError e) {
            System.out.println(" Test failed: " + e.getMessage() + "\n");
        }
        
    
        System.out.println("Testing append() method:");
        try {
            assertTrue( cons(1).append(cons(2)).equals(cons(1, 2)));
            System.out.println(" test 1 passed");
            
            assertTrue( cons(1).append(null).equals(cons(1)));
            System.out.println(" test 2 passed");
            
            assertTrue( cons(1, 2).append(cons(2)).equals(cons(1, 2, 2)));
            System.out.println(" test 3 passed");
            
            assertTrue(cons(1, 2, 3).append(cons(4, 5, 6)).equals(cons(1, 2, 3, 4, 5, 6)));
            System.out.println(" test 4 passed");
            
            System.out.println("   All append tests passed!\n");
        } catch (AssertionError e) {
            System.out.println(" append test failed: " + e.getMessage() + "\n");
        }
        
       
        System.out.println("a. Testing isSorted() method:");
        try {
            assertTrue( cons(1).isSorted());
            System.out.println("   isSorted test 1 passed");
            
            assertTrue( cons(2,1).isSorted());
            System.out.println("   isSorted test 2 passed");
            
            assertTrue( cons(2,2).isSorted());
            System.out.println("   isSorted test 3 passed");
            
            assertTrue(cons(3,2,1).isSorted());
            System.out.println("   isSorted test 4 passed");
            
            assertTrue( cons(4,3,2,1).isSorted());
            System.out.println("   isSorted test 5 passed");
            
            assertTrue(cons(4,3,3,1).isSorted());
            System.out.println("   isSorted test 6 passed");
            
            assertTrue(!cons(1,2).isSorted());
            System.out.println("   isSorted test 7 passed (correctly false)");
            
            assertTrue( !cons(1,2,3).isSorted());
            System.out.println("   isSorted test 8 passed (correctly false)");
            
            assertTrue( !cons(4,3,1,2).isSorted());
            System.out.println("   isSorted test 9 passed (false)");
            
            System.out.println("   All isSorted tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    isSorted test failed: " + e.getMessage() + "\n");
        }
        
        
        System.out.println("b. Testing insertSorted() method:");
        try {
            assertTrue(cons(1).insertSorted(m(2)).equals(cons(2,1)));
            System.out.println("    insertSorted test 1 passed");
            
            assertTrue( cons(3,1).insertSorted(m(2)).equals(cons(3,2,1)));
            System.out.println("    insertSorted test 2 passed");
            
            assertTrue( cons(3,2).insertSorted(m(1)).equals(cons(3,2,1)));
            System.out.println("    insertSorted test 3 passed");
            
            assertTrue(cons(3,1).insertSorted(m(8)).equals(cons(8,3,1)));
            System.out.println("    insertSorted test 4 passed");
            
            assertTrue( cons(3,2,1).insertSorted(m(2)).equals(cons(3,2,2,1)));
            System.out.println("    insertSorted test 5 passed");
            
            assertTrue(cons(4,4,3,3,2,2).insertSorted(m(2)).equals(cons(4,4,3,3,2,2,2)));
            System.out.println("    insertSorted test 6 passed");
            
            assertTrue(cons(4,4,3,3,2,2).insertSorted(m(0)).equals(cons(4,4,3,3,2,2,0)));
            System.out.println("    insertSorted test 7 passed");
            
            assertTrue( cons(4,4,3,3,2,2).insertSorted(m(3)).equals(cons(4,4,3,3,3,2,2)));
            System.out.println("    insertSorted test 8 passed");
            
            assertTrue( cons(4,4,3,3,2,2).insertSorted(m(4)).equals(cons(4,4,4,3,3,2,2)));
            System.out.println("    insertSorted test 9 passed");
            
            assertTrue( cons(4,4,3,3,2,2).insertSorted(m(8)).equals(cons(8,4,4,3,3,2,2)));
            System.out.println("    insertSorted test 10 passed");
            
            System.out.println("   All insertSorted tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    insertSorted test failed: " + e.getMessage() + "\n");
        }
        
       
        System.out.println("c. Testing sort() method:");
        try {
            assertTrue( cons(1).sort().equals(cons(1)));
            System.out.println("    sort test 1 passed");
            
            assertTrue( cons(1,1).sort().equals(cons(1,1)));
            System.out.println("    sort test 2 passed");
            
            assertTrue( cons(1,1,2).sort().equals(cons(2,1,1)));
            System.out.println("    sort test 3 passed");
            
            assertTrue( cons(1,2,3,4).sort().equals(cons(4,3,2,1)));
            System.out.println("    sort test 4 passed");
            
            assertTrue( cons(2,1,2,5,3,0,8,4).sort().equals(cons(8,5,4,3,2,2,1,0)));
            System.out.println("    sort test 5 passed");
            
            System.out.println("   All sort tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    sort test failed: " + e.getMessage() + "\n");
        }
        
    
        System.out.println("d. Testing simplify() method:");
        try {
            assertTrue( cons(m(1,1)).simplify().equals(cons(m(1,1))));
            System.out.println("   simplify test 1 passed");
            
            assertTrue( cons(m(1,1), m(1,1)).simplify().equals(cons(m(2,1))));
            System.out.println("   simplify test 2 passed");
            
            assertTrue(cons(m(1,1), m(1,1), m(1,1)).simplify().equals(cons(m(3,1))));
            System.out.println("   simplify test 3 passed");
            
            assertTrue( cons(m(1,1), m(1,3), m(1,1), m(1,3), m(1,1)).simplify().equals(cons(m(2,3), m(3,1))));
            System.out.println("   simplify test 4 passed");
            
            assertTrue(cons(m(1,1), m(1,3), m(1,1), m(1,3), m(1,1)).simplify().equals(cons(m(2,3), m(3,1))));
            System.out.println("   simplify test 5 passed");
            
            assertTrue( cons(m(1,1), m(1,7), m(1,3), m(1,1), m(1,3), m(1,1), m(1,3), m(1,3), m(1, 8)).simplify().equals(
                    cons(m(1,8), m(1,7), m(4,3), m(3,1))
                ));
            System.out.println("   simplify test 6 passed");
            
            System.out.println("   All simplify tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("   simplify test failed: " + e.getMessage() + "\n");
        }
        
    
        System.out.println("e. Testing add() method:");
        try {
    
            assertTrue(poly("x").add(null).equals(poly("x")));
            System.out.println("    add test 1 passed");
            
            assertTrue( (poly("x").add(poly("x^2"))).equals(poly("x^2+x^1")));
            System.out.println("    add test 2 passed");
            
            assertTrue( poly("x + x").add(poly("x^2 + x + x^2")).equals(poly("2x^2 + 3x")));
            System.out.println("    add test 3 passed");
            
            System.out.println("   All add tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    add test failed: " + e.getMessage() + "\n");
        }
        
    
        System.out.println("f. Testing mul() method:");
        try {
            assertTrue( poly("x").mul(poly("x")).equals(poly("x^2")));
            System.out.println("    mul test 1 passed");
            
            assertTrue (poly("2x").mul(poly("5x")).equals(poly("10x^2")));
            System.out.println("    mul test 2 passed");
            
            assertTrue(poly("x^3 + 2x^2 + 3x - 1").mul(poly("6x^2")).equals(poly("6x^5 + 12x^4 + 18x^3 - 6x^2")));
            System.out.println("    mul test 3 passed");
            
            assertTrue( poly("x + 2").mul(poly("x + 3")).equals(poly("x^2 + 5x + 6")));
            System.out.println("    mul test 4 passed");
            
            assertTrue(poly("x^2 + x").mul(poly("x + 1")).equals(poly("x^3 + 2x^2 + x")));
            System.out.println("    mul test 5 passed");
            
            System.out.println("   All mul tests passed!\n");
        } catch (AssertionError e) {
            System.out.println("    mul test failed: " + e.getMessage() + "\n");
        }

    
    }
}
