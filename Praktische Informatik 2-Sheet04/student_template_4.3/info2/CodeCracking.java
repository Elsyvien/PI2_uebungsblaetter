package info2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CodeCracking {
    
    //
    // Reads text file into String.
    //
    public static String readFromFile(final String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        
        final String cipher = readFromFile("cipher.txt");
        final String key = "find me!";
        final String decrypted = XORCipher.encryptDecrypt(cipher, key);
        
        System.out.println(cipher);
       
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println(decrypted);

    }
}