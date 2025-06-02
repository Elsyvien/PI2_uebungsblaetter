package info2.ram.instruction.monadic;

/**
 * Decrements a value a given address by one and stores
 * the result at the other given address.
 */
public class Decr extends MonadicOperation {

    public Decr(final int inAdr, final int outAdr) {
        super(inAdr, outAdr);
    }

    @Override
    protected long evaluate(long value) {
        return value - 1;
    }

        
}