package info2.ram.instruction.monadic;

/**
 * Increments a value a given address by one and stores
 * the result at the other given address.
 */
public class Incr extends MonadicOperation {

    public Incr(final int inAdr, final int outAdr) {
        super(inAdr, outAdr);
    }

    @Override
    protected long evaluate(long value) {
        return value + 1;
    }

        
}