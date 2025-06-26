package info2.polynomial.tools;

import info2.polynomial.Monomial;
import info2.polynomial.Polynomial;
import info2.polynomial.Rational;

/**
 * This is a simple implementation of a polynomial parser. 
 * instruction parser that can parse assignments and arithmetic expressions
 * consisting of the terminal symbols '+', '-','*', '/', '(', ')', '=', 
 * double literals, and identifier.
 *
 * @author Sebastian Otte
 */
public class PolynomialParser {

    private static final char[] WHITESPACES = {' ', '\t'};
    private static final char[] DIGITS = unroll('0', '9');
    private static final char[] LETTERS_LCASE = unroll('a', 'z');
    private static final char[] LETTERS_UCASE = unroll('A', 'Z');
    
    private static final char[] unroll(final char lbd, final char ubd) {
        final char[] result = new char[ubd - lbd + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = (char)(lbd + i);
        }
        return result;
    }

    private static final char PLUS  = '+';
    private static final char MINUS = '-';
    private static final char MUL   = '*';
    private static final char DIV   = '/';
    private static final char UNDER = '_';
    
    private static final char LPAR  = '(';
    private static final char RPAR  = ')';
    private static final char POW   = '^';
    
    private String str;
    private int    length;
    private int    ptr;
    
    public boolean isEOF() {
        return this.ptr >= this.length;
    }
    
    public PolynomialParser(final String str) {
        this.str    = str;
        this.ptr    = 0;
        this.length = str.length(); 
    }
    
    
    private char consumeChar() {
        return this.str.charAt(this.ptr++);
    }
    
    private char currentChar() {
        return this.str.charAt(this.ptr);
    }
    
    private static boolean isWhiteSpace(final char c) {
        return isOneOf(c, WHITESPACES);
    }
    
    private static boolean isOneOf(final char c, final char... chars) {
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                return true;
            }
        }
        return false;
    }
    
    private void nextToken() {
        while (!this.isEOF() && isWhiteSpace(this.currentChar())) {
            this.consumeChar();
        }
    }
    
    private static boolean isDigit(final char value) {
        return isOneOf(value, DIGITS);
    }
    
    private int consumeDigits() {
        //
        int ctr = 0;
        //
        while (
            !this.isEOF() && isDigit(this.currentChar())
        ) {
            ctr++;
            this.consumeChar();
        }
        //
        return ctr;
    }
    
    private static boolean isLetter(final char value) {
        return (
            isOneOf(value, LETTERS_LCASE) ||
            isOneOf(value, LETTERS_UCASE)
        );
    }
    
    private int consumeLetters() {
        //
        int ctr = 0;
        //
        while (
            !this.isEOF() && isLetter(this.currentChar()) 
        ) {
            ctr++;
            this.consumeChar();
        }
        //
        return ctr;
    }
    
    private int consumeLettersAndDigits() {
        //
        int ctr = 0;
        //
        while (
            !this.isEOF() && 
            (
                isLetter(this.currentChar()) || 
                isDigit(this.currentChar()) ||
                (this.currentChar() == UNDER)
            ) 
        ) {
            ctr++;
            this.consumeChar();
        }
        //
        return ctr;     
    }
    
    
    public boolean parseChar(final char c) {
        final int mark = this.ptr;
        this.nextToken();
        if (!this.isEOF() && (this.currentChar() == c)) {
            this.consumeChar();
            return true;
        } else {
            this.ptr = mark;
            return false;
        }
    }
    
    public boolean parsePlus() {
        return parseChar(PLUS);
    }
    
    public boolean parseMinus() {
        return parseChar(MINUS);
    }

    public boolean parseMul() {
        return parseChar(MUL);
    }
    
    public boolean parseDiv() {
        return parseChar(DIV);
    }
    
    public boolean parseLPar() {
        return parseChar(LPAR);
    }
    
    public boolean parseRPar() {
        return parseChar(RPAR);
    }
    
    public boolean parsePow() {
        return parseChar(POW);
    }
    
        
    public String parseVariable() {
        final int mark = this.ptr;
        //
        this.nextToken();
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        final int start = this.ptr;
        //
        // an identifier starts with a letter.
        //
        if (this.consumeLetters() == 0) {
            this.ptr = mark;
            return null;
        }
        this.consumeLettersAndDigits();
        //
        // at this point there was a well-formed identifier.
        //
        final String name = this.str.substring(start, this.ptr);
        return name;
    }
    
    public Integer parseInt() {
        final int mark = this.ptr;
        //
        this.nextToken();
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        final int start = this.ptr;
        //
        // parse sign optionally.
        //
        if (isOneOf(this.currentChar(), MINUS, PLUS)) {
            this.consumeChar();
        }
        //
        final int digits = this.consumeDigits(); 
        if (digits > 0) {
            final int value = Integer.parseInt(this.str.substring(start, this.ptr));
            return value;
        }
        //
        this.ptr = mark;
        return null;
    }
    
    public Integer parseIntExpression() {
        //
        final int mark = this.ptr;
        this.nextToken();
        //
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        if (this.parseLPar()) {
            final Integer result = this.parseIntExpression();
            if ((result != null) && this.parseRPar()) {
                return result;
            }
        } else {
            final Integer result = this.parseInt();
            if (result != null) {
                return result;
            }
        }
        //
        this.ptr = mark;
        return null;
    }
    
    public Rational parseFraction() {
        //
        final int mark = this.ptr;
        this.nextToken();
        //
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        final Integer num = this.parseIntExpression(); 
        if (num != null) {
            if (this.parseDiv()) {
                final Integer den = this.parseIntExpression();
                if (den != null) {
                    return new Rational(num, den);
                }
            } else {
                return new Rational(num);
            }
        }
        //
        this.ptr = mark;
        return null;

    }
    
    public Rational parseCoefficient(final boolean negate) {
        //
        final int mark = this.ptr;
        this.nextToken();
        //
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        final Rational fraction = this.parseFraction();
        if (fraction != null) {
            if (negate) {
                return fraction.negate().reduce();
            }
            return fraction.reduce();
        } else if (this.parseLPar()) {
            final Rational result = this.parseCoefficient(negate);
            if ((result != null) && this.parseRPar()) {
                return result;
            }
        }
        //
        this.ptr = mark;
        return null;
    }
    
    
    
    public Monomial parseMonomial(final boolean negate) {
        //
        final int mark = this.ptr;
        this.nextToken();
        //
        if (this.isEOF()) {
            this.ptr = mark;
            return null;
        }
        //
        Rational coefficient = this.parseCoefficient(negate);
        final boolean hasCoefficient = coefficient != null;
        final boolean hasMul = this.parseMul();        
        final boolean hasMinus = this.parseMinus();
        final String variable = this.parseVariable();
        final boolean hasVariable = variable != null;
        //
        if (hasVariable) {
            if (!variable.equals("x")) {
                throw new RuntimeException("Variable name '" + variable + "' not allowed. Use 'x' instead.");
            }
        }
        //
        boolean valid = false;
        //
        if (hasMul) {
            if (hasVariable && hasCoefficient) {
                valid = true;
            }
        } else {
            if (hasVariable || hasCoefficient) {
                valid = true;
            }
        } 
        //
        if (valid) {
            if (!hasCoefficient) {
                coefficient = new Rational((negate)?(-1):(1));
            }
            if (hasVariable) {
                if (hasMinus) {
                    coefficient = coefficient.negate();
                }
                if (this.parsePow()) {
                    Integer degree = this.parseIntExpression();
                    if (degree != null) {
                        return new Monomial(coefficient, degree);
                    }
                } else {
                    return new Monomial(coefficient, 1);
                }
            } else {
                return new Monomial(coefficient, 0);
            }
        }
        //
        // still here? let's try parenthesis expression ...
        //
        if (this.parseLPar()) {
            final Monomial monomial = this.parseMonomial(negate);
            if ((monomial != null) && this.parseRPar()) {
                return monomial;
            }
        }
        //
        this.ptr = mark;
        return null;

    }

    
    public Polynomial parsePolynomial() {
        return this.parsePolynomial(false);
    }
    
    private Polynomial parsePolynomial(final boolean negateNextMonomial) {
        //
        final int mark = this.ptr;
        this.nextToken();
        //
        final Monomial monomial = this.parseMonomial(negateNextMonomial);
        //
        if (monomial != null) {
            boolean hasNext = false;
            boolean hasMinus = false;
            
            if (this.parsePlus()) {
                hasNext = true;
                hasMinus = false;
            } else if (this.parseMinus()) {
                hasNext = true;
                hasMinus = true;
            }
            
            if (hasNext) {
                final Polynomial tail = this.parsePolynomial(hasMinus);
                return new Polynomial(monomial, tail);
            } else {
                return new Polynomial(monomial);
            }
            
        } else if (this.parseLPar()) {
            final Polynomial polynomial = this.parsePolynomial();
            if ((polynomial != null) && this.parseRPar()) {
                return polynomial;
            }
        }
        //
        this.ptr = mark;
        return null;        
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static Polynomial parse(final String str) {
        final PolynomialParser parser = new PolynomialParser(str);
        final Polynomial result = parser.parsePolynomial();
        parser.nextToken();
        if (!parser.isEOF()) {
            final String rest = parser.str.substring(parser.ptr);
            throw new RuntimeException("Could not parse \"" + rest + "\".");
        }
        return result;
    }

}
