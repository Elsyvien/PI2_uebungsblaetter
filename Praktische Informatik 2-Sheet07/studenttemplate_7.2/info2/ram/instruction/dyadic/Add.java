package info2.ram.instruction.dyadic;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

/**
 * Adds the values at the first two addresses and stores the result
 * at the third address.
 */
public class Add extends DyadicOperation {
    public Add(final int inAdr1, final int inAdr2, final int outAdr) {
        super(inAdr1, inAdr2, outAdr);
    }

    @Override
    protected long evaluate(long value1, long value2) {
        return value1 + value2;
    }
}