package info2.ram.instruction.dyadic;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

public abstract class DyadicOperation implements Instruction {
    private int inAdr1;
    private int inAdr2;
    private int outAdr;

    public DyadicOperation(final int inAdr1, final int inAdr2, final int outAdr) {
        this.inAdr1 = inAdr1;
        this.inAdr2 = inAdr2;
        this.outAdr = outAdr;
    }

    @Override
    public void execute(Ram ram) {
        ram.write(
                this.outAdr,
                evaluate(ram.read(this.inAdr1), ram.read(this.inAdr2))
        );
    }

    protected abstract long evaluate(long value1, long value2);
}
