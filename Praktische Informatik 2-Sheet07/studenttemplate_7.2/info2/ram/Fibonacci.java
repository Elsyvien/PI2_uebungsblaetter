package info2.ram;

import info2.ram.instruction.Exit;
import info2.ram.instruction.Instruction;
import info2.ram.instruction.Load;
import info2.ram.instruction.Print;
import info2.ram.instruction.dyadic.Add;
import info2.ram.instruction.jump.Jez;
import info2.ram.instruction.jump.Jmp;
import info2.ram.instruction.monadic.Decr;

public class Fibonacci {
    public static void main(String[] args) {
        final Ram ram = new Ram();
        final long n = 10; // n-te Fibonacci-Zahl
        final Instruction[] program = {
            new Load(n, 0),      // 0: counter = n
            new Jez(0, 12),      // 1: if n == 0 jump to instruction 12
            new Load(0, 1),      // 2: prev = 0
            new Load(1, 2),      // 3: curr = 1
            new Load(0, 4),      // 4: constant zero in mem[4]
            new Decr(0, 0),      // 5: counter = n - 1
            new Jez(0, 13),      // 6: if counter == 0 jump to instruction 13
            new Add(1, 2, 3),    // 7: temp = prev + curr
            new Add(2, 4, 1),    // 8: prev = curr
            new Add(3, 4, 2),    // 9: curr = temp
            new Decr(0, 0),      // 10: counter--
            new Jmp(6),          // 11: jump back to instruction 6
            new Print(0),        // 12: prints 0 (n == 0)
            new Print(2),        // 13: prints Fibonacci(n)
            new Exit()           // 14: terminates the program

        };
        ram.run(program);
    }
}
