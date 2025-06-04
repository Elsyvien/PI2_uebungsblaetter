package info2.ram;

import info2.ram.instruction.Exit;
import info2.ram.instruction.Instruction;
import info2.ram.instruction.Load;
import info2.ram.instruction.Print;
import info2.ram.instruction.dyadic.Mul;
import info2.ram.instruction.jump.Jez;
import info2.ram.instruction.jump.Jmp;
import info2.ram.instruction.monadic.Decr;

public class Factorial {
    
    public static void main(String[] args) {
        //
        // Create instance of Random Access Machine.
        //
        final Ram ram = new Ram();
        //
        // This program calculates the factorial n! of a number n.
        //
        final long n = 6;
        //
        final Instruction[] program = {
            new Load(n, 0),   // 0: loads value of n into memory at address 0
            new Load(1, 1),   // 1: loads value 1 into memory at address 1
            new Jez(0, 6),    // 2: jumps to instruction 6 if the value at address 0 is 0 (loop head)
            new Mul(0, 1, 1), // 3: multiplies memory[0] and memory[1] and stores the result in memory[1]
            new Decr(0, 0),   // 4: decreases value in memory[0] by 1 and stores result in memory[0]
            new Jmp(2),       // 5: jumps back to instruction 2 (actual loop)
            new Print(1),     // 6: prints memory[1]
            new Exit()        // 7: terminates the program
        };
        //
        // Executes the program.
        // 
        ram.run(program);
    }
}