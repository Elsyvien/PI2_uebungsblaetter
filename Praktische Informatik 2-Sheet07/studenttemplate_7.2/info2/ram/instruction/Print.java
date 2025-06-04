package info2.ram.instruction;

import info2.ram.Ram;


/**
 * Prints a value at a given address on the console. 
 */
public class Print implements Instruction {

    private int inAddress;
    
    public Print(final int inAddress) {
        this.inAddress = inAddress;
    }

    @Override
    public void execute(final Ram ram) {
        System.out.println(ram.read(this.inAddress));
    }
        
}