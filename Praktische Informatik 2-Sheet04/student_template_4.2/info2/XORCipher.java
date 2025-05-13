package info2;

public class XORCipher {
    
    //
    // This table contains 64 possible characters that are
    // used as alphabet for this encryption exercise.
    // We can use 6 bits (e.g. the most right 6 bits of an
    // int value) to uniquely encode a character.
    //
    public static char[] CHAR_TABLE = {
        ' ', '\n', ',', '.', '\'', '-', ':', ';', '?', '(', ')', '!',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    };
    
    // ----------------------------------------------------------------
    // Exercise 2 (a)
    // ----------------------------------------------------------------

    // TODO: implement valueToChar
    
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (b) 
    // ----------------------------------------------------------------
    
    // TODO: implement charToValue
    
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (c)
    // ----------------------------------------------------------------
    
    // TODO: implement stringToValue
    
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (d)
    // ----------------------------------------------------------------
    
    // TODO: implement valuesToString
    
    
    
    // ----------------------------------------------------------------
    // Exercise 2 (e)
    // ----------------------------------------------------------------

    // TODO: implement encryptDecrypt for type int[]


    
    // ----------------------------------------------------------------
    // Exercise 2 (f)
    // ----------------------------------------------------------------
    
    // TODO: implement encryptDecryot for type String
        

    
    
    // ----------------------------------------------------------------
    
    public static void main(String[] args) {
        final String msg = "Dieser Text ist sehr geheim...";
        final String key = "keyword";
        
        final String cipher = encryptDecrypt(msg, key);
        final String decrypted = encryptDecrypt(cipher, key);
        
        //
        // Wenn alles richtig implementiert wurde, sollte
        // der String decrypted wieder genau dem String 
        // msg entsprechen, waehrend der String cipher fuer
        // uns nicht lesbar ist.
        //
        
        System.out.println(msg);
        System.out.println(cipher);
        System.out.println(decrypted);
        
        System.out.println();
        
    }
}