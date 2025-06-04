package info2.ram.instruction.jump;

import info2.ram.Ram;
import info2.ram.instruction.Instruction;

/**
 * Abstract joint parent class for all conditional jump instructions.
 */
public abstract class CondJumpInstruction implements Instruction {

    private int inAdr;
    private int jmpIdx;
    
    public CondJumpInstruction(final int inAdr, final int jmpIdx) {
        this.inAdr = inAdr;
        this.jmpIdx = jmpIdx;
    }

    @Override
    public void execute(final Ram ram) {
        final long value = ram.read(this.inAdr);

        if (this.condition(value)) {
            ram.setPC(this.jmpIdx);
        }
    }
    
    protected abstract boolean condition(final long value);
}