package info2.ram.instruction.monadic;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

/**
 * Joint abstract parent class for all monadic operations. The subclasses
 * can thus reuse the execute-code and just have to specific how the actual
 * calculation is evaluated.
 */
public abstract class MonadicOperation implements Instruction {

    private int inAdr;
    private int outAdr;
    
    public MonadicOperation(final int inAdr, final int outAdr) {
        this.inAdr = inAdr;
        this.outAdr = outAdr;
    }

    @Override
    public void execute(final Ram ram) {
        ram.write(
            this.outAdr,
            this.evaluate(
                ram.read(this.inAdr)
            )
        );
    }
    
    protected abstract long evaluate(final long value); 
    
}