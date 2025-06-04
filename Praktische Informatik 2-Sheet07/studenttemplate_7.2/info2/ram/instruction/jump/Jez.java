package info2.ram.instruction.jump;

/**
 * Jumps to a specific instruction if the value a the given address is zero. 
 */
public class Jez extends CondJumpInstruction {

    public Jez(final int inAdr, final int jmpIdx) {
        super(inAdr, jmpIdx);
    }

    @Override
    protected boolean condition(final long value) {
        return value == 0;
    }

}