package info2.ram.instruction.dyadic;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

/**
 * Subtracts the values at the first two addresses and stores the result
 * at the third address.
 */
public class Sub implements Instruction {

    private int inAdr1;
    private int inAdr2;
    private int outAdr;
    
    public Sub(final int inAdr1, final int inAdr2, final int outAdr) {
        this.inAdr1 = inAdr1;
        this.inAdr2 = inAdr2;
        this.outAdr = outAdr;
    }

    @Override
    public void execute(final Ram ram) {
        ram.write(
            this.outAdr,
            ram.read(this.inAdr1) - ram.read(this.inAdr2)
        );
    }

        
}