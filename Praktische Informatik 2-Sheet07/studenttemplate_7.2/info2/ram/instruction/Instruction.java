package info2.ram.instruction;

import info2.ram.Ram;

/**
 * This main interface for all instructions.
 * All subclasses will provide an execute method.
 */
public interface Instruction {
    
    public void execute(final Ram ram);
    
}