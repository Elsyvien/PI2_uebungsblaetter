package info2.ram.instruction.dyadic;

public class Mul extends DyadicOperation {
    public Mul(final int inAdr1, final int inAdr2, final int outAdr) {
        super(inAdr1, inAdr2, outAdr);
    }

    @Override
    protected long evaluate(long value1, long value2) {
        return value1 * value2;
    }
}

