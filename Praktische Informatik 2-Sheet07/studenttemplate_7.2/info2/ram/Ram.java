package info2.ram;

import info2.ram.instruction.Instruction;

/**
 * This is an extremely simplistic implementation
 * of a Random Access Machine (RAM) with a simple
 * memory for storing long values.
 *  
 * @author Sebastian Otte
 *
 */
public class Ram {
    
    public static final int TERMINATED = -1;
    public static final int DEFAULT_MEMORY_SIZE = 16384; 
    public static final int DEFAULT_STACK_SIZE = 1000;
    
    /** Memory **/
    private int memorySize;
    private long[] memory;
    
    /** Program counter **/
    private int pc; 
    
    /** The program (an array of instructions) **/
    private Instruction[] program;
    
    public int getMemorySize() {
        return this.memorySize;
    }
    
    public void reset() {
        this.pc = 0;
    }
    
    public Ram() {
        this(DEFAULT_MEMORY_SIZE, DEFAULT_STACK_SIZE);
    }
    
    public Ram(final int memorySize, final int stackSize) {
        this.memorySize = memorySize;
        this.memory = new long[memorySize];
        this.reset();
    }
    
    
    /** 
     * The RAM starts executing the first command in the program
     * (the program counter is initialized with zero). After executing
     * a command, the program counter is increased or set by a jump 
     * instruction. The program is executed until the PC points outside
     * of the program or it indicates termination.
     */
    public void run(final Instruction[] program) {
        this.reset();
        this.program = program;
        
        while (this.pc < this.program.length && this.pc != TERMINATED) {
            Instruction inst = this.program[this.pc];
            this.pc++;
            inst.execute(this);
            
        }

        System.out.println("execution finished");
    }
    
    public int getPC() {
        return this.pc;
    }
    
    public void setPC(final int index) {
        this.pc = index;
    }
    
    /**
     * Sets the PC to an invalid index such indicating
     * the termination of the program.
     */
    public void terminate() {
        this.pc = TERMINATED;
    }
    
    /**
     * Reads a long value at the given memory address
     */
    public long read(final int address) {
        return this.memory[address];
    }
    
    /**
     * Writes a long value at the given memory address.
     */
    public void write(final int address, final long value) {
        this.memory[address] = value;
    }
    

}