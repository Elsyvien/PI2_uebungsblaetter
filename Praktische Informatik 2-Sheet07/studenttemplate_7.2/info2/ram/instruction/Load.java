package info2.ram.instruction;

import info2.ram.Ram;

/**
 * Loads a constants value to a specific address. 
 */
public class Load implements Instruction {

    private long value;
    private int outAdr;
    
    public Load(final long value, final int outAdr) {
        this.value = value;
        this.outAdr = outAdr;
    }

    @Override
    public void execute(final Ram ram) {
        ram.write(this.outAdr, this.value);
    }
        
}