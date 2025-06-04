package info2.ram.instruction;

import info2.ram.Ram;

/**
 * This instruction terminates the program execution.
 */
public class Exit implements Instruction {

    @Override
    public void execute(final Ram ram) {
        ram.terminate();
    }
        
}