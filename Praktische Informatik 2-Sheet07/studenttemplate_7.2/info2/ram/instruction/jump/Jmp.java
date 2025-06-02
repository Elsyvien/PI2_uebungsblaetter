package info2.ram.instruction.jump;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

/**
 * A simple jump instruction -> jumps to a specific instruction. 
 */
public class Jmp implements Instruction {

    private int jmpIdx;
    
    public Jmp(final int jmpIdx) {
        this.jmpIdx = jmpIdx;
    }

    @Override
    public void execute(final Ram ram) {
        ram.setPC(jmpIdx);
    }

}