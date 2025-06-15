package info2.ram;

import info2.ram.instruction.Exit;
import info2.ram.instruction.Instruction;
import info2.ram.instruction.Load;
import info2.ram.instruction.Print;
import info2.ram.instruction.dyadic.Add;
import info2.ram.instruction.dyadic.Sub;

public class TestDyadicOperations {
    
    public static void main(String[] args) {
        // Create instance of Random Access Machine
        final Ram ram = new Ram();
        
        // This program tests the Add and Sub operations
        final long a = 10;
        final long b = 5;
        
        final Instruction[] program = {
            new Load(a, 0),    // 0: loads value of a into memory at address 0
            new Load(b, 1),    // 1: loads value of b into memory at address 1
            new Add(0, 1, 2),  // 2: adds memory[0] and memory[1] and stores the result in memory[2]
            new Print(2),      // 3: prints memory[2] (should be 15)
            new Sub(0, 1, 3),  // 4: subtracts memory[1] from memory[0] and stores the result in memory[3]
            new Print(3),      // 5: prints memory[3] (should be 5)
            new Exit()         // 6: terminates the program
        };
        
        // Execute the program
        ram.run(program);
    }
}