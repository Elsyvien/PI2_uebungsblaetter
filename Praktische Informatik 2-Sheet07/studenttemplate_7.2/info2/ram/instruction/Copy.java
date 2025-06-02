package info2.ram.instruction;

import info2.ram.Ram;

/**
 * This instruction copies a value from on address
 * to another address.
 */
public class Copy implements Instruction {

    private int inAdr;
    private int outAdr;
    
    public Copy(final int inAdr, final int outAdr) {
        this.inAdr = inAdr;
        this.outAdr = outAdr;
    }

    @Override
    public void execute(final Ram ram) {
        ram.write(this.outAdr, ram.read(this.inAdr));
    }
        
}